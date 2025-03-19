
/**
 * Solution class to solve the problem of making all elements in a binary array equal to 1
 * using minimum operations. In each operation, we can flip three consecutive elements
 * (0 becomes 1 and 1 becomes 0).
 */
class Solution {

    /**
     * Calculates the minimum number of operations required to make all elements
     * equal to 1.
     *
     * @param nums The input array containing only 0s and 1s
     * @return The minimum number of operations needed, or -1 if it's impossible
     * @throws IllegalArgumentException if the array length is less than 3
     */
    public int minOperations(int[] nums) {
        if (nums.length < 3) {
            throw new IllegalArgumentException("Array length must be at least 3");
        }

        int n = nums.length;
        int operations = 0;

        // Create a copy to avoid modifying the original array
        int[] arr = nums.clone();

        // Process each element except the last two
        for (int i = 0; i < n - 2; i++) {
            if (arr[i] == 0) {
                operations++;
                // Flip the three consecutive elements
                arr[i] ^= 1;
                arr[i + 1] ^= 1;
                arr[i + 2] ^= 1;
            }
        }

        // Check if all elements are 1
        return (arr[n - 2] == 1 && arr[n - 1] == 1) ? operations : -1;
    }

    /**
     * Main method to test the solution with various test cases.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case where solution exists
        int[] test1 = {0, 0, 0, 1, 0};
        System.out.println("Test Case 1: " + solution.minOperations(test1));  // Expected: 2

        // Test Case 2: Array already contains all 1s
        int[] test2 = {1, 1, 1, 1, 1};
        System.out.println("Test Case 2: " + solution.minOperations(test2));  // Expected: 0

        // Test Case 3: Impossible to make all elements 1
        int[] test3 = {0, 0, 0, 0, 0};
        System.out.println("Test Case 3: " + solution.minOperations(test3));  // Expected: -1

        // Test Case 4: Minimum length array
        int[] test4 = {0, 0, 0};
        System.out.println("Test Case 4: " + solution.minOperations(test4));  // Expected: 1

        try {
            // Test Case 5: Array with invalid length
            int[] test5 = {0, 0};
            System.out.println("Test Case 5: " + solution.minOperations(test5));
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 5: Caught expected exception - " + e.getMessage());
        }
    }
}
