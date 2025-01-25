package com.conenctkhalid.product_service.constants;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Data
public class RestApiResponse {

    @Builder
    @Data
    public static class ValidationError {
        String fieldName;
        String validationMessage;
    }

    private int statusCode;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object details;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object errors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    public static ResponseEntity<Object> buildResponseWithDetails(int statusCode, String message, Object details) {
        return new ResponseEntity<>(RestApiResponse.builder()
                .statusCode(statusCode)
                .message(message)
                .details(details)
                .build(),
                HttpStatus.valueOf(statusCode));
    }
}
