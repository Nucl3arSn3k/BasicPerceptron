package com.basicperception;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Perceptron {
    private float eta; // Learning rate
    private int n_iter;
    private ArrayList<Double> weights;
    private ArrayList<Integer> errors;

    public Perceptron(float eta, int n_iter) {
        this.eta = eta;
        this.n_iter = n_iter;

    }

    public static double dot_product(double[] x, double[] y) { // Handles dot product.
        //double re = 0.0;
        ArrayList<Double> prhold = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            double pr = x[i] * y[i];
            prhold.add(pr);
        }
        // double n = 0.0;
        double sum = 0.0;
        for (double n : prhold) { // Sum the elements
            sum += n;
        }

        return sum;
    }

    public double weighted_sum(double[] X) {
        double[] weights = new double[X.length];
        for (int i = 0; i < X.length; i++) {
            weights[i] = this.weights.get(i + 1);
        }
        return this.weights.get(0) + dot_product(X, weights);
    }



    public int[] predict(double[] X) {
        double weightedSum = weighted_sum(X);
        return new int[]{weightedSum >= 0 ? 1 : -1};
    }




    public Perceptron fit(double[][] X, int[] y) {
        int numFeatures = X[0].length;
        this.weights = new ArrayList<>(numFeatures + 1);
        for (int i = 0; i <= numFeatures; i++) {
            this.weights.add(0.0); // Initialize weights to 0
        }
        this.errors = new ArrayList<>();

        for (int i = 0; i < this.n_iter; i++) {
            int error = 0;
            for (int j = 0; j < X.length; j++) {
                double[] xi = X[j];
                int target = y[j];
                int prediction = predict(xi)[0];
                
                if (prediction != target) {
                    double update = this.eta * (target - prediction);
                    for (int k = 1; k < this.weights.size(); k++) {
                        this.weights.set(k, this.weights.get(k) + update * xi[k-1]);
                    }
                    this.weights.set(0, this.weights.get(0) + update); // Update bias
                    error++;
                }
            }
            this.errors.add(error);
        }
        return this;
    }
    public static void activate(int inputfeatures) {

    }

}
