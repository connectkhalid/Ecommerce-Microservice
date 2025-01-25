package com.conenctkhalid.product_service.controller.pub;

import com.conenctkhalid.product_service.constants.Constants;
import com.conenctkhalid.product_service.constants.RestResponseMessage;
import com.conenctkhalid.product_service.constants.RestResponseStatusCode;
import com.conenctkhalid.product_service.dto.ProductRequestDto;
import com.conenctkhalid.product_service.dto.ProductResponseDto;
import com.conenctkhalid.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.conenctkhalid.product_service.constants.RestApiResponse.buildResponseWithDetails;

@Slf4j
@RestController
@CrossOrigin
@Validated
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    public static final String API_PATH_CREATE_PRODUCT= Constants.ApiPath.PRIVATE_API_PATH + "/create-product";
    public static final String API_PATH_GET_ALL_PRODUCT = Constants.ApiPath.PRIVATE_API_PATH + "/product-list";

    @PostMapping(
            path = API_PATH_CREATE_PRODUCT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductRequestDto requestDto){
        ProductResponseDto responseDto = productService.createProduct(requestDto);
        return  buildResponseWithDetails(RestResponseStatusCode.OK_STATUS, RestResponseMessage.CREATE_OK, responseDto);
    }
    @GetMapping(
            path = API_PATH_GET_ALL_PRODUCT,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> getProduct(){
        List<ProductResponseDto> productResponseDtoList = productService.getAllProducts();
        return buildResponseWithDetails(RestResponseStatusCode.OK_STATUS, RestResponseMessage.FETCH_OK, productResponseDtoList);
    }
}
