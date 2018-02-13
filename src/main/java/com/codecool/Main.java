package com.codecool;

public class Main {

    public static void main(String[] args) {

        // xml
        PersistentStore ps = new PersistentStore();
        StoreManager manager = new StoreManager();
        manager.addStorage(ps);
        manager.addCDProduct("Metallica: Master of puppets", 1500, 10);
        manager.addBookProduct("Bölcsek Köve", 2500, 500);
        ps.store("Products.xml");
        System.out.println("Products from xml: " +  manager.listProducts());
        System.out.println("Total price: " + manager.getTotalProductPrice());

        // csv
        PersistentStoreToCsv psCsv = new PersistentStoreToCsv();
        StoreManager csvManager = new StoreManager();
        csvManager.addStorage(psCsv);
        csvManager.addCDProduct("Akkezdet Phiai: Akkezdet", 2500, 12);
        csvManager.addBookProduct("Slam.pont!", 2200, 60);
        psCsv.store("Products.csv");
        System.out.println("Products from csv: " +  csvManager.listProducts());
        System.out.println("Total price: " + csvManager.getTotalProductPrice());

    }

}
