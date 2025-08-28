/*
 * Spring Boot API Starter Kit
 * Copyright (c) 2025 Gianfranco Coppola. All rights reserved.
 * Commercial use permitted. Redistribution prohibited.
 */
package com.starterkit.api.mapper;

import com.starterkit.api.dto.request.ProductRequest;
import com.starterkit.api.dto.response.ProductResponse;
import com.starterkit.api.entity.Category;
import com.starterkit.api.entity.Product;

import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public Product toEntity(ProductRequest request, Category category) {
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .category(category)
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .build();
    }

    public List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }
}
