/*
1129. Shortest Path with Alternating Colors
Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.

Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.

Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).



Example 1:

Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
Output: [0,1,-1]
Example 3:

Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
Output: [0,-1,-1]
Example 4:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
Output: [0,1,2]
Example 5:

Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
Output: [0,1,1]


Constraints:

1 <= n <= 100
red_edges.length <= 400
blue_edges.length <= 400
red_edges[i].length == blue_edges[i].length == 2
0 <= red_edges[i][j], blue_edges[i][j] < n
 */
class Solution {
    Map<Integer, List<Integer>> red_map ;
    Map<Integer, List<Integer>> blue_map ;
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[] result = new int[n];
        red_map = new HashMap<>();
        blue_map = new HashMap<>();
        HashSet<Integer> discovered = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        Arrays.fill(result, -1);
        result[0] = 0;
        for (int[] edge : red_edges) {
            List<Integer> lst = red_map.getOrDefault(edge[0], new ArrayList<Integer>());
            lst.add(edge[1]);
            red_map.put(edge[0], lst);
            discovered.add(edge[1]);
            discovered.add(edge[0]);
        }

        for (int[] edge : blue_edges) {
            List<Integer> lst = blue_map.getOrDefault(edge[0], new ArrayList<Integer>());
            lst.add(edge[1]);
            blue_map.put(edge[0], lst);
            discovered.add(edge[1]+n);
            discovered.add(edge[0]+n);
        }

        // Entrance
        if (red_map.containsKey(0)) {
            q.add(new int[]{0,0,0}); // {nodeID, from Red/Blue, step_count}
        }
        if (blue_map.containsKey(0)) {
            q.add(new int[]{0,1,0});
        }

        Map<Integer, List<Integer>> tempMap;

        while (!(q.isEmpty() || discovered.isEmpty())) {
            int[] curr_node = q.poll();
            if (curr_node[1] == 0) {
                tempMap =red_map;
                if (!discovered.remove(curr_node[0])) {
                    // first 0-(n-1) is red
                    continue;
                }
            } else {
                tempMap = blue_map;
                if (!discovered.remove(curr_node[0]+n)) {
                    // n - (2n-1) is blue
                    continue;
                }
            }

            if (tempMap.containsKey(curr_node[0])) {
                List<Integer> templst = tempMap.get(curr_node[0]);
                for (int j : templst) {
                    // only we have updated the result array will be added into the queue
                    if (result[j] < 0) {
                        result[j] = curr_node[2]+1;
                    } else {
                        result[j] = Math.min(result[j], curr_node[2]+1);
                    }
                    if (curr_node[1] == 0) {
                        q.add(new int[]{j, 1, curr_node[2]+1});
                    } else {
                        q.add(new int[]{j, 0, curr_node[2]+1});
                    }

                }
            } else {
                // current map doesn't have the current node, we don't care about it
                continue;
            }
        }
        return result;
    }
}