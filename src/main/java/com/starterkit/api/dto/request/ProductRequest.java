/*
 * Spring Boot API Starter Kit
 * Copyright (c) 2025 Gianfranco Coppola. All rights reserved.
 * Commercial use permitted. Redistribution prohibited.
 */
package com.starterkit.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

    @Schema(description = "Product name", example = "Smartphone")
    String name;

    @Schema(description = "Product price", example = "399.99")
    Double price;

    @Schema(description = "Category ID to which the product belongs", example = "1")
    Long categoryId;
}
