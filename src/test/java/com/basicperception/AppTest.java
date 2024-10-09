package com.basicperception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testApp() {
        assertTrue(true, "This test should always pass");
    }
   
    @Test
    public void testDotProduct() {
        Perceptron perp = new Perceptron(0.1f, 10);
        double[] X = {1.0, 2.0, 3.0};
        double[] Y = {4.0, 5.0, 6.0};
       
        double expected = 32.0; // 1*4 + 2*5 + 3*6 = 4 + 10 + 18 = 32
        double actual = perp.dot_product(X, Y);
       
        assertEquals(expected, actual, 0.0001, "The dot product should be 32.0");
    }
}