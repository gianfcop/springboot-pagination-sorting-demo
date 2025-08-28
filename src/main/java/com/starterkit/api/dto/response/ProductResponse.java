/*
 * Spring Boot API Starter Kit
 * Copyright (c) 2025 Gianfranco Coppola. All rights reserved.
 * Commercial use permitted. Redistribution prohibited.
 */
package com.starterkit.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Product response DTO")
public class ProductResponse {

    @Schema(description = "Product ID", example = "1")
    Long id;

    @Schema(description = "Product category", example = "Electronics")
    String category;

    @Schema(description = "Product name", example = "Smartphone")
    String name;

    @Schema(description = "Product price", example = "399.99")
    Double price;

}
