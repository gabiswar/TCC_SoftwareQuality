package com.restaurant.services;

import com.restaurant.models.KitchenStatus;
import com.restaurant.models.Order;

import java.util.HashMap;
import java.util.Map;

public class KitchenService {
    private Map<Integer, KitchenStatus> kitchenStatusMap;

    public KitchenService() {
        kitchenStatusMap = new HashMap<>();
    }

    public void sendOrderToKitchen(Order order) {
        KitchenStatus status = new KitchenStatus(order.getOrderId(), "Preparing");
        kitchenStatusMap.put(order.getOrderId(), status);
        System.out.println("Order " + order.getOrderId() + " sent to kitchen.");
    }

    public void updateKitchenStatus(int orderId, String status) {
        if (kitchenStatusMap.containsKey(orderId)) {
            kitchenStatusMap.get(orderId).setStatus(status);
            System.out.println("Order " + orderId + " status updated to " + status + ".");
        } else {
            System.out.println("Order " + orderId + " not found in kitchen status.");
        }
    }

    public KitchenStatus getKitchenStatus(int orderId) {
        return kitchenStatusMap.get(orderId);
    }
}