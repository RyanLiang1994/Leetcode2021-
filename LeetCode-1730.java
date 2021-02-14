/*
1730. Shortest Path to Get Food
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.



Example 1:


Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 steps to reach the food.
Example 2:


Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
Output: -1
Explanation: It is not possible to reach the food.
Example 3:


Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
Output: 6
Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
Example 4:

Input: grid = [["O","*"],["#","O"]]
Output: 2
Example 5:

Input: grid = [["X","*"],["#","X"]]
Output: -1


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[row][col] is '*', 'X', 'O', or '#'.
The grid contains exactly one '*'.
 */
class Solution {
    int[][] directions = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
    public int getFood(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        if (row == 1 && col == 1) {
            return -1;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '*') {
                    return bfs(grid, i, j);
                }
            }
        }

        return -1;
    }
    private int bfs(char[][] grid, int start_x, int start_y) {
        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        grid[start_x][start_y] = 'X';
        queue.add(new int[] {start_x, start_y, 0});       // adding the current point
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] d : directions) {
                int row_t = current[0] + d[0];
                int col_t = current[1] + d[1];

                if (row_t < 0 || col_t < 0 || row_t > row-1 || col_t > col-1 || grid[row_t][col_t] == 'X') {
                    // skip this cell
                    continue;
                }
                if (grid[row_t][col_t] == '#') {
                    // food
                    return current[2]+1;
                }
                // 'O' cell
                queue.add(new int[] {row_t, col_t, current[2]+1});
                grid[row_t][col_t] = 'X'; // in case we go back;
            }
        }

        return -1;

    }

}