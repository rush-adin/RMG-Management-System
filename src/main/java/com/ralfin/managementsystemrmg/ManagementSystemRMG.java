//package com.ralfin.managementsystemrmg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.ListIterator;

class Garment {

    public String id;
    public String name;
    public String description;
    public String size;
    public String color;
    public double price;
    public int stockQuantity;

    Garment(String id, String name, String description, String size, String color, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
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

    Scanner scan = new Scanner(System.in);

    void addGarment() {
        if (!garments.isEmpty())
            scan.nextLine();
        String id, name, description, size, color;
        double price;
        int quantity;
        System.out.print("Enter Garment's id: ");
        id = scan.nextLine();
        System.out.print("Enter Garment's name: ");
        name = scan.nextLine();
        System.out.print("Enter Short description: ");
        description = scan.nextLine();
        System.out.print("Enter Size: ");
        size = scan.nextLine();
        System.out.print("Enter Color: ");
        color = scan.nextLine();
        System.out.print("Enter Price: ");
        price = scan.nextDouble();
        System.out.print("Enter Quantity: ");
        quantity = scan.nextInt();
        Garment garment = new Garment(id, name, description, size, color, price, quantity);
        garments.add(garment);
        System.out.println();
        System.out.println("** New Garment added to the inventory **");
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }

    void updateGarment() {
        if (!garments.isEmpty())
            scan.nextLine(); // problematic
        System.out.println("Enter the garment ID: ");
        String id = scan.nextLine();
        for (Garment garment : garments) {
            if (garment.id.equals(id)) {
                System.out.println("Updated Price: ");
                garment.price = scan.nextDouble();
                System.out.println("Updated Stock: ");
                garment.stockQuantity = scan.nextInt();
                int index = garments.indexOf(garment);
                System.out.println("** Garment updated successfully **");
                System.out.println("Press Enter to continue...");
                Use.pause();
                Use.clear();
                return;
            }
        }
        System.out.println("Item not found!");
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }

    void removeGarment() {
        if (!garments.isEmpty())
            scan.nextLine();
        System.out.println("Enter garment ID: ");
        String id = scan.nextLine();
        ListIterator<Garment> it = garments.listIterator();
        int flag = 1;
        while (it.hasNext()) {
            if (it.next().id.equals(id)) {
                it.remove();
                System.out.println("** Garment removed **");
                flag = 0;
                break;
            }
        }
        if (flag == 1) {
            System.out.println("** Item not found **");
        }
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }

    void display() {
        if (garments.isEmpty()) {
            System.out.println("No garments found.");
            for (Garment garment : garments) {
                System.out.println("ID: " + garment.id);
                System.out.println("Name: " + garment.name);
                System.out.println(garment.description);
                System.out.println("Size: " + garment.size);
                System.out.println("Color: " + garment.color);
                System.out.println("Price: " + garment.price);
                System.out.println("Stocks remaining: " + garment.stockQuantity);
                System.out.println();
            }
            System.out.println("Press Enter to continue...");
            Use.pause();
            Use.clear();
        }
    }

    void addSupplier() {
        String id, name, contactInfo;
        System.out.print("Enter Supplier's id: ");
        id = scan.nextLine();
        System.out.print("Enter Supplier's name: ");
        name = scan.nextLine();
        System.out.print("Enter Supplier's contact info: ");
        contactInfo = scan.nextLine();

        Supplier supplier = new Supplier(id, name, contactInfo);
        suppliers.add(supplier);

        System.out.println("\n** New Supplier added to the list **");
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }

    void updateSupplier() {
        System.out.println("Enter the supplier ID: ");
        String id = scan.nextLine();
        for (Supplier supplier : suppliers) {
            if (supplier.id.equals(id)) {
                System.out.println("Updated Name: ");
                supplier.name = scan.nextLine();
                System.out.println("Updated Contact Info: ");
                supplier.contactInfo = scan.nextLine();

                System.out.println("** Supplier updated successfully **");
                System.out.println("Press Enter to continue...");
                Use.pause();
                Use.clear();
                return;
            }
        }
        System.out.println("Item not found!");
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }

    void removeSupplier() {
        System.out.println("Enter supplier ID: ");
        String id = scan.nextLine();
        boolean flag = true;
        ListIterator<Supplier> it = suppliers.listIterator();
        while (it.hasNext()) {
            if (it.next().id.equals(id)) {
                it.remove();
                System.out.println("** Supplier removed **");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("** Item not found **");
        }
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }

    void displaySuppliers() {
        if (suppliers.isEmpty()) {
            System.out.println("No supplier found.");
            for (Supplier supplier : suppliers) {
                System.out.println("ID: " + supplier.id);
                System.out.println("Name: " + supplier.name);
                System.out.println("Contact Info: " + supplier.contactInfo);
                System.out.println();
            }
            System.out.println("Press Enter to continue...");
            Use.pause();
            Use.clear();
        }
    }

    void addCust() {
        System.out.print("Enter Customer's ID: ");
        String id = scan.nextLine();
        System.out.print("Enter Customer's Name: ");
        String name = scan.nextLine();
        System.out.print("Enter Customer's Email: ");
        String email = scan.nextLine();
        System.out.print("Enter Customer's Phone: ");
        String phone = scan.nextLine();

        Customer customer = new Customer(id, name, email, phone);
        customers.add(customer);
        System.out.println();

        System.out.println("** New Customer added **");
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }

    void updateCust() {
        System.out.print("Enter Customer ID to update: ");
        String id = scan.nextLine();

        for (Customer customer : customers) {
            if (customer.customerId.equals(id)) {
                System.out.print("Enter Updated Name: ");
                customer.name = scan.nextLine();
                System.out.print("Enter Updated Email: ");
                customer.email = scan.nextLine();
                System.out.print("Enter Updated Phone: ");
                customer.phone = scan.nextLine();
                System.out.println();
                System.out.println("** Customer updated successfully **");
                System.out.println("Press Enter to continue...");
                Use.pause();
                Use.clear();
                return;
            }
        }
        System.out.println("** Customer not found **");
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }

    void deleteCust() {
        System.out.print("Enter Customer ID to delete: ");
        String id = scan.nextLine();
        ListIterator<Customer> it = customers.listIterator();
        boolean flag = true;
        System.out.println();
        while (it.hasNext()) {
            if (it.next().customerId.equals(id)) {
                it.remove();
                System.out.println("** Customer removed **");
                flag = false;
                break;
            }
        }
        if (flag)
            System.out.println("** Customer not found **");
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }

    void viewCust() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println("ID: " + customer.customerId);
                System.out.println("Name: " + customer.name);
                System.out.println("Email: " + customer.email);
                System.out.println("Phone: " + customer.phone);
                System.out.println();
            }
        }
        System.out.println("Press Enter to continue...");
        Use.pause();
        Use.clear();
    }
}

