package com.basicperception;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private List<double[]> X; // Features
    private List<String> y;   // Labels

    public void open_csv(Path x) throws IOException {
        X = new ArrayList<>();
        y = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(x.toFile()))) {
            CSVFormat format = CSVFormat.Builder.create()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();

            try (CSVParser csvParser = new CSVParser(br, format)) {
                for (CSVRecord r : csvParser) {
                    double[] features = new double[4];
                    for (int i = 0; i < 4; i++) {
                        features[i] = Double.parseDouble(r.get(i));
                    }
                    X.add(features);
                    y.add(r.get(4)); // Assuming the label is in the 5th column
                }
            }
        }

        // Print some information about the parsed data
        System.out.println("Number of samples: " + X.size());
        System.out.println("Number of features per sample: " + X.get(0).length);
        System.out.println("Number of labels: " + y.size());
        for (int i = 0;i < Math.min(5,X.size());i++){
            System.out.println(Arrays.toString(X.get(i)));
        }
    }

    // Getter methods for X and y
    public List<double[]> getX() {
        return X;
    }

    public List<String> getY() {
        return y;
    }
}