package com.conenctkhalid.product_service.service;

import com.conenctkhalid.product_service.dto.ProductRequestDto;
import com.conenctkhalid.product_service.dto.ProductResponseDto;
import com.conenctkhalid.product_service.model.Product;
import com.conenctkhalid.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public ProductResponseDto createProduct(ProductRequestDto request){
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        repository.save(product);
        log.info("Product {} is saved.", product.getId());
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = repository.findAll();
        return products.stream().map(product -> ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build()).toList();
    }
}
