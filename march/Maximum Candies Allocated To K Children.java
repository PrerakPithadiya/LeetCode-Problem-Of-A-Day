
/**
 * Maximum Candies Allocated to K Children
 *
 * Problem Description:
 * Given an array of candy piles and a number of children k, find the maximum number of candies
 * that can be allocated to each child such that:
 * 1. Each pile can be divided into any number of sub-piles (but cannot be merged with other piles)
 * 2. Each child receives exactly the same number of candies
 * 3. Each child can only receive candies from one pile
 *
 * Algorithm:
 * - Use binary search to find the maximum possible allocation
 * - For each candidate allocation size, check if it's possible to serve all k children
 * - The search space is from 1 to (sum of all candies / k)
 *
 * Time Complexity: O(n * log(M)) where:
 *   - n is the number of candy piles
 *   - M is the maximum possible allocation (min of max pile size and sum/k)
 *
 * Space Complexity: O(1) - constant extra space
 */
class MaximumCandies {

    /**
     * Finds the maximum number of candies that can be allocated to each of k
     * children.
     *
     * @param candies Array representing different piles of candies
     * @param k Number of children to allocate candies to
     * @return Maximum number of candies each child can receive
     */
    public static int maximumCandies(int[] candies, long k) {
        // Calculate the total number of candies available
        long sum = 0;
        int maxCandy = 0;
        for (int candy : candies) {
            sum += candy;
            maxCandy = Math.max(maxCandy, candy);
        }

        // If total candies is less than number of children, return 0
        if (sum < k) {
            return 0;
        }

        // Binary search for the maximum allocation
        int left = 1;
        // Upper bound is either the maximum pile or the average (sum/k), whichever is smaller
        int right = (int) Math.min(maxCandy, sum / k);

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canDistribute(candies, k, mid)) {
                // If we can distribute mid candies to each child, try for more
                left = mid + 1;
            } else {
                // Cannot distribute mid candies to each child, try with fewer
                right = mid - 1;
            }
        }

        return right; // The maximum possible allocation
    }

    /**
     * Helper method to determine if we can distribute exactly candiesPerChild
     * candies to each of k children.
     *
     * @param candies Array of candy piles
     * @param k Number of children
     * @param candiesPerChild Number of candies to give each child
     * @return true if the distribution is possible, false otherwise
     */
    private static boolean canDistribute(int[] candies, long k, int candiesPerChild) {
        if (candiesPerChild == 0) {
            return true; // Edge case: 0 candies per child is always possible
        }

        long childrenCount = 0;

        for (int pile : candies) {
            // Calculate how many children can be served from this pile
            childrenCount += pile / candiesPerChild;

            if (childrenCount >= k) {
                return true; // We can serve at least k children
            }
        }

        return false; // Cannot serve k children
    }

    /**
     * Main method with test cases.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Test Case 1 from the problem statement
        int[] candies1 = {5, 8, 6};
        long k1 = 3;
        int expected1 = 5;
        int result1 = maximumCandies(candies1, k1);
        printTestResult(1, candies1, k1, expected1, result1);

        // Test Case 2 from the problem statement
        int[] candies2 = {2, 5};
        long k2 = 11;
        int expected2 = 0;
        int result2 = maximumCandies(candies2, k2);
        printTestResult(2, candies2, k2, expected2, result2);

        // Additional Test Case 3: Edge case with single pile
        int[] candies3 = {10};
        long k3 = 5;
        int expected3 = 2;
        int result3 = maximumCandies(candies3, k3);
        printTestResult(3, candies3, k3, expected3, result3);

        // Additional Test Case 4: Large numbers
        int[] candies4 = {1000000, 1000000, 1000000};
        long k4 = 5;
        int expected4 = 600000;
        int result4 = maximumCandies(candies4, k4);
        printTestResult(4, candies4, k4, expected4, result4);

        // Additional Test Case 5: Same pile sizes
        int[] candies5 = {4, 4, 4};
        long k5 = 4;
        int expected5 = 3;
        int result5 = maximumCandies(candies5, k5);
        printTestResult(5, candies5, k5, expected5, result5);
    }

    /**
     * Helper method to print test case results in a structured format.
     *
     * @param testNumber The test case number
     * @param candies The array of candy piles
     * @param k The number of children
     * @param expected The expected result
     * @param actual The actual result from our algorithm
     */
    private static void printTestResult(int testNumber, int[] candies, long k, int expected, int actual) {
        System.out.println("Test Case " + testNumber + ":");
        System.out.print("Candies = [");
        for (int i = 0; i < candies.length; i++) {
            System.out.print(candies[i]);
            if (i < candies.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("], k = " + k);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + actual);
        System.out.println("Status: " + (expected == actual ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
