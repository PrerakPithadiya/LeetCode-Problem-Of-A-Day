/**
 * Recovery of Binary Tree from Preorder Traversal
 * 
 * This class provides a solution to recover a binary tree from its preorder traversal
 * representation where nodes are separated by dashes indicating their depth level.
 * 
 * Example format: "1-2--3--4-5--6--7"
 * Where:
 * - Each number represents a node value
 * - The number of dashes before a value indicates its depth in the tree
 * - Single dash (-) means depth 1, double dash (--) means depth 2, and so on
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    int index = 0;  // Global index to track string position

    /**
     * Recovers a binary tree from its preorder traversal string representation.
     * 
     * @param traversal The preorder traversal string with dash-separated nodes
     * @return The root node of the reconstructed binary tree
     */
    public TreeNode recoverFromPreorder(String traversal) {
        index = 0; // Reset index for multiple test cases
        return dfs(traversal, 0);
    }

    /**
     * Performs depth-first search to construct the binary tree.
     * 
     * @param s The preorder traversal string
     * @param depth Current depth level in the tree
     * @return TreeNode at the current position
     */
    private TreeNode dfs(String s, int depth) {
        if (index >= s.length()) {
            return null;
        }

        // Count dashes to determine current level
        int dashCount = 0;
        while (index + dashCount < s.length() && s.charAt(index + dashCount) == '-') {
            dashCount++;
        }

        // Return if depth doesn't match
        if (dashCount != depth) {
            return null;
        }

        // Skip the dashes
        index += dashCount;

        // Parse node value
        int val = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            val = val * 10 + (s.charAt(index++) - '0');
        }

        // Create new node
        TreeNode node = new TreeNode(val);

        // Recursively build left and right subtrees
        node.left = dfs(s, depth + 1);
        node.right = dfs(s, depth + 1);

        return node;
    }
}

public class Main {
    /**
     * Utility method to print the binary tree in an inorder traversal.
     */
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Case 1: Simple tree with three levels
        String test1 = "1-2--3--4-5--6--7";
        System.out.println("\nTest Case 1: " + test1);
        TreeNode result1 = solution.recoverFromPreorder(test1);
        System.out.print("Inorder traversal: ");
        printInorder(result1);
        
        // Test Case 2: Tree with single node
        String test2 = "1";
        System.out.println("\n\nTest Case 2: " + test2);
        TreeNode result2 = solution.recoverFromPreorder(test2);
        System.out.print("Inorder traversal: ");
        printInorder(result2);
        
        // Test Case 3: Tree with left-heavy structure
        String test3 = "1-2--3---4";
        System.out.println("\n\nTest Case 3: " + test3);
        TreeNode result3 = solution.recoverFromPreorder(test3);
        System.out.print("Inorder traversal: ");
        printInorder(result3);
    }
}