package com.pluralsight;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class StoreApp {
    public static void main(String[] args) {
        System.out.println("=== Welcome to the product inventory ===");
        runing();
    }

    public static void runing() {
        while (true) {
            menu();
            menuSelector();
        }
    }

    public static void menu() {
        System.out.println("What do you want to do?\n" +
                "  1- List all products\n" +
                "  2- Lookup a product by its id\n" +
                "  3- Find all products within a price range\n" +
                "  4- Add a new product\n" +
                "  5- Quit the application\n");
    }

    public static void menuSelector() {
        ArrayList<Product> inventory = getInventory();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.println();
                viewAll(inventory);
                break;
            case 2:
                System.out.print("ID to search: ");
                String searchProductByID = scanner.nextLine().trim();
                //searchProductByID(inventory, id)
                break;
            case 3:
                //searchProductByPriceRange(inventory, startRange, endRange)
                break;
            case 4:
                //inventory = addProduct(inventory)
                break;
            case 5:
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect option entered, try again");
        }

        System.out.println("\nPress ENTER to continue...\n");
        scanner.nextLine();

    }

    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>();
//        ArrayList<Product> inventory = new ArrayList<Product>();
//        inventory.add(new Product(1, "Marmelade", 3.99f));
//        inventory.add(new Product(2, "Peanut Butter", 4.49f));
//        inventory.add(new Product(3, "Strawberry Jam", 3.49f));
//        inventory.add(new Product(4, "Honey", 5.99f));
//        inventory.add(new Product(5, "Nutella", 6.99f));

        try {
            FileReader fileReader = new FileReader("src/main/resources/DataFiles/inventory.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            //ArrayList<Product> inventory = getInventory();
            String input;
            while ((input = bufReader.readLine()) != null) {
                String[] tokens = input.split("\\|");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                float price = Float.parseFloat(tokens[2]);
                inventory.add(new Product(id, name, price));

//            ArrayList<Product> inventory = getInventory();
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("We carry the following inventory: ");
//            for (int i = 0; i < inventory.size(); i++) {
//                Product p = inventory.get(i);
//                System.out.printf("id: %d \n %s - Price: $%.2f \n",
//                        p.getId(), p.getName(), p.getPrice());
            }
            bufReader.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return inventory;
    }
    public static void viewAll(ArrayList<Product> inventory) {
        for (Product inventoryItem : inventory) {
            displayProduct(inventoryItem);
        }
    }
    public static void displayProduct(Product p) {
        System.out.printf("Product: %d\n  Name: %s\n  Price: $%.2f\n\n", p.getId(), p.getName(), p.getPrice());
    }
}
