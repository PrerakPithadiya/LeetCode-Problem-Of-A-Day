
import java.util.PriorityQueue;

/**
 * Solution class for finding minimum operations to exceed a threshold value
 * Problem: Given an array of integers and a threshold k, perform operations to
 * make all elements >= k Operation: Take two smallest numbers x,y and replace
 * them with (min(x,y) * 2 + max(x,y))
 */
class Solution {

    /**
     * Calculates minimum operations needed to make all elements >= threshold k
     *
     * @param nums Input array of integers to process
     * @param k Threshold value that all elements should meet or exceed
     * @return Minimum number of operations needed, or -1 if impossible
     * @throws IllegalArgumentException if nums is null or empty, or k is
     * negative
     */
    public int minOperations(int[] nums, int k) {
        // Input validation
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Array must contain at least 2 elements");
        }
        if (k < 0) {
            throw new IllegalArgumentException("Threshold k must be non-negative");
        }

        // Create a min heap priority queue
        PriorityQueue<Long> pq = new PriorityQueue<>();

        // Add all numbers to priority queue
        // Using long to handle potential integer overflow
        for (int num : nums) {
            pq.offer((long) num);
        }

        int operations = 0;

        // Continue while we have at least 2 elements and
        // the smallest element is less than k
        while (pq.size() >= 2 && pq.peek() < k) {
            // Get two smallest numbers
            long x = pq.poll();
            long y = pq.poll();

            // Calculate new number using the formula
            long newNum = Math.min(x, y) * 2 + Math.max(x, y);

            // Add new number back to queue
            pq.offer(newNum);

            operations++;
        }

        // Check if we succeeded in making all elements >= k
        return pq.peek() >= k ? operations : -1;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal case
        int[] nums1 = {2, 3, 4, 5};
        int k1 = 10;
        assert solution.minOperations(nums1, k1) == 2;

        // Test Case 2: Already meeting threshold
        int[] nums2 = {10, 12, 15};
        int k2 = 10;
        assert solution.minOperations(nums2, k2) == 0;

        // Test Case 3: Impossible case
        int[] nums3 = {1, 1, 1};
        int k3 = 100;
        assert solution.minOperations(nums3, k3) == -1;

        // Test Case 4: Edge case with minimum array size
        int[] nums4 = {1, 2};
        int k4 = 5;
        assert solution.minOperations(nums4, k4) == 1;

        // Test Case 5: Large numbers
        int[] nums5 = {1000000, 1000000};
        int k5 = 5000000;
        assert solution.minOperations(nums5, k5) == 2;

        System.out.println("All test cases passed!");
    }
}
