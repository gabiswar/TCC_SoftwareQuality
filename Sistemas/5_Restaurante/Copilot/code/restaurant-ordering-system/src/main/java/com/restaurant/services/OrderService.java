package com.restaurant.services;

import com.restaurant.models.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;

    public OrderService() {
        this.orders = new ArrayList<>();
    }

    public Order createOrder() {
        Order newOrder = new Order();
        orders.add(newOrder);
        return newOrder;
    }

    public List<Order> viewOrders() {
        return orders;
    }

    public void updateOrderStatus(int orderId, String status) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(status);
                break;
            }
        }
    }

    public double calculateTotalPrice(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order.calculateTotalPrice();
            }
        }
        return 0.0;
    }
}