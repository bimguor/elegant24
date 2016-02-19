package com.ccsi;

public class Main {

    static double[] origInputs;
    static double result = 24;
    static Step[] steps;
    static char[] ops = {'+', '-', '*', '/'};
    static boolean solutionFound = false;
    static boolean done = false;
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
    }
    private static void solve(double[] inputs) {
        if (done) return;
        int stepCount = origInputs.length - inputs.length;
        if (inputs.length == 1) {
            if (inputs[0] == result) {
                solutionFound = true;
                print();
                done = true;
            }
            return;
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
}

