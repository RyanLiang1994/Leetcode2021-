/*
505. The Maze II
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12

Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1

Explanation: There is no way for the ball to stop at the destination.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
class Solution {
    int[][] direction = new int[][]{{0, 1}, {0,-1}, {1, 0}, {-1, 0}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        int col = maze[0].length;
        int[][] dist = new int[row][col];
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {Arrays.fill(dist[i], Integer.MAX_VALUE);}
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(start[0], start[1], 0, -1));
        while(!queue.isEmpty()) {
            List<Integer> curr = queue.poll();
            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];
                if (i == curr.get(3)) {continue;} // we can't go back;
                int[] next_pos = new int[]{curr.get(0), curr.get(1)};
                int step = 0;
                while(true) {
                    next_pos[0] += dir[0];
                    next_pos[1] += dir[1];
                    if (isHitedWall(maze, next_pos)) {
                        next_pos[0] -= dir[0];
                        next_pos[1] -= dir[1];
                        break;
                    }
                    step += 1;
                }
                if (step == 0) {continue;} // we are not moving
                if (curr.get(2) + step < dist[next_pos[0]][next_pos[1]]) {
                    dist[next_pos[0]][next_pos[1]] = curr.get(2) + step; // mark as discovered
                    queue.add(Arrays.asList(next_pos[0], next_pos[1], curr.get(2) + step, i));
                }
                if (next_pos[0] == destination[0] && next_pos[1] == destination[1]) {
                    if (dist[next_pos[0]][next_pos[1]] < shortest) {shortest = dist[next_pos[0]][next_pos[1]];}
                    else {break;}
                }
            }
        }


        if (shortest == Integer.MAX_VALUE) {
            return -1;
        }
        return shortest;
    }

    boolean isHitedWall(int[][] maze, int[] pos) {

        int new_row = pos[0];
        int new_col = pos[1];
        if (new_row < 0 || new_col < 0 || new_row >= maze.length || new_col >= maze[0].length) {
            return true;
        }
        if (maze[new_row][new_col] == 1) {return true;}
        return false;
    }
}