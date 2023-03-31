package com.dailycodebuffer.OrderService.service;

import com.dailycodebuffer.OrderService.entity.Order;
import com.dailycodebuffer.OrderService.external.client.ProductService;
import com.dailycodebuffer.OrderService.model.OrderRequest;
import com.dailycodebuffer.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    /**
     * Order Entity -> save the data with status order created. Product service -> Block product(reduce the quantity). Payment
     *  service->payment->success->COMPLETE. Else CANCELLED.
     */

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        log.info("Placing order request: {}", orderRequest);
        log.info("Calling PRODUCT-SERVICE to reduce product quantity for productId; {} by quantity: {}",
                orderRequest.getProductId(), orderRequest.getQuantity());
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        log.info("Creating order with status as CREATED");

        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .amount(orderRequest.getAmount())
                .orderDate(Instant.now())
                .orderStatus("CREATED")
                .build();
        order = orderRepository.save(order);
        log.info("Successfully placed order with orderId:{}", order.getId());
        return order.getId();
    }
}
