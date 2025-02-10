
/**
 * Solution class containing method to clear digits and their closest non-digit characters from a string.
 */
class Solution {

    /**
     * Removes digits and their closest non-digit characters to the left from a
     * given string. For each digit found in the string, both the digit and its
     * nearest non-digit character to the left (if one exists) are removed. This
     * process continues until no digits remain.
     *
     * Algorithm: 1. Convert input string to StringBuilder for efficient
     * manipulation 2. Repeatedly: a. Find the first digit in the remaining
     * string b. Find the closest non-digit character to the left of this digit
     * c. Remove both characters (if left character exists) 3. Continue until no
     * digits remain
     *
     * Time Complexity: O(n²) where n is the length of the input string Space
     * Complexity: O(n) for the StringBuilder
     *
     * @param s The input string to process
     * @return The resulting string after removing all digits and their paired
     * characters
     * @throws NullPointerException if the input string is null
     */
    public String clearDigits(String s) {
        if (s == null) {
            throw new NullPointerException("Input string cannot be null");
        }

        // Convert to StringBuilder for efficient manipulation
        StringBuilder sb = new StringBuilder(s);

        while (true) {
            // Find the first digit
            int digitIndex = -1;
            for (int i = 0; i < sb.length(); i++) {
                if (Character.isDigit(sb.charAt(i))) {
                    digitIndex = i;
                    break;
                }
            }

            // If no digit found, we're done
            if (digitIndex == -1) {
                break;
            }

            // Find the closest non-digit to the left
            int leftCharIndex = -1;
            for (int i = digitIndex - 1; i >= 0; i--) {
                if (!Character.isDigit(sb.charAt(i))) {
                    leftCharIndex = i;
                    break;
                }
            }

            // Remove both characters
            // Remove the larger index first to maintain correct positions
            sb.deleteCharAt(digitIndex);
            if (leftCharIndex != -1) {
                sb.deleteCharAt(leftCharIndex);
            }
        }

        return sb.toString();
    }

    /**
     * Test cases to verify the functionality of clearDigits method.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Basic case with alternating letters and digits
        assert solution.clearDigits("a1b2c3").equals("c");

        // Test case 2: String with consecutive digits
        assert solution.clearDigits("ab12c").equals("ac");

        // Test case 3: String with no digits
        assert solution.clearDigits("abc").equals("abc");

        // Test case 4: String with only digits
        assert solution.clearDigits("123").equals("");

        // Test case 5: Empty string
        assert solution.clearDigits("").equals("");

        // Test case 6: String with digits at the beginning
        assert solution.clearDigits("1abc").equals("bc");

        // Test case 7: String with multiple consecutive non-digits
        assert solution.clearDigits("abc123def").equals("abdef");

        // Test case 8: Complex case with mixed characters
        assert solution.clearDigits("a1b2c3d4e5f").equals("f");

        System.out.println("All test cases passed!");
    }
}
