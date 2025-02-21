
/**
 * LeetCode Problem: Find Elements in a Contaminated Binary Tree
 *
 * Problem Description:
 * Given a binary tree with nodes where all node values are -1 (contaminated),
 * recover the tree by setting node values according to the following rules:
 * - The root node's value is 0
 * - If a node's value is x, then:
 *   - Left child's value is 2 * x + 1
 *   - Right child's value is 2 * x + 2
 *
 * Time Complexity:
 * - Constructor: O(n) where n is number of nodes
 * - find(): O(1)
 *
 * Space Complexity: O(n) for storing all valid values
 */
import java.util.*;

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class FindElements {

    private Set<Integer> values;

    /**
     * Initializes the FindElements class and recovers the contaminated tree
     *
     * @param root Root node of the contaminated binary tree
     */
    public FindElements(TreeNode root) {
        values = new HashSet<>();
        recoverTree(root, 0);
    }

    /**
     * Recursively recovers the tree by setting correct values for each node
     *
     * @param node Current node being processed
     * @param val Value to be assigned to current node
     */
    private void recoverTree(TreeNode node, int val) {
        if (node == null) {
            return;
        }

        node.val = val;
        values.add(val);

        recoverTree(node.left, 2 * val + 1);
        recoverTree(node.right, 2 * val + 2);
    }

    /**
     * Checks if a value exists in the recovered tree
     *
     * @param target Value to search for
     * @return true if value exists, false otherwise
     */
    public boolean find(int target) {
        return values.contains(target);
    }

    /**
     * Test cases to validate the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Simple tree with 3 nodes
        TreeNode root1 = new TreeNode(-1);
        root1.left = new TreeNode(-1);
        root1.right = new TreeNode(-1);
        FindElements fe1 = new FindElements(root1);
        System.out.println("Test Case 1:");
        System.out.println("Find 1: " + fe1.find(1));  // true
        System.out.println("Find 2: " + fe1.find(2));  // true
        System.out.println("Find 3: " + fe1.find(3));  // false

        // Test Case 2: Empty tree
        FindElements fe2 = new FindElements(null);
        System.out.println("\nTest Case 2:");
        System.out.println("Find 0: " + fe2.find(0));  // false

        // Test Case 3: Larger tree
        TreeNode root3 = new TreeNode(-1);
        root3.left = new TreeNode(-1);
        root3.right = new TreeNode(-1);
        root3.left.left = new TreeNode(-1);
        FindElements fe3 = new FindElements(root3);
        System.out.println("\nTest Case 3:");
        System.out.println("Find 3: " + fe3.find(3));  // true
        System.out.println("Find 4: " + fe3.find(4));  // false
        System.out.println("Find 5: " + fe3.find(5));  // false
    }
}
