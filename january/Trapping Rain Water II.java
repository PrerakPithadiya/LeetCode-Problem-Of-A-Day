
/**
 * LeetCode 407 - Trapping Rain Water II
 *
 * Problem Description:
 * Given an m x n integer matrix heightMap representing the height of each unit cell,
 * return the volume of water it can trap after raining.
 *
 * Solution Approach:
 * 1. Uses a min-heap based approach to process cells from the boundary inwards
 * 2. Maintains a visited array to track processed cells
 * 3. Process order: lowest height cells first to ensure correct water trapping
 *
 * Time Complexity: O(m*n * log(m*n)) where m,n are dimensions of the heightMap
 * Space Complexity: O(m*n) for visited array and priority queue
 */
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    /**
     * Calculates the volume of water that can be trapped in the given height
     * map.
     *
     * @param heightMap 2D array representing heights of cells
     * @return total volume of water that can be trapped
     */
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        // Edge case: If the matrix is too small to trap water.
        if (m < 3 || n < 3) {
            return 0;
        }

        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        // Add all boundary cells to the heap
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    minHeap.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        // Directions for traversing neighbors (right, down, left, up)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int trappedWater = 0;

        // Process cells in the priority queue
        while (!minHeap.isEmpty()) {
            int[] cell = minHeap.poll();
            int x = cell[0], y = cell[1], height = cell[2];

            // Visit all 4 neighbors
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                // Check bounds and if the cell is already visited
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    // Calculate trapped water
                    trappedWater += Math.max(0, height - heightMap[nx][ny]);
                    // Update height to the maximum of the current boundary
                    minHeap.offer(new int[]{nx, ny, Math.max(height, heightMap[nx][ny])});
                }
            }
        }

        return trappedWater;
    }

    /**
     * Test cases for the trapRainWater method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case with water trapped in the middle
        int[][] test1 = {
            {1, 4, 3, 1, 3, 2},
            {3, 2, 1, 3, 2, 4},
            {2, 3, 3, 2, 3, 1}
        };
        assert solution.trapRainWater(test1) == 4 : "Test Case 1 Failed";

        // Test Case 2: No water can be trapped
        int[][] test2 = {
            {3, 3, 3, 3},
            {3, 2, 2, 3},
            {3, 3, 3, 3}
        };
        assert solution.trapRainWater(test2) == 0 : "Test Case 2 Failed";

        // Test Case 3: Small matrix (edge case)
        int[][] test3 = {
            {1, 2},
            {2, 1}
        };
        assert solution.trapRainWater(test3) == 0 : "Test Case 3 Failed";

        // Test Case 4: Complex case with multiple water traps
        int[][] test4 = {
            {12, 13, 1, 12},
            {13, 4, 13, 12},
            {13, 8, 10, 12},
            {12, 13, 12, 12},
            {13, 13, 13, 13}
        };
        assert solution.trapRainWater(test4) == 14 : "Test Case 4 Failed";

        System.out.println("All test cases passed!");
    }
}
