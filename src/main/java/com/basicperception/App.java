package com.basicperception;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static class Pair<T, U> {
        public final T first;
        public final U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        // System.out.println("Hello World!");
        FileHandler fh = new FileHandler();
        Path filePath = Paths.get("src", "main", "resources", "iris.csv");

        try {
            fh.open_csv(filePath);

        } catch (IOException e) {
            System.out.println(e);

        }

        List<double[]> loc_x = fh.getX();
        List<String> loc_y = fh.getY();
        Perceptron perp = new Perceptron((float) 0.1, 10);

        Pair<List<double[]>, List<double[]>> splitX = split_list(loc_x);
        Pair<List<String>, List<String>> splitY = split_list(loc_y);

        // 75% train, 25% test data

        // double[] x = {1.0,2.0,3.0};
        // double[] y = {4.0,5.0,6.0};
        // double result = perp.dotProduct(x, y);

        // System.out.println(result);
        List<Integer> train_labels = gen_stuff(splitY, true, null);
        List<Integer> test_labels = gen_stuff(splitY, false, null);

        // Length check
        //System.out.println("Printing length check");
        //System.out.println(splitY.first.size());
        //System.out.println(splitY.second.size());


        System.out.println("Printing train data details");
        // Train data

        System.out.println(train_labels.size());
        System.out.println(train_labels);
        System.out.println(splitX.second);

        // Test data
        System.out.println(test_labels.size());
        System.out.println(test_labels);
        System.out.println(splitX.first);


        perp.fit(splitX.second, train_labels);

        List<Integer> result = perp.predict_batch(splitX.first);

        System.out.println(result);

    }

    public static <T> Pair<List<T>, List<T>> split_list(List<T> orig_list) {
        int splitIndex = orig_list.size() / 4; // 25% of the list size
        List<T> firstQuarter = new ArrayList<>(orig_list.subList(0, splitIndex));
        List<T> remainingThreeQuarters = new ArrayList<>(orig_list.subList(splitIndex, orig_list.size()));

        return new Pair<>(firstQuarter, remainingThreeQuarters);
    }

    public static <T, U> List<Integer> gen_stuff(Pair<List<T>, List<U>> input, Boolean state, String filter) { 
        List<Integer> loc_labels = new ArrayList<>();
        if (state == true) {
            for (int x = 0; x < input.second.size(); x++) { // Works on the 75%
                if (input.second.get(x).equals("Iris-setosa")) {
                    loc_labels.add(1);
                } else {
                    loc_labels.add(-1);
                }
            }

        } else {
            for (int x = 0; x < input.first.size(); x++) { // Works on the 25%
                if (input.first.get(x).equals("Iris-setosa")) {
                    loc_labels.add(1);
                } else {
                    loc_labels.add(-1);
                }
            }

        }

        return loc_labels;
    }
}
