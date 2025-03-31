
import java.util.Arrays;

/**
 * Put Marbles in Bags - Problem Solution
 *
 * Problem Description: You have n marbles and k bags. Each marble has a weight,
 * and we need to distribute these marbles into k bags. The score of a bag
 * distribution is determined by the sum of weights of the first and last
 * marbles in each bag. This program calculates the difference between the
 * maximum and minimum possible scores.
 *
 * Time Complexity: O(n log n) where n is the length of weights array Space
 * Complexity: O(n) for storing the splits array
 */
class Solution {

    /**
     * Calculates the difference between maximum and minimum scores possible
     * when distributing marbles into bags.
     *
     * @param weights Array containing weights of marbles
     * @param k Number of bags to distribute marbles into
     * @return The difference between maximum and minimum possible scores
     */
    public long putMarbles(int[] weights, int k) {
        if (k == 1) {
            return 0;
        }
        int n = weights.length;
        int[] splits = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            splits[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(splits);
        int splitNeeded = k - 1;
        long maxSum = 0;
        long minSum = 0;
        for (int i = 0; i < splitNeeded; i++) {
            minSum += splits[i];
            maxSum += splits[splits.length - 1 - i];
        }
        return maxSum - minSum;
    }

    /**
     * Main method containing test cases to demonstrate the functionality.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case
        int[] weights1 = {1, 3, 5, 1};
        int k1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Weights: " + Arrays.toString(weights1));
        System.out.println("K: " + k1);
        System.out.println("Result: " + solution.putMarbles(weights1, k1));
        System.out.println();

        // Test Case 2: Single bag case
        int[] weights2 = {1, 2, 3, 4, 5};
        int k2 = 1;
        System.out.println("Test Case 2:");
        System.out.println("Weights: " + Arrays.toString(weights2));
        System.out.println("K: " + k2);
        System.out.println("Result: " + solution.putMarbles(weights2, k2));
        System.out.println();

        // Test Case 3: Larger numbers
        int[] weights3 = {10, 20, 30, 40, 50};
        int k3 = 3;
        System.out.println("Test Case 3:");
        System.out.println("Weights: " + Arrays.toString(weights3));
        System.out.println("K: " + k3);
        System.out.println("Result: " + solution.putMarbles(weights3, k3));
    }
}
