package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductMgmtApp {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(3128874119L, "Banana", LocalDate.parse("2023-01-24"), 124, 0.55));
        products.add(new Product(2927458265L, "Apple", LocalDate.parse("2022-12-09"), 18, 1.09));
        products.add(new Product(9189927460L, "Carrot", LocalDate.parse("2023-03-31"), 89, 2.99));

        printProducts(products);
    }


    public static void printProducts(List<Product> products) {
        // Sort products by name in ascending order
        products.sort(Comparator.comparing(Product::getName));

        // Print JSON format
        System.out.println("JSON Format:");
        printProductsAsJSON(products);

        // Print XML format
        System.out.println("\nXML Format:");
        printProductsAsXML(products);

        // Print CSV format
        System.out.println("\nCSV Format:");
        printProductsAsCSV(products);
    }

    private static void printProductsAsJSON(List<Product> products) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());  // Register Java 8 date/time module
            mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print
            String json = mapper.writeValueAsString(products);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printProductsAsXML(List<Product> products) {
        System.out.println("<Products>");
        for (Product product : products) {
            System.out.println("  <Product>");
            System.out.println("    <productId>" + product.getProductId() + "</productId>");
            System.out.println("    <name>" + product.getName() + "</name>");
            System.out.println("    <dateSupplied>" + product.getDateSupplied() + "</dateSupplied>");
            System.out.println("    <quantityInStock>" + product.getQuantityInStock() + "</quantityInStock>");
            System.out.println("    <unitPrice>" + product.getUnitPrice() + "</unitPrice>");
            System.out.println("  </Product>");
        }
        System.out.println("</Products>");
    }

    private static void printProductsAsCSV(List<Product> products) {
        System.out.println("productId,name,dateSupplied,quantityInStock,unitPrice");
        for (Product product : products) {
            System.out.printf("%s,%s,%s,%d,%.2f%n",
                    product.getProductId(),
                    product.getName(),
                    product.getDateSupplied(),
                    product.getQuantityInStock(),
                    product.getUnitPrice());
        }
    }

}