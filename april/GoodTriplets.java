/**
 * LeetCode Problem: 2179. Count Good Triplets in an Array
 * 
 * Problem Description:
 * You are given two 0-indexed arrays nums1 and nums2 of length n, both of which are permutations of [0, 1, ..., n-1].
 * A triplet of indices (i, j, k) is good if it satisfies all of the following conditions:
 * - 0 <= i < j < k < n
 * - nums1[i] < nums1[j] < nums1[k]
 * - nums2[i] < nums2[j] < nums2[k]
 * 
 * Return the number of good triplets.
 * 
 * Approach:
 * 1. We use Fenwick Trees (Binary Indexed Trees) to efficiently count elements.
 * 2. We map the positions of each element in both arrays.
 * 3. We sort elements by their positions in nums1.
 * 4. For each element, we compute:
 *    - xCount: number of elements that appear before it in both arrays
 *    - zCount: number of elements that appear after it in both arrays
 * 5. The total number of good triplets is the sum of xCount[y] * zCount[y] for all y.
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

import java.util.Arrays;

class Solution {
    /**
     * Counts the number of good triplets in the given arrays.
     * 
     * @param nums1 First array, a permutation of [0, 1, ..., n-1]
     * @param nums2 Second array, a permutation of [0, 1, ..., n-1]
     * @return The number of good triplets
     */
    public long goodTriplets(int[] nums1, int[] nums2) {
        final int n = nums1.length;
        final int[] pos1 = new int[n];
        final int[] pos2 = new int[n];
        
        // Store the position of each element in both arrays
        for (int i = 0; i < n; ++i) {
            pos1[nums1[i]] = i;
            pos2[nums2[i]] = i;
        }
        
        // Create elements sorted by pos1
        final Integer[] elements = new Integer[n];
        for (int i = 0; i < n; ++i) elements[i] = i;
        Arrays.sort(elements, (a, b) -> pos1[a] - pos1[b]);
        
        // Precompute pos2 for elements sorted by pos1
        final int[] pos2Sorted = new int[n];
        for (int i = 0; i < n; ++i) pos2Sorted[i] = pos2[elements[i]];
        
        // Compute x_count using Fenwick Tree logic inline
        final int[] xCount = new int[n];
        final int[] ft1 = new int[n + 2];
        for (int i = 0; i < n; ++i) {
            final int currentPos2 = pos2Sorted[i];
            int sum = 0;
            for (int idx = currentPos2; idx > 0; idx -= idx & -idx) sum += ft1[idx];
            xCount[elements[i]] = sum;
            for (int idx = currentPos2 + 1; idx < ft1.length; idx += idx & -idx) ft1[idx]++;
        }
        
        // Compute z_count using Fenwick Tree logic inline in reverse
        final int[] zCount = new int[n];
        final int[] ft2 = new int[n + 2];
        int count = 0;
        for (int i = n - 1; i >= 0; --i) {
            final int currentPos2 = pos2Sorted[i];
            int sumLe = 0;
            for (int idx = currentPos2 + 1; idx > 0; idx -= idx & -idx) sumLe += ft2[idx];
            zCount[elements[i]] = count - sumLe;
            for (int idx = currentPos2 + 1; idx < ft2.length; idx += idx & -idx) ft2[idx]++;
            ++count;
        }
        
        // Calculate the total good triplets
        long total = 0;
        for (int y = 0; y < n; ++y) total += (long) xCount[y] * zCount[y];
        return total;
    }
}

/**
 * Main class to run test cases for the Solution
 */
public class GoodTriplets {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Case 1
        int[] nums1_1 = {2, 0, 1, 3};
        int[] nums2_1 = {0, 1, 2, 3};
        System.out.println("Test Case 1:");
        System.out.println("nums1 = " + Arrays.toString(nums1_1));
        System.out.println("nums2 = " + Arrays.toString(nums2_1));
        System.out.println("Expected Output: 1");
        System.out.println("Actual Output: " + solution.goodTriplets(nums1_1, nums2_1));
        System.out.println();
        
        // Test Case 2
        int[] nums1_2 = {4, 0, 1, 3, 2};
        int[] nums2_2 = {4, 1, 0, 2, 3};
        System.out.println("Test Case 2:");
        System.out.println("nums1 = " + Arrays.toString(nums1_2));
        System.out.println("nums2 = " + Arrays.toString(nums2_2));
        System.out.println("Expected Output: 4");
        System.out.println("Actual Output: " + solution.goodTriplets(nums1_2, nums2_2));
        System.out.println();
        
        // Test Case 3 (Larger example)
        int[] nums1_3 = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] nums2_3 = {0, 1, 2, 3, 4, 5, 6, 7};
        System.out.println("Test Case 3:");
        System.out.println("nums1 = " + Arrays.toString(nums1_3));
        System.out.println("nums2 = " + Arrays.toString(nums2_3));
        System.out.println("Expected Output: 56");
        System.out.println("Actual Output: " + solution.goodTriplets(nums1_3, nums2_3));
    }
}
