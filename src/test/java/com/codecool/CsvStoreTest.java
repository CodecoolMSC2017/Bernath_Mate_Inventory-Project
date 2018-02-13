package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CsvStoreTest {

    private PersistentStoreToCsv psCsv;
    private BufferedReader br;

    @BeforeEach
    void setUp() {
        psCsv = new PersistentStoreToCsv();
    }


    @Test
    void createProduct() {
        CDProduct cd;

        cd = (CDProduct) psCsv.createProduct("cd", "Kottazűr", 2670, 18);

        assertNotEquals(null, cd);
        assertEquals("Kottazűr", cd.getName());
        assertEquals(2670, cd.getPrice());
        assertEquals(18, cd.getNumOfTracks());
    }

    @Test
    void store() throws IOException {
        CDProduct cd = new CDProduct("mixtape", 1000, 20);
        psCsv.storeProduct(cd);

        psCsv.store("TestProducts.csv");
        br = new BufferedReader(new FileReader("TestProducts.csv"));

        assertNotEquals(null, br.readLine());
        assertEquals("mixtape,1000,20", br.readLine());
    }
}