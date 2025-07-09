package com.example.inventory.utils;

import com.example.inventory.models.Product;
import com.example.inventory.models.Warehouse;

import java.util.Date;

public class TransferUtils {

    public static void transferProduct(Warehouse sourceWarehouse, Warehouse destinationWarehouse, Product product, int quantity, Date transferDate) {
        if (sourceWarehouse.getProducts().containsKey(product.getId()) && sourceWarehouse.getProducts().get(product.getId()) >= quantity) {
            // Decrease quantity in source warehouse
            sourceWarehouse.getProducts().put(product.getId(), sourceWarehouse.getProducts().get(product.getId()) - quantity);
            
            // Increase quantity in destination warehouse
            destinationWarehouse.getProducts().put(product.getId(), destinationWarehouse.getProducts().getOrDefault(product.getId(), 0) + quantity);
            
            // Log the transfer (this could be enhanced with a proper logging mechanism)
            System.out.println("Transferred " + quantity + " of " + product.getName() + " from " + sourceWarehouse.getName() + " to " + destinationWarehouse.getName() + " on " + transferDate);
        } else {
            System.out.println("Transfer failed: Insufficient quantity in source warehouse.");
        }
    }
}