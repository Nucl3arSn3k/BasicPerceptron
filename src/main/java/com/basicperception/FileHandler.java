package com.basicperception;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
public class FileHandler {
    private List<String> lines;
    public  void openCSV(Path x) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(x.toFile()));
        String line = "";  
        while ((line = br.readLine()) != null){
            System.out.println(line);



        }
    }

}
