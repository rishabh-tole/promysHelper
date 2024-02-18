import java.util.ArrayList;
import java.util.Arrays;

public class Problem2 {

    public static void main(String[] args) {

        for (int x = 1; x < 100; x++) {
            int firstNum = x;
            double optimalSecondNum = 0;
            int maxAlts = 0;

            for (double i = -x + 0.001; i < 0; i = i + 0.001) {
                double[] a = generateCustomFibonacci(firstNum, i, 50);
                int alts = numOfAlternates(a);
                if (alts > maxAlts) {
                    maxAlts = alts;
                    optimalSecondNum = i;
                }
            }
            System.out.println("First: " + firstNum + " Second: " + optimalSecondNum + " Alternate: " + maxAlts);

            // how I first found out that it was the golden ratio!
            System.out.println(firstNum / optimalSecondNum);
        }




        // Generate a custom Fibonacci sequence with specified starting numbers and count
        double[] set = generateCustomFibonacci(55, -34, 15);
        // Count the number of alternates in the sequence
        int n = numOfAlternates(set);
        // Print the number of alternates and the generated sequence
        System.out.println(n);
        System.out.println(Arrays.toString(set));
    }

    // Method to generate a custom Fibonacci sequence
    public static double[] generateCustomFibonacci(double firstNum, double secondNum, double count) {
        ArrayList<Double> arr = new ArrayList<>();
        // Add the initial numbers to the sequence
        arr.add(firstNum);
        arr.add(secondNum);

        // Generate subsequent numbers based on the custom Fibonacci formula
        for (int i = 2; i < count; i++) {
            double nextNum = firstNum + secondNum;
            arr.add(nextNum);

            firstNum = secondNum;
            secondNum = nextNum;
        }

        // Convert the ArrayList to an array of doubles and return it
        return arr.stream().mapToDouble(Double::doubleValue).toArray();
    }

    // Method to count the number of alternates in a sequence
    public static int numOfAlternates(double[] arr) {
        int alternate = 0;

        for (int i = 0; i < arr.length; i++) {
            // Check if the index is even or odd and if the value is positive or negative
            if (i % 2 == 0) {
                if (arr[i] > 0) {
                    alternate++;
                } else {
                    return alternate;
                }
            } else {
                if (arr[i] < 0) {
                    alternate++;
                } else {
                    return alternate;
                }
            }
        }

        return alternate;
    }
}
