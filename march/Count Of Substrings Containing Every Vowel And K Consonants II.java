
/**
 * Problem: Count Of Substrings Containing Every Vowel And K Consonants II
 *
 * Approach:
 * 1. Use sliding window technique with two pointers (left and right)
 * 2. Maintain a 2D frequency array:
 *    - First row marks vowels (a,e,i,o,u)
 *    - Second row tracks character frequencies
 * 3. Track:
 *    - currentK: count of consonants in current window
 *    - vowels: count of unique vowels in current window
 *    - extraLeft: count of additional valid windows
 *
 * Time Complexity: O(n) where n is the length of input string
 * Space Complexity: O(1) as we use fixed-size arrays
 */
class Solution {

    public long countOfSubstrings(String word, int k) {
        // Create 2D array to store vowel flags and frequencies
        int[][] frequencies = new int[2][128];

        // Mark vowels in the first row
        frequencies[0]['a'] = 1;
        frequencies[0]['e'] = 1;
        frequencies[0]['i'] = 1;
        frequencies[0]['o'] = 1;
        frequencies[0]['u'] = 1;

        long response = 0;
        int currentK = 0;       // Current count of consonants
        int vowels = 0;         // Count of unique vowels found
        int extraLeft = 0;      // Count of extra valid windows

        // Sliding window approach
        for (int right = 0, left = 0; right < word.length(); right++) {
            char rightChar = word.charAt(right);

            // Process right character
            if (frequencies[0][rightChar] == 1) {  // If it's a vowel
                if (++frequencies[1][rightChar] == 1) {
                    vowels++;
                }
            } else {  // If it's a consonant
                currentK++;
            }

            // Shrink window if consonants exceed k
            while (currentK > k) {
                char leftChar = word.charAt(left);
                if (frequencies[0][leftChar] == 1) {
                    if (--frequencies[1][leftChar] == 0) {
                        vowels--;
                    }
                } else {
                    currentK--;
                }
                left++;
                extraLeft = 0;
            }

            // Count additional valid windows by removing duplicate vowels from left
            while (vowels == 5 && currentK == k && left < right
                    && frequencies[0][word.charAt(left)] == 1
                    && frequencies[1][word.charAt(left)] > 1) {
                extraLeft++;
                frequencies[1][word.charAt(left)]--;
                left++;
            }

            // Add valid windows to result
            if (currentK == k && vowels == 5) {
                response += (1 + extraLeft);
            }
        }

        return response;
    }

    /**
     * Main method to run test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String[] testWords = {
            "aeioqq", // Test Case 1
            "aeiou", // Test Case 2
            "ieaouqqieaouqq" // Test Case 3
        };
        int[] testK = {1, 0, 1};
        long[] expectedResults = {0, 1, 3};

        // Run test cases
        for (int i = 0; i < testWords.length; i++) {
            long result = solution.countOfSubstrings(testWords[i], testK[i]);
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: word = \"" + testWords[i] + "\", k = " + testK[i]);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println("Got: " + result);
            System.out.println("Status: " + (result == expectedResults[i] ? "PASSED" : "FAILED"));
            System.out.println();
        }
    }
}
