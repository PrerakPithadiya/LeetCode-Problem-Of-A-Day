
import java.util.ArrayList;
import java.util.List;

/**
 * A class that maintains a running product of numbers and provides efficient
 * product queries for the last k numbers.
 *
 * This implementation uses a prefix product approach to achieve O(1) time
 * complexity for product queries and number additions.
 */
class ProductOfNumbers {

    /**
     * List to store prefix products of all numbers
     */
    private List<Integer> prefixProduct;

    /**
     * Initializes a new ProductOfNumbers instance. Creates an empty list and
     * adds 1 as the first element to handle edge cases.
     */
    public ProductOfNumbers() {
        prefixProduct = new ArrayList<>();
        prefixProduct.add(1);
    }

    /**
     * Adds a new number to the data structure.
     *
     * @param num The number to add Time Complexity: O(1)
     */
    public void add(int num) {
        if (num == 0) {
            // For zero, start a new prefix product
            prefixProduct = new ArrayList<>();
            prefixProduct.add(1);
        } else {
            // Multiply the last prefix product with current number
            prefixProduct.add(prefixProduct.get(prefixProduct.size() - 1) * num);
        }
    }

    /**
     * Returns the product of the last k numbers.
     *
     * @param k The number of last elements to consider
     * @return The product of the last k numbers, or 0 if k is larger than the
     * current size or if there's a zero in the last k numbers Time Complexity:
     * O(1)
     */
    public int getProduct(int k) {
        int n = prefixProduct.size();
        if (k >= n) {
            return 0;
        }
        return prefixProduct.get(n - 1) / prefixProduct.get(n - k - 1);
    }

    /**
     * Main method with test cases to demonstrate the functionality.
     */
    public static void main(String[] args) {
        // Test Case 1: Basic operations
        ProductOfNumbers prod1 = new ProductOfNumbers();
        prod1.add(3);    // [3]
        prod1.add(2);    // [3,2]
        prod1.add(4);    // [3,2,4]
        System.out.println(prod1.getProduct(2));  // Expected: 8 (2*4)
        System.out.println(prod1.getProduct(3));  // Expected: 24 (3*2*4)

        // Test Case 2: Handling zeros
        ProductOfNumbers prod2 = new ProductOfNumbers();
        prod2.add(3);    // [3]
        prod2.add(0);    // [0]
        prod2.add(2);    // [2]
        System.out.println(prod2.getProduct(2));  // Expected: 0
        System.out.println(prod2.getProduct(1));  // Expected: 2

        // Test Case 3: Large numbers
        ProductOfNumbers prod3 = new ProductOfNumbers();
        prod3.add(2);    // [2]
        prod3.add(5);    // [2,5]
        prod3.add(3);    // [2,5,3]
        prod3.add(4);    // [2,5,3,4]
        System.out.println(prod3.getProduct(4));  // Expected: 120 (2*5*3*4)
        System.out.println(prod3.getProduct(2));  // Expected: 12 (3*4)
    }
}
