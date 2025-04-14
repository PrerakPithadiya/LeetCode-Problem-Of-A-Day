/**
 * LeetCode Problem: Maximum Points from Solving Questions
 * Problem Number: 2140
 * 
 * Description:
 * You are given a 0-indexed 2D integer array questions where questions[i] = [points_i, brainpower_i].
 * The array represents a set of problems where:
 * - points_i represents the points you gain by solving the ith question.
 * - brainpower_i represents the number of questions you need to skip after solving the ith question.
 * 
 * You can solve the questions in any order. Return the maximum points you can earn.
 * 
 * Approach:
 * - Use dynamic programming with a bottom-up approach
 * - For each question, we have two choices:
 *   1. Solve the question: Gain points and skip the next 'brainpower' questions
 *   2. Skip the question: Move to the next question
 * - Choose the option that maximizes the total points
 * 
 * Time Complexity: O(n) where n is the number of questions
 * Space Complexity: O(n) for the dp array
 * 
 * @author Prerak Pithadiya
 * @date April 2024
 */
class Solution {
    /**
     * Calculates the maximum points that can be earned by solving questions optimally.
     * 
     * @param questions A 2D array where questions[i][0] represents points for the ith question
     *                  and questions[i][1] represents the number of questions to skip after solving it
     * @return The maximum possible points that can be earned
     */
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1]; // dp[n] is 0, representing no more questions
        
        for (int i = n - 1; i >= 0; i--) {
            int points = questions[i][0];
            int brainpower = questions[i][1];
            int j = i + brainpower + 1;
            long solve = points + (j < n ? dp[j] : 0);
            long skip = dp[i + 1];
            dp[i] = Math.max(solve, skip);
        }
        
        return dp[0];
    }

    /**
     * Main method with test cases to demonstrate the solution.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Case 1
        int[][] questions1 = {{3, 2}, {4, 3}, {4, 4}, {2, 5}};
        System.out.println("Test Case 1:");
        System.out.println("Input: [[3,2], [4,3], [4,4], [2,5]]");
        System.out.println("Expected Output: 5");
        System.out.println("Actual Output: " + solution.mostPoints(questions1));
        System.out.println();
        
        // Test Case 2
        int[][] questions2 = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}};
        System.out.println("Test Case 2:");
        System.out.println("Input: [[1,1], [2,2], [3,3], [4,4], [5,5]]");
        System.out.println("Expected Output: 7");
        System.out.println("Actual Output: " + solution.mostPoints(questions2));
        System.out.println();
        
        // Test Case 3
        int[][] questions3 = {{1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}};
        System.out.println("Test Case 3:");
        System.out.println("Input: [[1,0], [2,0], [3,0], [4,0], [5,0]]");
        System.out.println("Expected Output: 15");
        System.out.println("Actual Output: " + solution.mostPoints(questions3));
        System.out.println();
        
        // Test Case 4 - Large input
        int[][] questions4 = new int[10][2];
        for (int i = 0; i < 10; i++) {
            questions4[i][0] = (i + 1) * 10; // Points
            questions4[i][1] = i;            // Brainpower
        }
        System.out.println("Test Case 4 (Large Input):");
        System.out.println("Input: Array of 10 questions with increasing points and brainpower");
        System.out.println("Actual Output: " + solution.mostPoints(questions4));
    }
}
