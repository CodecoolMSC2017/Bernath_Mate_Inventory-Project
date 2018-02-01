package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    PersistentStore ps;
    List<Product> testProducts;

    @BeforeEach
    void setUp() {
        ps =  new PersistentStore();
        testProducts = ps.loadProducts("Test.xml");
    }

    @Test
    void getAllProduct() {
        assertEquals(ps.getAllProduct().size(), ps.getProducts().size());
    }

    @Test
    void storeCDProduct() {
        boolean isIn = false;

        ps.storeCDProduct("Akkezdet", 2600, 13);

        for(int i = 0; i < ps.getProducts().size(); i++) {
            if (ps.getProducts().get(i).getName().equals("Akkezdet")) {
                isIn = true;
            }
        }

        assertTrue(isIn);
    }

    @Test
    void storeBookProduct() {
        boolean isIn = false;

        ps.storeBookProduct("Slam.pont", 2400, 56);

        for(int i = 0; i < ps.getProducts().size(); i++) {
            if (ps.getProducts().get(i).getName().equals("Slam.pont")) {
                isIn = true;
            }
        }

        assertTrue(isIn);
    }

    @Test
    void loadProducts() {
        boolean increased = false;
        int listSizeBeforeLoad = ps.getProducts().size();
        ps.loadProducts("Products.xml");

        if(ps.getProducts().size() > listSizeBeforeLoad) {
            increased = true;
        }

        assertTrue(increased);
    }

    @Test
    void testLoadEquals() {
        ps.loadProducts("Test.xml");
        assertEquals(testProducts.size(), ps.getProducts().size());
        assertEquals("Egyjólemez", testProducts.get(0).getName());
    }

    @Test
    void testLoadProductsExceptions() {
        boolean thrown = false;

        try {
            ps.loadProducts("Products.xml");
        } catch (Exception e) {
            thrown = true;
        }

        assertFalse(thrown);
    }


    @Test
    void store() {
        boolean isEquals = false;
        ps.storeCDProduct("Egyjólemez", 2400, 10);
        ps.store("Test.xml");
        testProducts = ps.loadProducts("Test.xml");

        if(testProducts == ps.getProducts()) {
            isEquals = true;
        }

        assertTrue(isEquals);
    }
}