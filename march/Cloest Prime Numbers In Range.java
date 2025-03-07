
/**
 * Solution class for finding the closest prime numbers within a given range.
 *
 * Problem Description:
 * Given two positive integers left and right, find two prime numbers num1 and num2 such that:
 * 1. left <= num1 < num2 <= right
 * 2. num2 - num1 is the minimum possible difference
 *
 * Time Complexity: O(n log log n) where n is the value of right
 * Space Complexity: O(n) for the boolean array
 */
class Solution {

    /**
     * Finds the closest pair of prime numbers within the given range.
     *
     * @param left The lower bound of the range (inclusive)
     * @param right The upper bound of the range (inclusive)
     * @return int[] Array containing the closest prime pair, or [-1,-1] if no
     * such pair exists
     */
    public int[] closestPrimes(int left, int right) {
        // Base case checks
        if (right <= 2 || left > right) {
            return new int[]{-1, -1};
        }

        // Initialize variables to track closest primes
        int prevPrime = -1;
        int minDiff = Integer.MAX_VALUE;
        int[] result = {-1, -1};

        // Create boolean array only for required range
        boolean[] isComposite = new boolean[right + 1];

        // Optimized Sieve of Eratosthenes
        for (int i = 2; i * i <= right; i++) {
            if (!isComposite[i]) {
                // Mark composites starting from i*i
                for (int j = i * i; j <= right; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        // Find closest primes in one pass
        for (int i = Math.max(2, left); i <= right; i++) {
            if (!isComposite[i]) {
                if (prevPrime != -1) {
                    int diff = i - prevPrime;
                    if (diff < minDiff) {
                        minDiff = diff;
                        result[0] = prevPrime;
                        result[1] = i;
                    }
                }
                prevPrime = i;
            }
        }

        return result[0] == -1 ? new int[]{-1, -1} : result;
    }

    /**
     * Main method to run test cases.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        int[][] testCases = {
            {10, 19}, // Expected: [11, 13]
            {4, 6}, // Expected: [-1, -1]
            {1, 1}, // Expected: [-1, -1]
            {1, 10}, // Expected: [2, 3]
            {20, 50} // Expected: [23, 29]
        };

        // Run test cases
        for (int i = 0; i < testCases.length; i++) {
            int[] result = solution.closestPrimes(testCases[i][0], testCases[i][1]);
            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("Input: left = %d, right = %d\n", testCases[i][0], testCases[i][1]);
            System.out.printf("Output: [%d, %d]\n", result[0], result[1]);
            System.out.println("------------------");
        }
    }
}
