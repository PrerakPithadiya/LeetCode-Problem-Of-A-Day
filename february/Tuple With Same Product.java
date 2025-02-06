package LeetCode.February;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode Problem 1726: Tuple with Same Product
 *
 * Given an array nums of distinct positive integers, return the number of
 * tuples (a,b,c,d) such that: - a, b, c, and d are elements of nums - a * b = c
 * * d - a, b, c, and d are distinct elements
 *
 * Time Complexity: O(n^2) where n is the length of nums array Space Complexity:
 * O(n^2) to store the products in HashMap
 */
class Solution {

    /**
     * Finds the number of tuples (a,b,c,d) where a*b = c*d
     *
     * @param nums array of distinct positive integers
     * @return number of valid tuples
     */
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> productCount = new HashMap<>();
        int count = 0;

        // Iterate over all pairs (i, j)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];
                productCount.put(product, productCount.getOrDefault(product, 0) + 1);
            }
        }

        // Calculate the number of tuples
        for (int val : productCount.values()) {
            if (val > 1) {
                count += val * (val - 1) / 2 * 8;
            }
        }

        return count;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case
        assert solution.tupleSameProduct(new int[]{2, 3, 4, 6}) == 8 : "Test case 1 failed";

        // Test Case 2: No valid tuples
        assert solution.tupleSameProduct(new int[]{1, 2, 4, 5}) == 0 : "Test case 2 failed";

        // Test Case 3: Multiple valid tuples
        assert solution.tupleSameProduct(new int[]{2, 3, 4, 6, 8, 12}) == 40 : "Test case 3 failed";

        // Test Case 4: Single element
        assert solution.tupleSameProduct(new int[]{1}) == 0 : "Test case 4 failed";

        // Test Case 5: Two elements
        assert solution.tupleSameProduct(new int[]{1, 2}) == 0 : "Test case 5 failed";

        // Test Case 6: Larger input
        assert solution.tupleSameProduct(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}) == 16 : "Test case 6 failed";

        // Test Case 7: Edge case with larger numbers
        assert solution.tupleSameProduct(new int[]{10, 20, 30, 40, 50, 60}) == 8 : "Test case 7 failed";

        System.out.println("All test cases passed!");
    }
}
