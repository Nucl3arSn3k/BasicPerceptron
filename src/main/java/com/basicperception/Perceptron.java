package com.basicperception;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
public class Perceptron {
    private float eta; // Learning rate
    private int n_iter; //Epochs needed
    private ArrayList<Double> weights; //Weight holder
    private ArrayList<Integer> errors;

    public Perceptron(float eta, int n_iter) { //Constructor details
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

    public double weighted_sum(double[] X) { //Weighted sum equation
        double[] weights = new double[X.length];
        for (int i = 0; i < X.length; i++) {
            weights[i] = this.weights.get(i + 1);
        }
        return this.weights.get(0) + dot_product(X, weights);
    }



    public int predict(double[] X) { //calc weighted sum then apply activation function
        double weightedSum = weighted_sum(X);
        return weightedSum >= 0 ? 1 : -1;
    }


    public List<Integer> predict_batch(List<double[]> X) { //batch prediction method
        List<Integer> val = new ArrayList<Integer>(X.size());
        for (int i = 0;i < X.size();i++){
            double weightedSum = weighted_sum(X.get(i));
            int var = weightedSum >= 0 ? 1 : -1;
            val.add(var);
        }
        
        return val;
    }

    //public int batchpredictions(int[]X){}




    public Perceptron fit(List<double[]> x, List<Integer> y) { //Starts training and chaging the weights
        if (x.isEmpty() || y.isEmpty() || x.size() != y.size()) { //Input checking
            throw new IllegalArgumentException("Input lists must be non-empty and of equal size");
        }
    
        int numFeatures = x.get(0).length;
        this.weights = new ArrayList<>(numFeatures + 1);
        for (int i = 0; i <= numFeatures; i++) {
            this.weights.add(0.0); // Initialize weights to 0
        }
    
        this.errors = new ArrayList<>();
    
        for (int i = 0; i < this.n_iter; i++) {//n_iter is the number of epochs
            int error = 0;
            for (int j = 0; j < x.size(); j++) {//predict outputs and update weights if incorrect
                double[] xi = x.get(j);
                int target = y.get(j);
                int prediction = predict(xi);
               
                if (prediction != target) { //Use percerptron learning rule to update weights
                    double update = this.eta * (target - prediction);
                    for (int k = 1; k < this.weights.size(); k++) {
                        this.weights.set(k, this.weights.get(k) + update * xi[k-1]);
                    }
                    this.weights.set(0, this.weights.get(0) + update); // Update bias
                    error++; //error tracking stuff
                }
            }
            this.errors.add(error);
        }
        return this;
    }
    public static void activate(int inputfeatures) {

    }

}
