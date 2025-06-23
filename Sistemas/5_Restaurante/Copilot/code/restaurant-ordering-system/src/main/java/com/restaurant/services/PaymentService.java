package com.restaurant.services;

import com.restaurant.models.Order;
import com.restaurant.utils.ReceiptGenerator;

public class PaymentService {

    public double calculateTotal(Order order) {
        return order.getTotalPrice();
    }

    public String generateReceipt(Order order) {
        ReceiptGenerator receiptGenerator = new ReceiptGenerator();
        return receiptGenerator.generateReceipt(order);
    }
}