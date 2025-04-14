/**
 * LeetCode Problem 1534: Count Good Triplets
 * 
 * Problem Description:
 * Given an array of integers arr, and three integers a, b and c.
 * A triplet (arr[i], arr[j], arr[k]) is called a "good triplet" if the following conditions are true:
 * 1. 0 <= i < j < k < arr.length
 * 2. |arr[i] - arr[j]| <= a
 * 3. |arr[j] - arr[k]| <= b
 * 4. |arr[i] - arr[k]| <= c
 * 
 * Where |x| denotes the absolute value of x.
 * 
 * Return the number of good triplets.
 * 
 * Time Complexity: O(n³) where n is the length of the array
 * Space Complexity: O(n²) for the precomputed differences matrix
 */
public class Solution {
    /**
     * Counts the number of "good triplets" in the given array based on the specified constraints.
     * 
     * @param arr The input array of integers
     * @param a   Maximum allowed absolute difference between arr[i] and arr[j]
     * @param b   Maximum allowed absolute difference between arr[j] and arr[k]
     * @param c   Maximum allowed absolute difference between arr[i] and arr[k]
     * @return    The number of good triplets
     */
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        int n = arr.length;
        boolean checkC = (a + b) > c;
        int[][] diff = new int[n][n];
        
        // Precompute absolute differences between all pairs for efficiency
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                diff[i][j] = Math.abs(arr[i] - arr[j]);
            }
        }
        
        // Find all triplets (i,j,k) where 0 <= i < j < k < n
        for (int i = 0; i < n; ++i) {
            for (int k = i + 2; k < n; ++k) {
                // Optimization: If |arr[i] - arr[k]| > c, no need to check j values in between
                if (checkC && diff[i][k] > c) {
                    continue;
                }
                for (int j = i + 1; j < k; ++j) {
                    // Check if the triplet satisfies the conditions
                    if (diff[i][j] <= a && diff[j][k] <= b && diff[i][k] <= c) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    /**
     * Main method to run test cases for the countGoodTriplets function.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Case 1
        int[] arr1 = {3, 0, 1, 1, 9, 7};
        int a1 = 7, b1 = 2, c1 = 3;
        int result1 = solution.countGoodTriplets(arr1, a1, b1, c1);
        printTestResult(1, arr1, a1, b1, c1, result1, 4);
        
        // Test Case 2
        int[] arr2 = {1, 1, 2, 2, 3};
        int a2 = 0, b2 = 0, c2 = 1;
        int result2 = solution.countGoodTriplets(arr2, a2, b2, c2);
        printTestResult(2, arr2, a2, b2, c2, result2, 0);
        
        // Test Case 3
        int[] arr3 = {7, 3, 7, 3, 12, 1, 12, 2, 3};
        int a3 = 5, b3 = 8, c3 = 1;
        int result3 = solution.countGoodTriplets(arr3, a3, b3, c3);
        printTestResult(3, arr3, a3, b3, c3, result3, 12);
    }
    
    /**
     * Helper method to print test results in a formatted way.
     * 
     * @param testNumber The test case number
     * @param arr        The input array
     * @param a          Parameter a
     * @param b          Parameter b
     * @param c          Parameter c
     * @param result     The actual result
     * @param expected   The expected result
     */
    private static void printTestResult(int testNumber, int[] arr, int a, int b, int c, int result, int expected) {
        System.out.println("=".repeat(60));
        System.out.println("Test Case " + testNumber + ":");
        System.out.print("Array: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("Parameters: a=" + a + ", b=" + b + ", c=" + c);
        System.out.println("Expected Result: " + expected);
        System.out.println("Actual Result: " + result);
        System.out.println("Status: " + (result == expected ? "PASSED ✓" : "FAILED ✗"));
        System.out.println("=".repeat(60));
        System.out.println();
    }
}
