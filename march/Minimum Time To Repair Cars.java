
/**
 * Solution class for the Car Repair Time Optimization Problem
 *
 * Problem Description:
 * Given an array of mechanics with their ranks and number of cars to repair,
 * find the minimum time needed to repair all cars.
 *
 * Key Points:
 * - Each mechanic can repair one car at a time
 * - Time taken by a mechanic = rank * n², where n is the number of cars
 * - Need to minimize the maximum time taken
 *
 * Algorithm:
 * - Uses Binary Search to find the optimal time
 * - For each time value, checks if all cars can be repaired
 * - Search space: 0 to (cars * cars * max_rank)
 *
 * Time Complexity: O(log(C²R) * M), where:
 * - C is the number of cars
 * - R is the maximum rank
 * - M is the number of mechanics
 *
 * Space Complexity: O(1)
 */
class Solution {

    /**
     * Main method to test the solution with various test cases
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case
        int[] ranks1 = {4, 2, 3, 1};
        int cars1 = 10;
        System.out.println("Test Case 1:");
        System.out.println("Ranks: " + java.util.Arrays.toString(ranks1));
        System.out.println("Cars: " + cars1);
        System.out.println("Minimum time: " + solution.repairCars(ranks1, cars1));
        System.out.println();

        // Test Case 2: Single mechanic
        int[] ranks2 = {5};
        int cars2 = 8;
        System.out.println("Test Case 2:");
        System.out.println("Ranks: " + java.util.Arrays.toString(ranks2));
        System.out.println("Cars: " + cars2);
        System.out.println("Minimum time: " + solution.repairCars(ranks2, cars2));
        System.out.println();

        // Test Case 3: Large number of cars
        int[] ranks3 = {1, 1, 1};
        int cars3 = 100;
        System.out.println("Test Case 3:");
        System.out.println("Ranks: " + java.util.Arrays.toString(ranks3));
        System.out.println("Cars: " + cars3);
        System.out.println("Minimum time: " + solution.repairCars(ranks3, cars3));
    }

    /**
     * Finds the minimum time needed to repair all cars
     *
     * @param ranks array of mechanic ranks
     * @param cars number of cars to repair
     * @return minimum time needed to repair all cars
     */
    public long repairCars(int[] ranks, int cars) {
        // Define the search space
        long left = 0;
        long right = (long) cars * cars * ranks[0]; // Upper bound: worst case if all cars are repaired by the slowest mechanic

        while (left < right) {
            long mid = left + (right - left) / 2;

            // Check if we can repair all cars in 'mid' time
            if (canRepairAllCars(ranks, cars, mid)) {
                right = mid; // Try to find a smaller time
            } else {
                left = mid + 1; // Need more time
            }
        }

        return left;
    }

    /**
     * Helper method to check if all cars can be repaired within given time
     *
     * @param ranks array of mechanic ranks
     * @param cars number of cars to repair
     * @param time time limit to check
     * @return true if all cars can be repaired within the time limit, false
     * otherwise
     */
    private boolean canRepairAllCars(int[] ranks, int cars, long time) {
        long totalCarsRepaired = 0;

        for (int rank : ranks) {
            // For each mechanic, calculate how many cars they can repair in 'time'
            // Formula: time = rank * n², therefore n = sqrt(time / rank)
            long carsRepairedByThisMechanic = (long) Math.sqrt(time / rank);
            totalCarsRepaired += carsRepairedByThisMechanic;

            if (totalCarsRepaired >= cars) {
                return true;
            }
        }

        return false;
    }
}
