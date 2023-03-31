package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.entity.Product;
import com.dailycodebuffer.ProductService.exception.ProductServiceCustomException;
import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.repository.ProductRepository;
import com.dailycodebuffer.ProductService.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product: {} to repository", productRequest.getProductName());
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);

        log.info("Product created with productId: {}", product.getProductId());
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for productId: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product not present for given id: " + productId, "PRODUCT_NOT_FOUND"));

        ProductResponse productResponse = ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity by {} for productId: {}", quantity, productId);
        Product product = new Product();
        BeanUtils.copyProperties(getProductById(productId), product);

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity saved successfully");
    }


}
