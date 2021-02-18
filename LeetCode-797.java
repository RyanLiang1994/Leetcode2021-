/*
797. All Paths From Source to Target
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1, and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).



Example 1:


Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Example 2:


Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
Example 3:

Input: graph = [[1],[]]
Output: [[0,1]]
Example 4:

Input: graph = [[1,2,3],[2],[3],[]]
Output: [[0,1,2,3],[0,2,3],[0,3]]
Example 5:

Input: graph = [[1,3],[2],[3],[]]
Output: [[0,1,2,3],[0,3]]


Constraints:

n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i (i.e., there will be no self-loops).
The input graph is guaranteed to be a DAG.
 */
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return dfs(graph);
    }

    private List<List<Integer>> dfs(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        // List<List<Integer>> result = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();
        int n = graph.length;
        stack.add(new ArrayList<>(Arrays.asList(0)));

        while (!stack.isEmpty()) {
            List<Integer> path = stack.pop();
            int curr_node = path.get(path.size()-1);
            int[] next = graph[curr_node];
            if (next.length == 0 && curr_node != n-1) {
                continue;
            }

            if (curr_node == n-1) {
                result.add(path);
                continue;
            }

            for (int j : next) {
                List<Integer> newList = new ArrayList<>(path);
                newList.add(j);
                stack.add(newList);
            }
        }
        return result;
    }
}