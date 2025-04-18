/**
 * Optimized solution for the Count and Say problem
 * 
 * Time Complexity: O(n * L) where L is the length of the result string at each step
 * Space Complexity: O(L) where L is the length of the result string
 */
public class CountAndSaySolution {
    
    public static void main(String[] args) {
        CountAndSaySolution solution = new CountAndSaySolution();
        
        // Test cases
        System.out.println("n=1: " + solution.countAndSay(1)); // "1"
        System.out.println("n=4: " + solution.countAndSay(4)); // "1211"
        System.out.println("n=10: " + solution.countAndSay(10)); // "13211311123113112211"
    }
    
    /**
     * Returns the nth term in the count-and-say sequence.
     * 
     * Optimization techniques:
     * 1. Using StringBuilder instead of String concatenation to avoid creating multiple string objects
     * 2. Direct character comparison instead of substring operations
     * 3. Iterative approach to avoid recursion overhead
     * 4. Single-pass counting of consecutive characters
     * 
     * @param n The position in the sequence (1 ≤ n ≤ 30)
     * @return The nth term in the count-and-say sequence
     */
    public String countAndSay(int n) {
        if (n <= 0) {
            return "";
        }
        
        // Base case
        String result = "1";
        
        // Generate the sequence iteratively
        for (int i = 2; i <= n; i++) {
            result = getNextSequence(result);
        }
        
        return result;
    }
    
    /**
     * Helper method to generate the next sequence based on the current one
     * using the run-length encoding approach.
     */
    private String getNextSequence(String current) {
        StringBuilder next = new StringBuilder();
        
        // Initialize with the first character
        char previousChar = current.charAt(0);
        int count = 1;
        
        // Process the rest of the string
        for (int i = 1; i < current.length(); i++) {
            char currentChar = current.charAt(i);
            
            if (currentChar == previousChar) {
                // Increment count for consecutive identical characters
                count++;
            } else {
                // Append the count and character to the result
                next.append(count).append(previousChar);
                
                // Reset for the new character
                previousChar = currentChar;
                count = 1;
            }
        }
        
        // Don't forget to append the last group
        next.append(count).append(previousChar);
        
        return next.toString();
    }
    
    /**
     * Alternative implementation using the hint approach with two helper functions.
     * This demonstrates the approach mentioned in the hints but the above solution
     * is more efficient for this specific problem.
     */
    public String countAndSayUsingHints(int n) {
        if (n == 1) return "1";
        
        String prev = "1";
        for (int i = 2; i <= n; i++) {
            // Step 1: Convert string to frequency pairs
            int[][] pairs = mapToFrequencyPairs(prev);
            
            // Step 2: Convert pairs back to string
            prev = createStringFromPairs(pairs);
        }
        
        return prev;
    }
    
    /**
     * Helper function that maps a string to pairs of [digit, frequency]
     * For example: "22331" -> [[2,2], [3,2], [1,1]]
     */
    private int[][] mapToFrequencyPairs(String s) {
        if (s == null || s.isEmpty()) return new int[0][2];
        
        // Count consecutive digits
        int count = 1;
        char current = s.charAt(0);
        StringBuilder compressed = new StringBuilder();
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == current) {
                count++;
            } else {
                compressed.append(count).append(current);
                current = s.charAt(i);
                count = 1;
            }
        }
        compressed.append(count).append(current);
        
        // Convert to pairs
        String result = compressed.toString();
        int[][] pairs = new int[result.length()/2][2];
        
        for (int i = 0; i < result.length(); i += 2) {
            pairs[i/2][0] = result.charAt(i) - '0';     // Count
            pairs[i/2][1] = result.charAt(i+1) - '0';   // Digit
        }
        
        return pairs;
    }
    
    /**
     * Helper function that creates a string from frequency pairs
     * For example: [[2,2], [3,2], [1,1]] -> "22321"
     */
    private String createStringFromPairs(int[][] pairs) {
        StringBuilder result = new StringBuilder();
        
        for (int[] pair : pairs) {
            result.append(pair[0]).append(pair[1]);
        }
        
        return result.toString();
    }
}
