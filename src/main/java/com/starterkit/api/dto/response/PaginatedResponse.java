/*
 * Spring Boot API Starter Kit
 * Copyright (c) 2025 Gianfranco Coppola. All rights reserved.
 * Commercial use permitted. Redistribution prohibited.
 */
package com.starterkit.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Generic paginated response wrapper")
public class PaginatedResponse<T> {

    @Schema(description = "List of items in the current page")
    private List<T> content;

    @Schema(description = "Current page number (zero-based)", example = "0")
    private int currentPage;

    @Schema(description = "Number of items per page", example = "10")
    private int pageSize;

    @Schema(description = "Total number of elements", example = "150")
    private long totalElements;

    @Schema(description = "Total number of pages", example = "15")
    private int totalPages;

    @Schema(description = "Is this the first page?", example = "true")
    private boolean first;

    @Schema(description = "Is this the last page?", example = "false")
    private boolean last;
}
