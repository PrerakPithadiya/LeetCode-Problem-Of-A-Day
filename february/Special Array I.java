package LeetCode.February;

/**
 * Solution class for determining if an array is special based on alternating
 * parity. An array is considered special if adjacent elements have different
 * parity (odd/even).
 */
class Solution {

    /**
     * Determines if the given array is special based on alternating parity.
     *
     * @param nums The input array of integers to check
     * @return true if the array is special, false otherwise
     */
    public boolean isArraySpecial(int[] nums) {
        // If the array has only one element, it is special
        if (nums.length == 1) {
            return true;
        }

        // Iterate through the array and check adjacent pairs
        for (int i = 0; i < nums.length - 1; i++) {
            // Check if the parity of nums[i] and nums[i+1] is the same
            if ((nums[i] % 2) == (nums[i + 1] % 2)) {
                return false;
            }
        }

        // If all adjacent pairs have different parity, return true
        return true;
    }

    /**
     * Main method to test the solution with various test cases.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Array with alternating even-odd numbers
        int[] test1 = {2, 3, 4, 5};
        System.out.println("Test 1 (Expected: true): " + solution.isArraySpecial(test1));

        // Test Case 2: Array with same parity adjacent numbers
        int[] test2 = {2, 4, 6, 8};
        System.out.println("Test 2 (Expected: false): " + solution.isArraySpecial(test2));

        // Test Case 3: Single element array
        int[] test3 = {1};
        System.out.println("Test 3 (Expected: true): " + solution.isArraySpecial(test3));

        // Test Case 4: Array with negative numbers
        int[] test4 = {-2, 3, -4, 5};
        System.out.println("Test 4 (Expected: true): " + solution.isArraySpecial(test4));

        // Test Case 5: Empty array
        int[] test5 = {};
        System.out.println("Test 5 (Expected: true): " + solution.isArraySpecial(test5));
    }
}
