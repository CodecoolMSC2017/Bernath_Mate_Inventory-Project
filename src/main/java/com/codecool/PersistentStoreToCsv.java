package com.codecool;

public class PersistentStoreToCsv extends CsvStore{

    public void storeProduct(Product product) {
        getProducts().add(product);
    }
}
