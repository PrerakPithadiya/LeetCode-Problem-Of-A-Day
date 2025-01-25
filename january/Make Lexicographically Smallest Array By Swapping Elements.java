
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution class for making lexicographically smallest array by swapping
 * elements within a limit.
 *
 * Problem Description: Given an array of integers and a limit value, make the
 * array as lexicographically small as possible by swapping elements that differ
 * by at most the limit value.
 *
 * Time Complexity: O(n log n) where n is the length of the input array Space
 * Complexity: O(n) for storing pairs, groups, and union-find data structure
 */
class Solution {

    /**
     * Makes the input array lexicographically smallest possible while
     * respecting the limit constraint.
     *
     * @param nums The input array of integers
     * @param limit The maximum allowed difference between elements that can be
     * swapped
     * @return The lexicographically smallest possible array after valid swaps
     */
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Create pairs of value and original index
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[]{nums[i], i};
        }

        // Sort pairs based on values
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        // Use Union Find (Disjoint Set Union) to group connected elements
        UnionFind uf = new UnionFind(n);

        // Connect indices with elements within the limit
        for (int i = 1; i < n; i++) {
            if (pairs[i][0] - pairs[i - 1][0] <= limit) {
                uf.union(pairs[i - 1][1], pairs[i][1]);
            }
        }

        // Group indices by their root
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(pairs[i][1]);
            groups.computeIfAbsent(root, _ -> new ArrayList<>()).add(i);
        }

        // Reconstruct the result array
        int[] result = new int[n];
        for (List<Integer> group : groups.values()) {
            // Extract original indices and values for this group
            List<Integer> indices = new ArrayList<>();
            List<Integer> values = new ArrayList<>();

            for (int index : group) {
                indices.add(pairs[index][1]);
                values.add(pairs[index][0]);
            }

            // Sort indices and values
            Collections.sort(indices);
            Collections.sort(values);

            // Place sorted values in sorted indices
            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }
        }

        return result;
    }

    /**
     * Union Find (Disjoint Set Union) class for grouping connected elements.
     * Implements path compression and union by rank optimizations.
     */
    class UnionFind {

        private final int[] parent;
        private final int[] rank;

        /**
         * Initializes the Union Find data structure.
         *
         * @param size The number of elements to be managed
         */
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        /**
         * Finds the root of the set containing element x. Uses path compression
         * for optimization.
         *
         * @param x The element to find the root for
         * @return The root of the set containing x
         */
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * Unions two sets containing elements x and y. Uses union by rank for
         * optimization.
         *
         * @param x First element
         * @param y Second element
         */
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    /**
     * Test cases for the lexicographicallySmallestArray method.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case
        int[] nums1 = {1, 5, 3, 9, 8};
        int limit1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", limit = " + limit1);
        System.out.println("Output: " + Arrays.toString(solution.lexicographicallySmallestArray(nums1, limit1)));
        // Expected: [1, 3, 5, 8, 9]

        // Test Case 2: No swaps possible
        int[] nums2 = {1, 7, 6, 18, 2};
        int limit2 = 1;
        System.out.println("\nTest Case 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", limit = " + limit2);
        System.out.println("Output: " + Arrays.toString(solution.lexicographicallySmallestArray(nums2, limit2)));
        // Expected: [1, 7, 6, 18, 2]

        // Test Case 3: All elements can be swapped
        int[] nums3 = {5, 4, 3, 2, 1};
        int limit3 = 10;
        System.out.println("\nTest Case 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", limit = " + limit3);
        System.out.println("Output: " + Arrays.toString(solution.lexicographicallySmallestArray(nums3, limit3)));
        // Expected: [1, 2, 3, 4, 5]

        // Test Case 4: Single element array
        int[] nums4 = {1};
        int limit4 = 5;
        System.out.println("\nTest Case 4:");
        System.out.println("Input: nums = " + Arrays.toString(nums4) + ", limit = " + limit4);
        System.out.println("Output: " + Arrays.toString(solution.lexicographicallySmallestArray(nums4, limit4)));
        // Expected: [1]
    }
}
