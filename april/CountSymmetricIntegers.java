class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        
        // Get the number of digits in low and high
        int lowDigits = getDigitCount(low);
        int highDigits = getDigitCount(high);
        
        // Process each digit length separately
        for (int digitLength = lowDigits; digitLength <= highDigits; digitLength++) {
            // Skip odd-length numbers - they can't be symmetric
            if (digitLength % 2 != 0) continue;
            
            // Calculate range for current digit length
            int rangeStart = (digitLength == lowDigits) ? low : (int)Math.pow(10, digitLength - 1);
            int rangeEnd = (digitLength == highDigits) ? high : (int)Math.pow(10, digitLength) - 1;
            
            // Count symmetric numbers based on digit length
            if (digitLength == 2) {
                // For 2-digit numbers: only 11, 22, 33, ..., 99 are symmetric
                for (int i = 1; i <= 9; i++) {
                    int num = i * 11; // Shorthand for i*10 + i
                    if (num >= rangeStart && num <= rangeEnd) {
                        count++;
                    }
                }
            } else if (digitLength == 4) {
                // For 4-digit numbers: abcd is symmetric if a+b = c+d
                // Optimize by avoiding division operations where possible
                for (int num = rangeStart; num <= rangeEnd; num++) {
                    int d = num % 10;          // Last digit
                    int c = (num / 10) % 10;    // Third digit
                    int b = (num / 100) % 10;   // Second digit
                    int a = num / 1000;         // First digit
                    
                    if (a + b == c + d) {
                        count++;
                    }
                }
            }
            // We don't need to handle other even digit lengths for this problem's constraints
        }
        
        return count;
    }
    
    // Helper method to get the number of digits in a number
    private int getDigitCount(int num) {
        if (num == 0) return 1;
        return (int)Math.log10(num) + 1;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        System.out.println("Example 1:");
        System.out.println("Input: low = 1, high = 100");
        System.out.println("Output: " + solution.countSymmetricIntegers(1, 100));
        System.out.println("Expected: 9");
        
        // Test case 2
        System.out.println("\nExample 2:");
        System.out.println("Input: low = 1200, high = 1230");
        System.out.println("Output: " + solution.countSymmetricIntegers(1200, 1230));
        System.out.println("Expected: 4");
    }
}
