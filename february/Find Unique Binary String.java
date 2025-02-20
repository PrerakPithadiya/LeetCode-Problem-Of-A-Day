/**
 * LeetCode #2397: Find Unique Binary String
 * 
 * Problem Description:
 * Given an array of strings nums containing n unique binary strings each of length n,
 * return a binary string of length n that does not appear in nums.
 * 
 * Approach:
 * This solution uses Cantor's diagonal argument:
 * 1. For each string at index i, look at its i-th character
 * 2. Take the opposite bit (0->1 or 1->0) for that position
 * 3. The resulting string is guaranteed to be different from all input strings
 * 
 * Time Complexity: O(n) where n is the length of nums array
 * Space Complexity: O(n) to store the result string
 */
class Solution {
    /**
     * Finds a binary string that is not present in the given array of binary strings.
     * 
     * @param nums Array of binary strings, where each string has length equal to nums.length
     * @return A binary string that does not appear in nums
     * @throws IllegalArgumentException if nums is empty or contains invalid binary strings
     */
    public String findDifferentBinaryString(String[] nums) {
        // Input validation
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty");
        }
        
        int n = nums.length;
        // Validate each string's length and content
        for (String str : nums) {
            if (str == null || str.length() != n) {
                throw new IllegalArgumentException("Each binary string must have length equal to array length");
            }
            if (!str.matches("[01]+")) {
                throw new IllegalArgumentException("Strings must contain only 0s and 1s");
            }
        }
        
        StringBuilder result = new StringBuilder();
        
        // Using Cantor's diagonal argument
        // Take the opposite bit of each string at its own index
        for (int i = 0; i < nums.length; i++) {
            // Get the i-th character of i-th string and flip it
            result.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }
        
        return result.toString();
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Case 1: Basic case
        test(solution, new String[]{"01", "10"}, "11");
        
        // Test Case 2: Another basic case
        test(solution, new String[]{"00", "01"}, "11");
        
        // Test Case 3: Longer strings
        test(solution, new String[]{"111", "011", "001"}, "101");
        
        // Test Case 4: Single element
        test(solution, new String[]{"0"}, "1");
        
        // Test Case 5: Edge case with all zeros
        test(solution, new String[]{"000", "001", "010"}, "111");
        
        System.out.println("All test cases passed!");
    }
    
    /**
     * Helper method to run test cases
     */
    private static void test(Solution solution, String[] input, String expectedOutput) {
        String result = solution.findDifferentBinaryString(input);
        // Verify that result is not in input array and has correct length
        assert result.length() == input.length : 
            "Result length should match input string length";
        for (String str : input) {
            assert !str.equals(result) : 
                "Result should not be present in input array";
        }
        System.out.println("Test passed for input: " + String.join(", ", input) + 
                         " | Output: " + result);
    }
} 