
/**
 * Map of Highest Peak - Problem Solution
 *
 * Problem Description:
 * Given an isWater matrix where 1 represents water cells and 0 represents land cells,
 * construct a height map where:
 * - Water cells must have height 0
 * - Land cells must have positive heights
 * - Any two adjacent cells must have an absolute height difference of at most 1
 *
 * The goal is to maximize the height of the land cells while satisfying these conditions.
 */
import java.util.LinkedList;
import java.util.Queue;

/**
 * Solution 1: Optimized BFS Approach Time Complexity: O(m*n) where m and n are
 * dimensions of the matrix Space Complexity: O(m*n) for the queue in worst case
 */
class Solution1 {

    /**
     * Constructs a height map using BFS starting from water cells
     *
     * @param isWater Input matrix where 1 represents water and 0 represents
     * land
     * @return Matrix containing the heights of each cell
     */
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // Initialize and mark water cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    isWater[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    isWater[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int h = isWater[curr[0]][curr[1]];

            for (int[] d : dirs) {
                int x = curr[0] + d[0], y = curr[1] + d[1];
                if (x >= 0 && x < m && y >= 0 && y < n && isWater[x][y] > h + 1) {
                    isWater[x][y] = h + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return isWater;
    }

    /**
     * Test cases demonstrating the functionality
     */
    public static void runTests() {
        Solution1 solution = new Solution1();

        // Test Case 1: Simple 2x2 matrix
        int[][] test1 = {{1, 0}, {0, 0}};
        assert java.util.Arrays.deepEquals(solution.highestPeak(test1), new int[][]{{0, 1}, {1, 2}});

        // Test Case 2: Matrix with all water cells
        int[][] test2 = {{1, 1}, {1, 1}};
        assert java.util.Arrays.deepEquals(solution.highestPeak(test2), new int[][]{{0, 0}, {0, 0}});

        // Test Case 3: Matrix with all land cells
        int[][] test3 = {{0, 0}, {0, 0}};
        int[][] result3 = solution.highestPeak(test3);
        assert result3[0][0] >= 0 && Math.abs(result3[0][0] - result3[0][1]) <= 1;

        System.out.println("All tests passed for Solution1!");
    }
}

/**
 * Solution 2: Dynamic Programming Approach Time Complexity: O(m*n) where m and
 * n are dimensions of the matrix Space Complexity: O(m*n) for the height matrix
 */
class Solution2 {

    /**
     * Constructs a height map using two-pass dynamic programming
     *
     * @param isWater Input matrix where 1 represents water and 0 represents
     * land
     * @return Matrix containing the heights of each cell
     */
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] height = new int[m][n];
        int INF = 2000000;

        // First pass: top-left to bottom-right
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                } else {
                    height[i][j] = INF;
                    if (i > 0) {
                        height[i][j] = Math.min(height[i][j], height[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        height[i][j] = Math.min(height[i][j], height[i][j - 1] + 1);
                    }
                }
            }
        }

        // Second pass: bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i < m - 1) {
                    height[i][j] = Math.min(height[i][j], height[i + 1][j] + 1);
                }
                if (j < n - 1) {
                    height[i][j] = Math.min(height[i][j], height[i][j + 1] + 1);
                }
            }
        }

        return height;
    }

    /**
     * Test cases demonstrating the functionality
     */
    public static void runTests() {
        Solution2 solution = new Solution2();

        // Test Case 1: Simple 2x2 matrix
        int[][] test1 = {{1, 0}, {0, 0}};
        assert java.util.Arrays.deepEquals(solution.highestPeak(test1), new int[][]{{0, 1}, {1, 2}});

        // Test Case 2: Larger matrix with multiple water cells
        int[][] test2 = {
            {1, 0, 0},
            {0, 0, 1},
            {0, 0, 0}
        };
        int[][] result2 = solution.highestPeak(test2);
        // Verify adjacent cell difference is at most 1
        for (int i = 0; i < result2.length; i++) {
            for (int j = 0; j < result2[0].length; j++) {
                if (i > 0) {
                    assert Math.abs(result2[i][j] - result2[i - 1][j]) <= 1;
                }
                if (j > 0) {
                    assert Math.abs(result2[i][j] - result2[i][j - 1]) <= 1;
                }
            }
        }

        System.out.println("All tests passed for Solution2!");
    }
}
