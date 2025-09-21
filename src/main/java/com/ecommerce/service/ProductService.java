package com.ecommerce.service;

import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = new Product();

        updateProductFromRequest(product,productRequest);
        Product savedProduct = productRepository.save(product);

        return mapToProductResponse(savedProduct);

    }


    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {

            return productRepository.findById(id)
                    .map(existingProduct -> {
                        updateProductFromRequest(existingProduct, productRequest);
                        Product savedProduct = productRepository.save(existingProduct);
                        return mapToProductResponse(savedProduct);
                    }).orElseThrow(() -> new RuntimeException("Product not found : "+ id));

    }


    private void updateProductFromRequest(Product product, ProductRequest productRequest) {

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setImageUrl(productRequest.getImageUrl());

    }

    private ProductResponse mapToProductResponse(Product savedProduct) {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(savedProduct.getId());
        productResponse.setName(savedProduct.getName());
        productResponse.setActive(savedProduct.getActive());
        productResponse.setPrice(savedProduct.getPrice());
        productResponse.setCategory(savedProduct.getCategory());
        productResponse.setDescription(savedProduct.getDescription());
        productResponse.setStockQuantity(savedProduct.getStockQuantity());
        productResponse.setImageUrl(savedProduct.getImageUrl());
        return productResponse;
    }

}
