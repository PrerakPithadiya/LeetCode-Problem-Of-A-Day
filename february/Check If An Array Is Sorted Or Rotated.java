package LeetCode.February;

/**
 * Check If Array Is Sorted and Rotated
 *
 * Problem Description: Given an array nums, return true if the array was
 * originally sorted in non-decreasing order, then rotated some number of
 * positions (including zero). Otherwise, return false.
 *
 * Algorithm: 1. Count the number of positions where current element is greater
 * than next element 2. If count is less than or equal to 1, array is sorted and
 * rotated 3. For a sorted and rotated array, there can be at most one position
 * where nums[i] > nums[i+1]
 *
 * Time Complexity: O(n) where n is length of array Space Complexity: O(1)
 */
class Solution {

    /**
     * Checks if array is sorted and rotated
     *
     * @param nums input array of integers
     * @return true if array was originally sorted and rotated, false otherwise
     */
    public boolean check(int[] nums) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
        }

        return count <= 1;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Sorted and rotated array
        int[] test1 = {3, 4, 5, 1, 2};
        System.out.println("Test 1: " + solution.check(test1)); // Expected: true

        // Test Case 2: Sorted array (no rotation)
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2: " + solution.check(test2)); // Expected: true

        // Test Case 3: Unsorted array
        int[] test3 = {2, 1, 3, 4};
        System.out.println("Test 3: " + solution.check(test3)); // Expected: false

        // Test Case 4: Single element array
        int[] test4 = {1};
        System.out.println("Test 4: " + solution.check(test4)); // Expected: true

        // Test Case 5: Array with duplicate elements
        int[] test5 = {2, 1, 1, 2, 1};
        System.out.println("Test 5: " + solution.check(test5)); // Expected: false
    }
}
