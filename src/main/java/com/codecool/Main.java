package com.codecool;

public class Main {

    public static void main(String[] args) {

        PersistentStore ps = new PersistentStore();
        PersistentStoreToCsv psCsv = new PersistentStoreToCsv();
        StoreManager manager = new StoreManager();
        manager.addStorage(psCsv);
        manager.addStorage(ps);
        manager.addCDProduct("Metallica: Master of puppets", 1500, 10);
        manager.addCDProduct("Akkezdet Phiai: Akkezdet", 2500, 12);
        manager.addBookProduct("Bölcsek Köve", 2500, 500);
        manager.addBookProduct("Slam.pont!", 2200, 60);
        psCsv.store("Products.csv");
        ps.store("Products.xml");
        ps.loadProducts("Products.xml");
        System.out.println(manager.listProducts());
        System.out.println(manager.getTotalProductPrice());

    }

}
