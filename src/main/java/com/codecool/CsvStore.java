package com.codecool;

import java.io.*;
import java.util.*;

public abstract class CsvStore extends Store {

    private void saveToCsv(String filename) {
        String csvSeparator = ",";
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            for (Product product: getProducts()) {
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
