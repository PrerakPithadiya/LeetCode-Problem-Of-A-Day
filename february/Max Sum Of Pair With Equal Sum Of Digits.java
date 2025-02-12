
/**
 * Solution class for finding the maximum sum of a pair of numbers with equal digit sums.
 *
 * Problem Description:
 * Given an array of integers, find two numbers that have the same sum of digits and
 * maximize their sum. If no such pair exists, return -1.
 *
 * Time Complexity: O(n) where n is the length of input array
 * Space Complexity: O(1) as we use fixed size arrays (82 elements)
 */
class Solution {

    /**
     * Finds the maximum possible sum of two numbers with equal digit sums.
     *
     * @param nums Input array of integers where 1 <= nums[i] <= 10^9 @ return
     * Maximum sum of two numbers with equal digit sums, or -1 if no such pair
     * exists
     */
    public int maximumSum(int[] nums) {
        // Map to store max two numbers for each digit sum
        // We'll use an array where index is digit sum
        // Since nums[i] <= 10^9, max digit sum is 9 * 9 = 81
        int[] maxNum = new int[82];    // Store largest number for each digit sum
        int[] secondMax = new int[82];  // Store second largest number

        // Initialize arrays with -1
        Arrays.fill(maxNum, -1);
        Arrays.fill(secondMax, -1);

        // Keep track of maximum sum found
        int maxSum = -1;

        // Process each number in array
        for (int num : nums) {
            int digitSum = getDigitSum(num);

            // Update maxNum and secondMax for this digitSum
            if (num > maxNum[digitSum]) {
                secondMax[digitSum] = maxNum[digitSum];
                maxNum[digitSum] = num;
            } else if (num > secondMax[digitSum]) {
                secondMax[digitSum] = num;
            }

            // If we have two numbers with this digit sum, update maxSum
            if (maxNum[digitSum] != -1 && secondMax[digitSum] != -1) {
                maxSum = Math.max(maxSum, maxNum[digitSum] + secondMax[digitSum]);
            }
        }

        return maxSum;
    }

    /**
     * Calculates the sum of digits for a given number.
     *
     * @param num Input number
     * @return Sum of all digits in the number
     */
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case with multiple pairs
        assert solution.maximumSum(new int[]{18, 43, 36, 13, 7}) == 54;  // 18 + 36 = 54 (both have digit sum 9)

        // Test Case 2: No valid pairs
        assert solution.maximumSum(new int[]{1, 2, 3, 4}) == -1;

        // Test Case 3: Multiple pairs with same digit sum
        assert solution.maximumSum(new int[]{10, 12, 19, 14}) == 33;  // 19 + 14 = 33 (both have digit sum 10)

        // Test Case 4: Large numbers
        assert solution.maximumSum(new int[]{999999999, 999999998, 123456789}) == 1999999997;

        // Test Case 5: Single element
        assert solution.maximumSum(new int[]{5}) == -1;

        System.out.println("All test cases passed!");
    }
}
