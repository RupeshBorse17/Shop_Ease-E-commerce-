package com.rupesh.SpringEcom.service;

import com.rupesh.SpringEcom.model.Orders;
import com.rupesh.SpringEcom.model.OrderItem;
import com.rupesh.SpringEcom.model.Product;
import com.rupesh.SpringEcom.model.dto.OrderItemRequest;
import com.rupesh.SpringEcom.model.dto.OrderItemResponse;
import com.rupesh.SpringEcom.model.dto.OrderRequest;
import com.rupesh.SpringEcom.model.dto.OrderResponse;
import com.rupesh.SpringEcom.repo.OrderRepo;
import com.rupesh.SpringEcom.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Service
public class OrderService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;


    public OrderResponse placeOrder(OrderRequest request) {

        Orders orders = new Orders();
        String orderId = "ORD" + (UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        orders.setOrderId(orderId);
        orders.setCustomerName(request.customerName());
        orders.setEmail(request.email());
        orders.setStatus("PLACED");
        orders.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemReq : request.items()) {

            Product product = productRepo.findById(itemReq.productId())
                    .orElseThrow(() -> new RuntimeException("Product not Found"));

            product.setStockQuantity(product.getStockQuantity() - itemReq.quantity());
            productRepo.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemReq.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity())))
                    .orders(orders)
                    .build();

            orderItems.add(orderItem);

        }
        orders.setOrderItems(orderItems);
        Orders savedOrders = orderRepo.save(orders);

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItem item : orders.getOrderItems()) {

            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
            itemResponses.add(orderItemResponse);
        }

        OrderResponse orderResponse = new OrderResponse(
                savedOrders.getOrderId(),
                savedOrders.getCustomerName(),
                savedOrders.getEmail(),
                savedOrders.getStatus(),
                savedOrders.getOrderDate(),
                itemResponses
        );

        return orderResponse;
    }

    @Transactional
    public List<OrderResponse> getAllOrdersResponses() {

        List<Orders> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for(Orders order : orders){

            List<OrderItemResponse> itemResponses = new ArrayList<>();

            for(OrderItem item : order.getOrderItems()){

                OrderItemResponse orderItemResponse = new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                );
                itemResponses.add(orderItemResponse);

            }


            OrderResponse orderResponse = new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    itemResponses
            );

            orderResponses.add(orderResponse);
        }

        return orderResponses;

    }




}
