package com.me;

public class Main {

    private double[] a = {9, 7, 8, 4, 6};

    public static void main(String[] args) {
        Main m = new Main();

        System.out.println(m.findLowestClosingStockPrice());
    }

    public double findLowestClosingStockPrice() {
        double low =0;
        if (a != null) {
            low = a[0];
            for (Double s : a) {
                if (s < low) {
                    low = s;
                }
            }
        }
        return low;
    }
}


