//package com.ralfin.managementsystemrmg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Garment {

    public String id;
    public String name;
    public String description;
    public String size;
    public String color;
    public double price;
    public int stockQuantity;

    void updateStock(int quantity) {
        stockQuantity = quantity;
    }

    double calculateDiscountPrice(double discountPercentage) {
        double discount = price * (discountPercentage / 100);
        return discount;
    }
}

class Fabric {

    public String id;
    public String type;
    public String color;
    public double pricePerMeter;

    public double calculateCost(double meters) {
        double cost = pricePerMeter * meters;
        return cost;
    }
}

class Supplier {

    public String id;
    public String name;
    public String contactInfo;
    private List<Fabric> suppliedFabrics = new ArrayList<>();

    public void addFabric(Fabric fabric) {
        suppliedFabrics.add(fabric);
    }

    public List<Fabric> getSuppliedFabrics() {
        return suppliedFabrics;
    }
}

class Order {

    public String orderId;
    public Date orderDate;
    public int customerID;
    public List<Garment> garments = new ArrayList<>();
    public double totalAmount;

    public void addGarment(Garment garment) {
        garments.add(garment);
    }

    public double calculateTotalAmount() {
        for (Garment g : garments) {
            totalAmount += g.price;
        }
        return totalAmount;
    }

    void printOrderDetails() {
        String line = "-".repeat(50);
        System.out.println(line);
        System.out.println("|" + " ".repeat(18) + "Order Details" + " ".repeat(17) + "|");
        System.out.println(line);
        for (Garment g : garments) {
            System.out.printf("| %-12s : %-30s  |%n", "Name", g.name);
            System.out.printf("| %-12s : %-31.2f |%n", "Price", g.price);
            System.out.printf("| %-12s : %-30s  |%n", "Description", g.description);
            System.out.println(line);
        }
    }
}

class Customer {

    public String customerId;
    public String name;
    public String email;
    public String phone;
    private List<Order> orders = new ArrayList<>();

    public void placeOrder(Order order) {
        orders.add(order);
        order.printOrderDetails();
        System.out.println("Order Placed");

    }

    public List<Order> viewOrders() {
        return null;
    }
}

class Inventory {

    List<Garment> garments = new ArrayList<>();

    void addGarment(Garment garment) {
        garments.add(garment);
    }

    void removeGarment(String id) {
        garments.remove(id);
    }

    Garment findGarment(String id) {
        for (Garment g : garments) {
            if (g.id == id) {
                return g;
            }
        }
        return null;
    }
}

public class ManagementSystemRMG {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
