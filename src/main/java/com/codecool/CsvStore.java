package com.codecool;

import java.io.*;
import java.util.*;

public abstract class CsvStore implements StorageCapable{

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    protected abstract void storeProduct(Product product);

    @Override
    public List<Product> getAllProduct() {
        return products;
    }

    @Override
    public void storeCDProduct(String name, int price, int tracks) {
        storeProduct(createProduct("cd", name, price, tracks));
    }

    @Override
    public void storeBookProduct(String name, int price, int pages) {
        storeProduct(createProduct("book", name, price, pages));
    }

    protected Product createProduct(String type, String name, int price, int size) {
        Product product = null;
        try {
            if (type.toLowerCase().equals("book")) {
                product = new BookProduct(name, price, size);
            } else if (type.toLowerCase().equals("cd")) {
                product = new CDProduct(name, price, size);
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            System.out.println("Not valid type! (Try cd or book!)");
        }
        return product;
    }

    private void saveToCsv(String filename) {
        String csvSeparator = ",";
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            StringBuffer header = new StringBuffer();
            header.append("Name,");
            header.append("Price,");
            header.append("Tracks/Pages");
            bw.write(header.toString());
            bw.newLine();
            for (Product product: products) {
                StringBuffer oneLine = new StringBuffer();
                if (product instanceof CDProduct) {
                    oneLine.append(product.getName());
                    oneLine.append(csvSeparator);
                    oneLine.append(product.getPrice());
                    oneLine.append(csvSeparator);
                    oneLine.append(((CDProduct) product).getNumOfTracks());

                } else if (product instanceof  BookProduct) {
                    oneLine.append(product.getName());
                    oneLine.append(csvSeparator);
                    oneLine.append(product.getPrice());
                    oneLine.append(csvSeparator);
                    oneLine.append(((BookProduct) product).getNumOfPage());
                }
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void store(String filename) {
        saveToCsv(filename);
    }
}
