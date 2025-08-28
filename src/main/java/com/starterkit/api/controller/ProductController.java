/*
 * Spring Boot API Starter Kit
 * Copyright (c) 2025 Gianfranco Coppola. All rights reserved.
 * Commercial use permitted. Redistribution prohibited.
 */
package com.starterkit.api.controller;

import com.starterkit.api.dto.request.ProductRequest;
import com.starterkit.api.dto.response.PaginatedResponse;
import com.starterkit.api.dto.response.ProductResponse;
import com.starterkit.api.entity.Product;
import com.starterkit.api.mapper.ProductMapper;
import com.starterkit.api.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Basic R/W operations for products with pagination and sorting examples")
public class ProductController {

    private final ProductService productService;

    // 0. Create new product
    @Operation(
        summary = "Create a new product",
        description = "Creates a product with a name and category"
    )
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody ProductRequest request) {

        ProductResponse response = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // 1. Basic pagination
    @Operation(
        summary = "Get all products with basic pagination",
        description = "Returns a paginated list of products without sorting",
        parameters = {
            @Parameter(name = "page", description = "Page number (0-based)", example = "0") ,
            @Parameter(name = "size", description = "Number of items per page", example = "10") 
        }
    )
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public PaginatedResponse<ProductResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

    // 2. Pagination + single-field sorting
    @Operation(
        summary = "Get all products with pagination and single-field sorting",
        description = "Sort by a single field in ascending or descending order",
        parameters = {
            @Parameter(name = "page", description = "Page number (0-based)", example = "0"),
            @Parameter(name = "size", description = "Number of items per page", example = "10"),
            @Parameter(name = "sortField", description = "Field to sort by", example = "name"),
            @Parameter(name = "direction", description = "Sort direction", example = "asc") 
        }
    )
    @GetMapping(value = "/sorted", produces = APPLICATION_JSON_VALUE)
    public PaginatedResponse<ProductResponse> getAllProductsSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String direction) {
        return productService.getAllProductsSorted(page, size, sortField, direction);
    }

    // 3. Pagination + multiple sorting criteria
    @Operation(
        summary = "Get all products with pagination and multiple sort criteria",
        description = "Provide multiple sort parameters like: ?sort=name,asc&sort=price,desc",
        parameters = {
            @Parameter(name = "page", description = "Page number (0-based)", example = "0") ,
            @Parameter(name = "size", description = "Number of items per page", example = "10"),
            @Parameter(name = "sort", description = "Sorting criteria in format field,direction", example = "name,asc") 
        }
    )
    @GetMapping(value = "/multi-sort", produces = APPLICATION_JSON_VALUE)
    public PaginatedResponse<ProductResponse> getAllProductsMultiSort(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam List<String> sort) {
        return productService.getAllProductsMultiSort(page, size, sort);
    }

    // 4. Combine Pagination + Sorting via Query Params (Spring handles automatically)
    @Operation(
        summary = "Get all products using Spring's Pageable",
        description = "Pagination and sorting handled automatically by Spring Boot",
        parameters = {
            @Parameter(name = "page", description = "Page number (0-based)", example = "0"),
            @Parameter(name = "size", description = "Number of items per page", example = "10"),
            @Parameter(name = "sort", description = "Sorting criteria in format field,direction", example = "name,asc")
        }
    )
    @GetMapping(value = "/query-sort", produces = APPLICATION_JSON_VALUE)
    public Page<ProductResponse> getAllProductsQuerySort(@PageableDefault(size = 10) Pageable pageable) {
        Page<Product> pageResult = productService.getProductsPageable(pageable);
        return pageResult.map(ProductMapper::toResponse);
    }

    // 5. Products by category with pagination
    @Operation(
        summary = "Get products by category with pagination",
        description = "Returns paginated products belonging to a specific category",
        parameters = {
            @Parameter(name = "categoryId", description = "Category ID", example = "1"),
            @Parameter(name = "page", description = "Page number (0-based)", example = "0"),
            @Parameter(name = "size", description = "Number of items per page", example = "10") 
        }
    )
    @GetMapping(value = "/by-category/{categoryId}", produces = APPLICATION_JSON_VALUE)
    public PaginatedResponse<ProductResponse> getProductsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getProductsByCategory(categoryId, page, size);
    }

    // 6. Top priced products (no pagination, just top N sorted)
    @Operation(
        summary = "Get top N priced products",
        description = "Returns a list of the most expensive products, limited by the given number",
        parameters = {
            @Parameter(description = "Number of top products to retrieve", example = "5") 
        }
    )
    @GetMapping(value = "/top-priced", produces = APPLICATION_JSON_VALUE)
    public List<ProductResponse> getTopPricedProducts(
            @RequestParam(defaultValue = "5") int limit) {
        return productService.getTopPricedProducts(limit);
    }
}
