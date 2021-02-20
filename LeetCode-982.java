/*
982. Triples with Bitwise AND Equal To Zero

Given an array of integers A, find the number of triples of indices (i, j, k) such that:

0 <= i < A.length
0 <= j < A.length
0 <= k < A.length
A[i] & A[j] & A[k] == 0, where & represents the bitwise-AND operator.


Example 1:

Input: [2,1,3]
Output: 12
Explanation: We could choose the following i, j, k triples:
(i=0, j=0, k=1) : 2 & 2 & 1
(i=0, j=1, k=0) : 2 & 1 & 2
(i=0, j=1, k=1) : 2 & 1 & 1
(i=0, j=1, k=2) : 2 & 1 & 3
(i=0, j=2, k=1) : 2 & 3 & 1
(i=1, j=0, k=0) : 1 & 2 & 2
(i=1, j=0, k=1) : 1 & 2 & 1
(i=1, j=0, k=2) : 1 & 2 & 3
(i=1, j=1, k=0) : 1 & 1 & 2
(i=1, j=2, k=0) : 1 & 3 & 2
(i=2, j=0, k=1) : 3 & 2 & 1
(i=2, j=1, k=0) : 3 & 1 & 2


Note:

1 <= A.length <= 1000
0 <= A[i] < 2^16
 */

class Solution {
    public int countTriplets(int[] A) {
        // return  bruteForce(A);
        return  quickTriplets(A);
        // return quickerTriplets(A);

    }

    private int bruteForce(int[] A) {
        int count = 0;
        int len = A.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    if(((A[i] & A[j]) & A[k]) == 0) {
                        count += 1;
                    }
                }
            }
        }

        return count;
    }

    private int quickTriplets(int[] A) {
        int len = A.length;
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int temp = A[i] & A[j];
                if (map.containsKey(temp)) {map.put(temp, map.get(temp)+1);}
                else {map.put(temp, 1);}
            }
        }
        for (int k = 0; k < len; k++) {
            for (int key : map.keySet()) {
                if ((A[k] & key) == 0) {
                    result += map.get(key);
                }
            }
        }
        return result;
    }

    public int quickerTriplets(int[] A) {
        int N = 1 << 16, M = 3;
        int[][] dp = new int[M + 1][N];
        dp[0][N - 1] = 1;
        for (int i = 0; i < M; i++) {
            for (int k = 0; k < N; k++) {
                for (int a : A) {
                    dp[i + 1][k & a] += dp[i][k];
                }
            }
        }
        return dp[M][0];
    }
}