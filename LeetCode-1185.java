/*
1185. Day of the Week
Given a date, return the corresponding day of the week for that date.

The input is given as three integers representing the day, month and year respectively.

Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.



Example 1:

Input: day = 31, month = 8, year = 2019
Output: "Saturday"
Example 2:

Input: day = 18, month = 7, year = 1999
Output: "Sunday"
Example 3:

Input: day = 15, month = 8, year = 1993
Output: "Sunday"


Constraints:

The given dates are valid dates between the years 1971 and 2100.
 */
class Solution {
    String[] Week = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    int[] Days = new  int[] {31,28,31,30,31,30,31,31,30,31,30,31};
    int[] Leap_Days = new  int[] {31,29,31,30,31,30,31,31,30,31,30,31};
    public String dayOfTheWeek(int day, int month, int year) {
        int i = 5; // 1971.1.1 is Friday
        int pasted_days = dayBetween(1,1,1971, day, month, year);
        int j = pasted_days % 7;
        return Week[(i+j)%7];
    }

    // y2 > y1
    int dayBetween(int d1, int m1, int y1, int d2, int m2, int y2) {
        int days1 = (y2 - y1 - 1) * 365;
        int days2 = 0;
        int days3 = 365;
        int days4 = (y2 - y1 -1)/4;
        for (int i = 0; i < m2-1; i++) {
            if (y2 % 4 == 0 && y2 != 2100) {days2 += Leap_Days[i];}
            else {days2 += Days[i];}
        }
        days2 += d2;
        if (y2 % 4 == 0) {days2 -= 1;} // not sure why


        return days1 + days2 + days3 + days4;
    }

}