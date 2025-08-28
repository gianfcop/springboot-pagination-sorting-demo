/*
 * Spring Boot API Starter Kit
 * Copyright (c) 2025 Gianfranco Coppola. All rights reserved.
 * Commercial use permitted. Redistribution prohibited.
 */
package com.starterkit.api.service;

import com.starterkit.api.dto.request.ProductRequest;
import com.starterkit.api.dto.response.PaginatedResponse;
import com.starterkit.api.dto.response.ProductResponse;
import com.starterkit.api.entity.Category;
import com.starterkit.api.entity.Product;
import com.starterkit.api.mapper.ProductMapper;
import com.starterkit.api.repository.CategoryRepository;
import com.starterkit.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private static final int MAX_PAGE_SIZE = 50;

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found with id: " + request.getCategoryId()));

        Product product = ProductMapper.toEntity(request, category);
        Product saved = productRepository.save(product);

        return ProductMapper.toResponse(saved);
    }

    public PaginatedResponse<ProductResponse> getAllProducts(int page, int size) {
        size = validatePageSize(size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = getProductsPageable(pageable);

        return buildPaginatedResponse(productPage);
    }

    public PaginatedResponse<ProductResponse> getAllProductsSorted(int page, int size, String sortField, String direction) {
        size = validatePageSize(size);
        Sort sort = direction.equalsIgnoreCase("desc") 
            ? Sort.by(sortField).descending() 
            : Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = getProductsPageable(pageable);

        return buildPaginatedResponse(productPage);
    }

    public PaginatedResponse<ProductResponse> getAllProductsMultiSort(int page, int size, List<String> sortParams) {
        size = validatePageSize(size);

        // Example: sortParams = ["name,asc", "price,desc"]
        Sort sort = Sort.unsorted();
        for (String param : sortParams) {
            String[] parts = param.split(",");
            if (parts.length == 2) {
                sort = sort.and(Sort.by(Sort.Direction.fromString(parts[1]), parts[0]));
            }
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = getProductsPageable(pageable);

        return buildPaginatedResponse(productPage);
    }

    public PaginatedResponse<ProductResponse> getProductsByCategory(Long categoryId, int page, int size) {
        size = validatePageSize(size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, pageable);

        return buildPaginatedResponse(productPage);
    }

    public List<ProductResponse> getTopPricedProducts(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("price").descending());
        Page<Product> productPage = getProductsPageable(pageable);
        return ProductMapper.toResponseList(productPage.getContent());
    }


    public Page<Product> getProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    private int validatePageSize(int size) {
        return Math.min(size, MAX_PAGE_SIZE);
    }

    private PaginatedResponse<ProductResponse> buildPaginatedResponse(Page<Product> page) {
        return PaginatedResponse.<ProductResponse>builder()
                .content(ProductMapper.toResponseList(page.getContent()))
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}