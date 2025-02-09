
import java.util.HashMap;
import java.util.Map;

/**
 * Solution class for counting bad pairs in an array. A pair (i,j) is called bad
 * if i < j and j - i != nums[j] - nums[i]
 */
class Solution {

    /**
     * Counts the number of bad pairs in the given array.
     *
     * Algorithm: 1. Use a map to store count of (i - nums[i]) for each index 2.
     * Calculate total possible pairs using n*(n-1)/2 3. Calculate good pairs
     * using elements with same (i - nums[i]) value 4. Return total pairs minus
     * good pairs to get bad pairs
     *
     * Time Complexity: O(n) where n is length of input array Space Complexity:
     * O(n) for storing differences in HashMap
     *
     * @param nums Input array of integers
     * @return Number of bad pairs in the array
     */
    public long countBadPairs(int[] nums) {
        // Use a map to store the count of i - nums[i]
        Map<Integer, Long> map = new HashMap<>();

        // Calculate i - nums[i] for each element and count occurrences
        for (int i = 0; i < nums.length; i++) {
            int diff = i - nums[i];
            map.put(diff, map.getOrDefault(diff, 0L) + 1);
        }

        // Calculate total possible pairs
        long n = nums.length;
        long totalPairs = (n * (n - 1)) / 2;

        // Calculate good pairs
        long goodPairs = 0;
        for (long count : map.values()) {
            // If we have k numbers with same difference,
            // we can form k * (k-1) / 2 good pairs
            goodPairs += (count * (count - 1)) / 2;
        }

        // Return bad pairs (total pairs - good pairs)
        return totalPairs - goodPairs;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case
        int[] nums1 = {1, 2, 3, 4, 5};
        assert solution.countBadPairs(nums1) == 0 : "Test case 1 failed";

        // Test Case 2: Array with bad pairs
        int[] nums2 = {4, 1, 3, 3};
        assert solution.countBadPairs(nums2) == 5 : "Test case 2 failed";

        // Test Case 3: Array with all same elements
        int[] nums3 = {1, 1, 1, 1};
        assert solution.countBadPairs(nums3) == 6 : "Test case 3 failed";

        // Test Case 4: Empty array
        int[] nums4 = {};
        assert solution.countBadPairs(nums4) == 0 : "Test case 4 failed";

        // Test Case 5: Array with single element
        int[] nums5 = {1};
        assert solution.countBadPairs(nums5) == 0 : "Test case 5 failed";

        System.out.println("All test cases passed!");
    }
}
