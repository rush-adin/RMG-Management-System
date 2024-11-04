package com.ralfin.managementsystemrmg;

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

    Garment(String id, String name, String description, String color, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

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

    Fabric(String id, String type, String color, double pricePerMeter) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.pricePerMeter = pricePerMeter;
    }

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

    Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

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

    Order(String orderId, Date orderDate, int customerID) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerID = customerID;
    }

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

        System.out.println(
                "|" + " ".repeat(18) + "Order Details" + " ".repeat(17) + "|");
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

    Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void placeOrder(Order order) {
        orders.add(order);
        order.printOrderDetails();
        System.out.println("Order Placed");

    }

    public List<Order> viewOrders() {
        return orders;
    }
}

class Inventory {

    List<Garment> garments = new ArrayList<>();
    List<Supplier> suppliers = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();

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

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String line = "-".repeat(39);
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println(line);
            System.out.println("|     ** RMG Management System **     |");
            System.out.println(line);
            System.out.printf("|      %-30s |%n", "1 : Manage Garments");
            System.out.printf("|      %-30s |%n", "2 : Manage Suppliers");
            System.out.printf("|      %-30s |%n", "3 : Manage Customers");
            System.out.printf("|      %-30s |%n", "4 : Update Orders");
            System.out.printf("|      %-30s |%n", "5 : Modify Inventories");
            System.out.printf("|      %-30s |%n", "6 : Exit");
            System.out.println(line);
            System.out.print("Input ");
            int choice = scan.nextInt();
            clear();

            switch (choice) {
                case 1: {
                    mngGar();
                    break;
                }

                case 2: {
                    mngSup();
                    break;
                }

                case 3: {
                    mngCust();
                    break;
                }

                case 4: {
                    mngOrdr();
                    break;
                }

                case 5: {
                    mngInvnt();
                    break;
                }

                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);  // Exit the program
                    break;

                default:
                    System.out.println("Invalid option. Please choose a valid number.");
            }

        }

    }

    //Manage Garments
    private static void mngGar() {
        while (true) {
            System.out.println(line);
            System.out.println("|         ** Manage Garments **       |");
            System.out.println(line);
            System.out.printf("|      %-30s |%n", "1 : Add New Garment");
            System.out.printf("|      %-30s |%n", "2 : Update Garment");
            System.out.printf("|      %-30s |%n", "3 : Delete Garment");
            System.out.printf("|      %-30s |%n", "4 : View All Garments");
            System.out.printf("|      %-30s |%n", "5 : Back to Main Menu");
            System.out.println(line);
            System.out.print("Enter ");
            int choice = scan.nextInt();
            clear();

            switch (choice) {
                case 1: {
                    //Inventory inventory = new Inventory();

                }
                case 2:
                    // Code to update a garment
                    break;
                case 3:
                    // Code to delete a garment
                    break;
                case 4:
                    // Code to view all garments
                    break;
                case 5:
                    // Back to main menu
                    return; // exits this inner menu loop
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    //Manage Suppliers
    private static void mngSup() {
        while (true) {
            System.out.println(line);
            System.out.println("|         ** Manage Suppliers **      |");
            System.out.println(line);
            System.out.printf("|      %-30s |%n", "1 : Add New Supplier");
            System.out.printf("|      %-30s |%n", "2 : Update Supplier");
            System.out.printf("|      %-30s |%n", "3 : Delete Supplier");
            System.out.printf("|      %-30s |%n", "4 : View All Suppliers");
            System.out.printf("|      %-30s |%n", "5 : Back to Main Menu");
            System.out.println(line);
            System.out.print("Enter ");
            int choice = scan.nextInt();
            clear();

            switch (choice) {
                case 1:
                    // Code to add a new supplier
                    break;
                case 2:
                    // Code to update a supplier
                    break;
                case 3:
                    // Code to delete a supplier
                    break;
                case 4:
                    // Code to view all suppliers
                    break;
                case 5:
                    // Back to main menu
                    return; // exits this inner menu loop
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    //Manage Customers
    private static void mngCust() {
        while (true) {
            System.out.println(line);
            System.out.println("|         ** Manage Customers **      |");
            System.out.println(line);
            System.out.printf("|      %-30s |%n", "1 : Add New Customer");
            System.out.printf("|      %-30s |%n", "2 : Update Customer");
            System.out.printf("|      %-30s |%n", "3 : Delete Customer");
            System.out.printf("|      %-30s |%n", "4 : View All Customers");
            System.out.printf("|      %-30s |%n", "5 : Back to Main Menu");
            System.out.println(line);
            System.out.print("Enter ");
            int choice = scan.nextInt();
            clear();

            switch (choice) {
                case 1:
                    // Code to add a new customer
                    break;
                case 2:
                    // Code to update a customer
                    break;
                case 3:
                    // Code to delete a customer
                    break;
                case 4:
                    // Code to view all customers
                    break;
                case 5:
                    // Back to main menu
                    return; // exits this inner menu loop
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    // Manage Orders
    private static void mngOrdr() {
        while (true) {
            System.out.println(line);
            System.out.println("|          ** Manage Orders **        |");
            System.out.println(line);
            System.out.printf("|      %-30s |%n", "1 : Update Order");
            System.out.printf("|      %-30s |%n", "2 : View All Orders");
            System.out.printf("|      %-30s |%n", "3 : Back to Main Menu");
            System.out.println(line);
            System.out.print("Enter your choice: ");

            int choice = scan.nextInt();
            clear();

            switch (choice) {
                case 1:
                    // Code to update an order
                    break;
                case 2:
                    // Code to view all orders
                    break;
                case 3:
                    // Back to main menu
                    return; // exits this inner menu loop
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    // Manage Inventories
    private static void mngInvnt() {
        while (true) {
            System.out.println(line);
            System.out.println("|       ** Modify Inventories **      |");
            System.out.println(line);
            System.out.printf("|      %-30s |%n", "1 : Add New Inventory Item");
            System.out.printf("|      %-30s |%n", "2 : Update Inventory Item");
            System.out.printf("|      %-30s |%n", "3 : Delete Inventory Item");
            System.out.printf("|      %-30s |%n", "4 : View All Inventory Items");
            System.out.printf("|      %-30s |%n", "5 : Back to Main Menu");
            System.out.println(line);
            System.out.print("Enter your choice: ");

            int choice = scan.nextInt();
            clear();

            switch (choice) {
                case 1:
                    // Code to add a new inventory item
                    break;
                case 2:
                    // Code to update an inventory item
                    break;
                case 3:
                    // Code to delete an inventory item
                    break;
                case 4:
                    // Code to view all inventory items
                    break;
                case 5:
                    // Back to main menu
                    return; // exits this inner menu loop
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}
