package com.basicperception;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        //System.out.println("Hello World!");
        FileHandler fh = new FileHandler();
        Path filePath = Paths.get("src", "main", "resources", "iris.csv");

        try {
            fh.openCSV(filePath);

        } catch (IOException e) {
            System.out.println(e);

        }

    }
}
