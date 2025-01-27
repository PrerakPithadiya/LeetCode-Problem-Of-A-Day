
import java.util.*;

/**
 * Solution for LeetCode Course Schedule IV problem
 *
 * Problem: Given a number of courses and prerequisites, determine if specific
 * courses are prerequisites of others based on direct or indirect
 * relationships.
 *
 * Time Complexity: O(n * (V + E)) where: - n is the number of courses - V is
 * the number of vertices (courses) - E is the number of edges (prerequisites)
 *
 * Space Complexity: O(n^2) for the isReachable matrix
 */
class Solution {

    /**
     * Determines if courses are prerequisites based on given relationships
     *
     * @param numCourses Total number of courses (labeled from 0 to
     * numCourses-1)
     * @param prerequisites Array of prerequisite relationships where
     * prerequisites[i] = [a,b] means course 'a' must be taken before course 'b'
     * @param queries Array of queries where queries[i] = [u,v] asks if course
     * 'u' is a prerequisite of course 'v'
     * @return List of boolean values answering each query
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Matrix to store reachability between courses
        boolean[][] isReachable = new boolean[numCourses][numCourses];

        // Build the adjacency list representation of the graph
        @SuppressWarnings("unchecked")
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            graph[pre[0]].add(pre[1]);
        }

        // Perform BFS from each course to find all reachable courses
        for (int i = 0; i < numCourses; i++) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            while (!queue.isEmpty()) {
                int course = queue.poll();
                for (int nextCourse : graph[course]) {
                    if (!isReachable[i][nextCourse]) {
                        isReachable[i][nextCourse] = true;
                        queue.offer(nextCourse);
                    }
                }
            }
        }

        // Process queries using the precomputed reachability matrix
        List<Boolean> answer = new ArrayList<>();
        for (int[] query : queries) {
            answer.add(isReachable[query[0]][query[1]]);
        }

        return answer;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic prerequisite chain
        int[][] prerequisites1 = {{1, 0}};
        int[][] queries1 = {{1, 0}, {0, 1}};
        List<Boolean> result1 = solution.checkIfPrerequisite(2, prerequisites1, queries1);
        System.out.println("Test Case 1: " + result1); // Expected: [true, false]

        // Test Case 2: Multiple prerequisites
        int[][] prerequisites2 = {{1, 0}, {2, 1}, {2, 0}};
        int[][] queries2 = {{1, 0}, {2, 0}, {2, 1}, {1, 2}};
        List<Boolean> result2 = solution.checkIfPrerequisite(3, prerequisites2, queries2);
        System.out.println("Test Case 2: " + result2); // Expected: [true, true, true, false]

        // Test Case 3: No prerequisites
        int[][] prerequisites3 = {};
        int[][] queries3 = {{1, 0}, {0, 1}};
        List<Boolean> result3 = solution.checkIfPrerequisite(2, prerequisites3, queries3);
        System.out.println("Test Case 3: " + result3); // Expected: [false, false]

        // Test Case 4: Cyclic prerequisites
        int[][] prerequisites4 = {{1, 0}, {0, 2}, {2, 1}};
        int[][] queries4 = {{1, 2}, {1, 0}, {2, 0}};
        List<Boolean> result4 = solution.checkIfPrerequisite(3, prerequisites4, queries4);
        System.out.println("Test Case 4: " + result4); // Expected: [true, true, true]
    }
}
