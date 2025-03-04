
/**
 * LeetCode Problem: Check If Number Is A Sum Of Powers Of Three
 *
 * Problem Description:
 * Given an integer n, determine if it can be represented as a sum of distinct powers of three.
 * A number is considered a power of three if it can be written as 3^x where x is an integer.
 *
 * Solution Approach:
 * - Convert the number to base-3 representation
 * - Check if any digit in base-3 is 2
 * - If we find a 2, return false (can't represent with distinct powers)
 * - If no 2's found, return true
 *
 * Time Complexity: O(log n) - we divide n by 3 until it becomes 0
 * Space Complexity: O(1) - only constant extra space used
 */
class Solution {

    /**
     * Checks if a number can be represented as a sum of distinct powers of
     * three
     *
     * @param n The input number to check (1 <= n <= 10^7) @ return true if the
     * number can be represented as sum of distinct powers of three, false
     * otherwise
     */
    public boolean checkPowersOfThree(int n) {
        // Convert to base-3 and check if any digit is 2
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    /**
     * Helper method to display test results
     *
     * @param testNumber The test case number
     * @param n The input number
     * @param expected The expected result
     * @param actual The actual result
     */
    private static void printTestResult(int testNumber, int n, boolean expected, boolean actual) {
        System.out.println("Test " + testNumber + ":");
        System.out.println("Input n = " + n);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        System.out.println("Result: " + (expected == actual ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: n = 12 (12 = 3^1 + 3^2)
        printTestResult(1, 12, true, solution.checkPowersOfThree(12));

        // Test Case 2: n = 91 (91 = 3^0 + 3^2 + 3^4)
        printTestResult(2, 91, true, solution.checkPowersOfThree(91));

        // Test Case 3: n = 21 (Cannot be represented as sum of distinct powers of three)
        printTestResult(3, 21, false, solution.checkPowersOfThree(21));

        // Test Case 4: n = 45 (45 = 3^2 + 3^3)
        printTestResult(4, 45, true, solution.checkPowersOfThree(45));

        // Test Case 5: n = 100 (Cannot be represented as sum of distinct powers of three)
        printTestResult(5, 100, false, solution.checkPowersOfThree(100));
    }
}
