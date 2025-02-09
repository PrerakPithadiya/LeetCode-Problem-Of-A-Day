
/**
 * Definition for a binary tree node.
 */
class Node {

    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

/**
 * Solution class to find the maximum path sum in a binary tree. A path is
 * defined as any sequence of nodes from some starting node to any node in the
 * tree along the parent-child connections.
 */
class Solution {

    /**
     * Stores the maximum path sum found so far
     */
    int maxSum = Integer.MIN_VALUE;

    /**
     * Main function to find maximum path sum from any node in a tree.
     *
     * @param node Root node of the tree or subtree
     * @return Maximum path sum in the entire tree
     */
    public int findMaxSum(Node node) {
        if (node == null) {
            return 0;
        }

        maxPathSum(node);
        return maxSum;
    }

    /**
     * Helper function that performs the actual recursion to find maximum path
     * sum. For each node, it considers: 1. Path through the node, connecting
     * left and right subtrees 2. Path from node to either left or right subtree
     *
     * @param node Current node being processed
     * @return Maximum sum of any path starting from current node going
     * downwards
     */
    private int maxPathSum(Node node) {
        if (node == null) {
            return 0;
        }

        // Get maximum path sum from left and right subtrees
        // If they're negative, we'll use 0 instead (by taking Math.max with 0)
        int leftSum = Math.max(0, maxPathSum(node.left));
        int rightSum = Math.max(0, maxPathSum(node.right));

        // Update maxSum if current path (node + left + right) is greater
        maxSum = Math.max(maxSum, leftSum + rightSum + node.data);

        // Return maximum single path that can be used by parent node
        // (can only choose one path - either left or right)
        return Math.max(leftSum, rightSum) + node.data;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Simple tree with positive values
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        assert solution.findMaxSum(root1) == 6; // Path: 2 -> 1 -> 3

        // Test Case 2: Tree with negative values
        Node root2 = new Node(-10);
        root2.left = new Node(9);
        root2.right = new Node(20);
        root2.right.left = new Node(15);
        root2.right.right = new Node(7);
        assert solution.findMaxSum(root2) == 42; // Path: 15 -> 20 -> 7

        // Test Case 3: Single node
        Node root3 = new Node(5);
        assert solution.findMaxSum(root3) == 5;

        // Test Case 4: Empty tree
        assert solution.findMaxSum(null) == 0;

        // Test Case 5: Tree with all negative values
        Node root5 = new Node(-1);
        root5.left = new Node(-2);
        root5.right = new Node(-3);
        assert solution.findMaxSum(root5) == -1; // Single node is the maximum path

        System.out.println("All test cases passed!");
    }
}
