/*
698. Partition to K Equal Sum Subsets

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.


Note:
1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */

// back tracking + recursion
//
class Solution {
    int[] visited;
    int avg;
    int[] array;
    public boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = 0;
        visited = new int[nums.length];
        array = nums;
        for (int num:nums) {
            sum += num;
        }
        avg = sum/k;
        if (sum%k != 0) {return false;}
        return backtrack(0, k, 0);
    }
    private boolean backtrack(int cur_sum, int k, int index) {
        if (k == 0) {return true;}
        if (cur_sum == avg) {
            return backtrack(0, k-1, 0);
        }
        for (int i = index; i < array.length; i++) {
            int cur = array[i];
            if (visited[i] == 0 && cur_sum <= avg) {
                visited[i] = 1;
                if (backtrack(cur_sum+cur, k, i)) {
                    return true;
                }
                visited[i] = 0;
            }
        }
        return false;
    }
}