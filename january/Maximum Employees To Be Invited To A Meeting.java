
import java.util.*;

/**
 * Solution class for the Maximum Employees to be Invited to a Meeting problem.
 * This class provides methods to calculate the maximum number of employees that
 * can be invited to a meeting based on their favorite colleague preferences.
 */
class Solution {

    /**
     * Performs a breadth-first search to calculate the maximum distance from a
     * given start node.
     *
     * @param startNode The starting node for BFS traversal
     * @param visitedNodes Set of nodes that have already been visited
     * @param reversedGraph Graph representing reversed relationships between
     * employees
     * @return The maximum distance found from the start node
     */
    private int bfs(
            int startNode,
            Set<Integer> visitedNodes,
            List<List<Integer>> reversedGraph
    ) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startNode, 0});
        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            for (int neighbor : reversedGraph.get(currentNode)) {
                if (visitedNodes.contains(neighbor)) {
                    continue;
                }
                visitedNodes.add(neighbor);
                queue.offer(new int[]{neighbor, currentDistance + 1});
                maxDistance = Math.max(maxDistance, currentDistance + 1);
            }
        }
        return maxDistance;
    }

    /**
     * Calculates the maximum number of employees that can be invited to a
     * meeting.
     *
     * @param favorite Array where favorite[i] represents the favorite colleague
     * of employee i
     * @return Maximum number of employees that can be invited
     */
    public int maximumInvitations(int[] favorite) {
        int numPeople = favorite.length;
        List<List<Integer>> reversedGraph = new ArrayList<>(numPeople);

        // Build the reversed graph where each node points to its admirers
        for (int i = 0; i < numPeople; i++) {
            reversedGraph.add(new ArrayList<>());
        }
        for (int person = 0; person < numPeople; person++) {
            reversedGraph.get(favorite[person]).add(person);
        }

        int longestCycle = 0;
        int twoCycleInvitations = 0;
        boolean[] visited = new boolean[numPeople];

        // Find all cycles in the graph
        for (int person = 0; person < numPeople; person++) {
            if (!visited[person]) {
                Map<Integer, Integer> visitedPersons = new HashMap<>();
                int currentPerson = person;
                int distance = 0;

                while (true) {
                    if (visited[currentPerson]) {
                        break;
                    }
                    visited[currentPerson] = true;
                    visitedPersons.put(currentPerson, distance++);
                    int nextPerson = favorite[currentPerson];

                    // Cycle detected
                    if (visitedPersons.containsKey(nextPerson)) {
                        int cycleLength = distance - visitedPersons.get(nextPerson);
                        longestCycle = Math.max(longestCycle, cycleLength);

                        // Handle cycles of length 2
                        if (cycleLength == 2) {
                            Set<Integer> visitedNodes = new HashSet<>();
                            visitedNodes.add(currentPerson);
                            visitedNodes.add(nextPerson);
                            twoCycleInvitations += 2
                                    + bfs(nextPerson, visitedNodes, reversedGraph)
                                    + bfs(currentPerson, visitedNodes, reversedGraph);
                        }
                        break;
                    }
                    currentPerson = nextPerson;
                }
            }
        }
        return Math.max(longestCycle, twoCycleInvitations);
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Simple cycle of length 3
        int[] test1 = {2, 2, 1, 2};
        assert solution.maximumInvitations(test1) == 3 : "Test case 1 failed";

        // Test Case 2: Two separate cycles
        int[] test2 = {1, 0, 3, 2};
        assert solution.maximumInvitations(test2) == 4 : "Test case 2 failed";

        // Test Case 3: Single two-cycle with extensions
        int[] test3 = {3, 0, 1, 4, 1};
        assert solution.maximumInvitations(test3) == 4 : "Test case 3 failed";

        // Test Case 4: Multiple two-cycles
        int[] test4 = {1, 0, 3, 2, 5, 4};
        assert solution.maximumInvitations(test4) == 6 : "Test case 4 failed";

        // Test Case 5: Single long cycle
        int[] test5 = {1, 2, 3, 4, 0};
        assert solution.maximumInvitations(test5) == 5 : "Test case 5 failed";

        System.out.println("All test cases passed!");
    }
}
