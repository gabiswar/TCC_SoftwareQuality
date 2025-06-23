package com.restaurant.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private List<MenuItem> menuItems;
    private double totalPrice;
    private String status;

    public Order(String orderId) {
        this.orderId = orderId;
        this.menuItems = new ArrayList<>();
        this.totalPrice = 0.0;
        this.status = "Pending";
    }

    public String getOrderId() {
        return orderId;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        totalPrice = 0.0;
        for (MenuItem item : menuItems) {
            totalPrice += item.getPrice();
        }
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}