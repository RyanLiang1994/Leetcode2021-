/*
681. Next Closest Time
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.



Example 1:

Input: time = "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: time = "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.


Constraints:

time.length == 5
time is a valid time in the form "HH:MM".
0 <= HH < 24
0 <= MM < 60
 */

class Solution {
    public String nextClosestTime(String time) {
        int[] mins = new int[]{Character.getNumericValue(time.charAt(3)), Character.getNumericValue(time.charAt(4))};
        int[] hours = new int[]{Character.getNumericValue(time.charAt(0)), Character.getNumericValue(time.charAt(1))};
        int[] usuable_int = new int[]{hours[0], hours[1], mins[0], mins[1]};
        Arrays.sort(usuable_int);
        int current_min = toMins(hours[0], hours[1], mins[0], mins[1]);
        int closet_min = 0;

        for (int i : usuable_int) { // O(4)
            if (i <= 2) {
                for (int j : usuable_int) { // O(4)
                    if (i*10 + j <24) {
                        for (int k: usuable_int) { // O(4)
                            if(k < 6) {
                                for (int l: usuable_int) { // O(4)
                                    if (k*10 + l < 60) {
                                        int temp_min = toMins(i, j,k,l);
                                        if (temp_min > current_min) {
                                            return String.format("%d%d:%d%d", i,j,k,l);
                                        }
                                    }

                                }
                            }

                        }
                    }
                }
            }

        } // O(256)
        // didn't return means we passed 24 hours. return the minimum possible result;
        // Since the "time" is always valid, we can ensure that the min digit is always valid (less than or equal to 2).
        return String.format("%d%d:%d%d", usuable_int[0],usuable_int[0],usuable_int[0],usuable_int[0]);

    }

    private int toMins(int h1, int h2, int m1, int m2) {
        return (h1*10 + h2) * 60 + m1*10 + m2;
    }
}