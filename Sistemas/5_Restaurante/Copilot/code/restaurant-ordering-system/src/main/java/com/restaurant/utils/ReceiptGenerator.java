package com.restaurant.utils;

import com.restaurant.models.Order;

import java.text.DecimalFormat;

public class ReceiptGenerator {

    public static String generateReceipt(Order order) {
        StringBuilder receipt = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00");

        receipt.append("Receipt for Order ID: ").append(order.getOrderId()).append("\n");
        receipt.append("Items:\n");

        order.getMenuItems().forEach(item -> {
            receipt.append(item.getItemName())
                   .append(" - $")
                   .append(df.format(item.getPrice()))
                   .append("\n");
        });

        receipt.append("Total Price: $").append(df.format(order.getTotalPrice())).append("\n");
        receipt.append("Status: ").append(order.getStatus()).append("\n");
        receipt.append("Thank you for your order!");

        return receipt.toString();
    }
}