class Use {

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pause() {
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("Exception occured!");
        }
    }
}

public class ManagementSystemRMG {

    private static String line = "-".repeat(39);
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Inventory inventory = new Inventory();

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
            scan.nextLine();
            Use.clear();

            switch (choice) {
                case 1: {
                    mngGar(inventory);
                    break;
                }

                case 2: {
                    mngSup(inventory);
                    break;
                }

                case 3: {
                    mngCust(inventory);
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
                    System.exit(0); // Exit the program
                    break;

                default:
                    System.out.println("Invalid option. Please choose a valid number.");
            }

        }

    }

    // Manage Garments
    private static void mngGar(Inventory inventory) {
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
            scan.nextLine();
            Use.clear();

            switch (choice) {
                case 1: {
                    inventory.addGarment();
                    break;
                }
                case 2: {
                    inventory.updateGarment();
                    break;
                }
                case 3: {
                    inventory.removeGarment();
                    break;
                }
                case 4: {
                    inventory.display();
                    break;
                }
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    // Manage Suppliers
    private static void mngSup(Inventory inventory) {
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
            Use.clear();

            switch (choice) {
                case 1: {
                    inventory.addSupplier();
                    break;
                }
                case 2: {
                    inventory.updateSupplier();
                    break;
                }
                case 3: {
                    inventory.removeSupplier();
                    break;
                }
                case 4: {
                    inventory.displaySuppliers();
                    break;
                }
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    // Manage Customers
    private static void mngCust(Inventory inventory) {
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
            Use.clear();

            switch (choice) {
                case 1: {
                    inventory.addCust();
                    break;
                }
                case 2: {
                    inventory.updateCust();
                    break;
                }
                case 3: {
                    inventory.deleteCust();
                    break;
                }
                case 4: {
                    inventory.viewCust();
                    break;
                }
                case 5:
                    return;
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
            Use.clear();

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
            Use.clear();

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
