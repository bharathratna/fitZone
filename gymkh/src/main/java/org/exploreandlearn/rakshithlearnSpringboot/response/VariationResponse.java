package org.exploreandlearn.rakshithlearnSpringboot.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariationResponse {
    private Integer variationId;
    private String variationName;
}
