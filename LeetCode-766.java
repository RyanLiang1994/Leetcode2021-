/*
Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.

A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.

Example 1:
Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: true
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.

Example 2:
Input: matrix = [[1,2],[2,2]]
Output: false
Explanation:
The diagonal "[1, 2]" has different elements.


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 20
0 <= matrix[i][j] <= 99
 */

class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        boolean result = false;
        int row = matrix.length;
        int col = matrix[0].length;


        if (col == 1 || row == 1) {
            return true;
        }
        for (int x=0; x < row-1; x++) {
            for (int y=col-1; y > 0; y--) {
                if (matrix[x+1][y] != matrix[x][y-1]) {
                    return false;
                }
            }
        }

        return true;

        //return checkDiagnoal(matrix, 0, row-1);
    }

    // private boolean checkDiagnoal(int[][] matrix, int x, int y) {
    //     // (x, y) is the right bottom corner
    //     if (x == matrix[0].length-1 || y == 0) {
    //         return true;
    //     } else {
    //         if (matrix[y-1][x] != matrix[y][x+1]) {
    //             return false;
    //         } else {
    //             if (checkDiagnoal(matrix, x, y-1)) {
    //                 return checkDiagnoal(matrix, x+1, y);
    //             } else {
    //                 return false;
    //             }
    //         }
    //     }
    // }

}
