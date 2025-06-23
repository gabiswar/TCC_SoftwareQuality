package com.restaurant;

import com.restaurant.services.OrderService;
import com.restaurant.services.KitchenService;
import com.restaurant.services.PaymentService;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        KitchenService kitchenService = new KitchenService();
        PaymentService paymentService = new PaymentService();

        // Initialize application and services
        System.out.println("Restaurant Ordering System Initialized");

        // Here you can add code to interact with the services, such as creating orders, processing payments, etc.
    }
}