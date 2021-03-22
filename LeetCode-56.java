/*
56. Merge Intervals

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */

class Solution {

    public int[][] merge(int[][] intervals) {
        int left = 0;
        int right = 0;
        List<int[]> result = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) ->
                ((int[])a)[0] - ((int[])b)[0]
        );

        for (int[]interval:intervals) { //n
            queue.add(interval); // log(n)
        }// O(nlog(n))
        int[] temp = queue.poll();
        left = temp[0];
        right = temp[1];

        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp[0] <= right) {
                //overlapping
                right = Math.max(temp[1], right);
            } else {
                result.add(new int[]{left, right});
                left = temp[0];
                right = temp[1];
            }

        } //O(n)
        result.add(new int[]{left, right});
        return result.toArray(new int[result.size()][]);
    }// Total: O(nlog(n) + n) -> O(nlog(n))
}