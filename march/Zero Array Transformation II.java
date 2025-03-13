
/**
 * Solution class for Zero Array Transformation problem
 * This class provides methods to find the minimum number of queries needed to transform an array into a zero array
 */
class Solution {

    /**
     * Finds the minimum number of queries needed to transform the array into a
     * zero array Uses binary search approach to find the optimal number of
     * queries
     *
     * @param nums The input array of integers to be transformed
     * @param queries 2D array where each query contains [left, right, val]
     * representing the range and value to add
     * @return The minimum number of queries needed, or -1 if transformation is
     * impossible
     */
    public int minZeroArray(int[] nums, int[][] queries) {
        int left = 0, right = queries.length;

        // Zero array isn't formed after all queries are processed
        if (!currentIndexZero(nums, queries, right)) {
            return -1;
        }

        // Binary Search to find minimum queries needed
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (currentIndexZero(nums, queries, middle)) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        // Return earliest query that zero array can be formed
        return left;
    }

    /**
     * Helper method to check if array can be transformed to zero array using k
     * queries Uses difference array technique to efficiently process range
     * updates
     *
     * @param nums The input array
     * @param queries Array of queries
     * @param k Number of queries to process
     * @return true if array can be transformed to zero array, false otherwise
     */
    private boolean currentIndexZero(int[] nums, int[][] queries, int k) {
        int n = nums.length, sum = 0;
        int[] differenceArray = new int[n + 1];

        // Process queries using difference array technique
        for (int queryIndex = 0; queryIndex < k; queryIndex++) {
            int left = queries[queryIndex][0], right = queries[queryIndex][1],
                    val = queries[queryIndex][2];

            // Process start and end of range
            differenceArray[left] += val;
            differenceArray[right + 1] -= val;
        }

        // Check if zero array can be formed by verifying if each position can be reduced to zero
        for (int numIndex = 0; numIndex < n; numIndex++) {
            sum += differenceArray[numIndex];
            if (sum < nums[numIndex]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main method with test cases to demonstrate the functionality
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Simple case where transformation is possible
        int[] nums1 = {2, 2};
        int[][] queries1 = {{0, 0, 1}, {1, 1, 1}, {0, 1, 1}};
        System.out.println("Test Case 1: " + solution.minZeroArray(nums1, queries1));
        // Expected output: 3

        // Test Case 2: Case where transformation is impossible
        int[] nums2 = {1, 2, 3};
        int[][] queries2 = {{0, 1, 1}, {1, 2, 1}};
        System.out.println("Test Case 2: " + solution.minZeroArray(nums2, queries2));
        // Expected output: -1

        // Test Case 3: Case with larger array
        int[] nums3 = {5, 4, 3, 2, 1};
        int[][] queries3 = {
            {0, 4, 2},
            {0, 2, 3},
            {2, 4, 1},
            {0, 4, 1}
        };
        System.out.println("Test Case 3: " + solution.minZeroArray(nums3, queries3));
        // Expected output: 4

        // Test Case 4: Edge case with single element
        int[] nums4 = {1};
        int[][] queries4 = {{0, 0, 1}};
        System.out.println("Test Case 4: " + solution.minZeroArray(nums4, queries4));
        // Expected output: 1
    }
}
