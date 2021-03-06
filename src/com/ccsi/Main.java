package com.ccsi;

public class Main {

    static double[] origInputs;
    static double result = 24;
    static Step[] steps;
    static char[] ops = {'+', '-', '*', '/'};
    static boolean solutionFound = false;
    public static void main(String[] args) {

        parse(args);
        steps = new Step[origInputs.length - 1];
        for (int i = 0; i < steps.length; i++) {
            steps[i] = new Step();
        }
        solve(origInputs);
        if (!solutionFound) {
            System.out.println("SOLUTION NOT FOUND :/\n");
        }

        //now check all numbers
        checkNumbers();
    }
    private static void solve(double[] inputs) {
        if (solutionFound) return;
        int stepCount = origInputs.length - inputs.length;
        if (inputs.length == 1) {
            if (inputs[0] == result) {
                solutionFound = true;
                print();
            }
        } else {
            for (int i = 0; i < inputs.length - 1; i++) {
                for (int j = i + 1; j < inputs.length; j++) {
                    for (int op = 0; op < 6; op++) {
                        double r = 0;
                        steps[stepCount].num1 = inputs[i];
                        steps[stepCount].num2 = inputs[j];
                        steps[stepCount].op = op;
                        switch (op) {
                            case 0:
                                r = inputs[i] + inputs[j];
                                break;
                            case 1:
                                r = inputs[i] - inputs[j];
                                break;
                            case 2:
                                r = inputs[i] * inputs[j];
                                break;
                            case 3:
                                r = inputs[i] / inputs[j];
                                break;
                            case 4:
                                r = inputs[j] - inputs[i];
                                break;
                            case 5:
                                r = inputs[j] / inputs[i];
                                break;
                        }
                        steps[stepCount].result = r;
                        double [] lessInputs = new double[inputs.length - 1];
                        lessInputs[0] = r;
                        int lessInputIndex = 1;
                        for (int k = 0; k < inputs.length; k++) {
                            if (k != i && k != j) {
                                lessInputs[lessInputIndex] = inputs[k];
                                lessInputIndex++;
                            }
                        }
                        solve(lessInputs);
                    }
                }
            }
        }
    }
    private static void parse(String[] args) {
        origInputs = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            origInputs[i] = Double.parseDouble(args[i]);
        }
    }
    private static void print() {
        for (int i = 0; i < steps.length; i++) {
            switch (steps[i].op) {
                case 0:
                    System.out.printf("%f + %f = %f\n", steps[i].num1, steps[i].num2, steps[i].result);
                    break;
                case 1:
                    System.out.printf("%f - %f = %f\n", steps[i].num1, steps[i].num2, steps[i].result);
                    break;
                case 2:
                    System.out.printf("%f * %f = %f\n", steps[i].num1, steps[i].num2, steps[i].result);
                    break;
                case 3:
                    System.out.printf("%f / %f = %f\n", steps[i].num1, steps[i].num2, steps[i].result);
                    break;
                case 4:
                    System.out.printf("%f - %f = %f\n", steps[i].num2, steps[i].num1, steps[i].result);
                    break;
                case 5:
                    System.out.printf("%f / %f = %f\n", steps[i].num2, steps[i].num1, steps[i].result);
                    break;
            }
        }
        System.out.println("solution found!");
    }
    private static class Step {
        public double num1;
        public double num2;
        public int op;
        public double result;
        public Step() {
            num1 = 0;
            num2 = 0;
            op = 0;
        }
    }
    private static boolean solve(int a, int b, int c, int d) {
        double[] inputs = new double[4];
        inputs[0] = a;
        inputs[1] = b;
        inputs[2] = c;
        inputs[3] = d;
        solutionFound = false;
        solve(inputs);
        return solutionFound;
    }
    private static void checkNumbers() {
        int a, b, c, d;
        int unsolvable = 0;
        for (a=1; a < 14; a++) {
            for (b=1; b<14; b++) {
                for (c=1; c<14; c++) {
                    for (d=1; d<14; d++) {
                        if (!solve(a, b, c, d)) {
                            unsolvable++;
                            System.out.printf("The numbers, %d, %d, %d, %d don't work.\n", a, b, c, d);
                        }
                    }
                }
            }
        }
        int allCount = 13 * 13 * 13 * 13;

        System.out.printf("There are %d unsolvable, which is %f%% out of %d all possible sets\n", unsolvable,
                ((double)unsolvable/(double)allCount * (double)100), allCount);
    }
}

