/*
1792. Maximum Average Pass Ratio
There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.



Example 1:

Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
Example 2:

Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485


Constraints:

1 <= classes.length <= 105
classes[i].length == 2
1 <= passi <= totali <= 105
1 <= extraStudents <= 105
 */

class Node implements Comparable<Node> {
    public double a;
    public double b;

    public Node(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public int compareTo(Node other) {
        double r1 = ((this.a+1)/(this.b+1))-(this.a/this.b);
        double r2 = ((other.a+1)/(other.b+1))-(other.a/other.b);
        if (r1 > r2) return -1;
        if (r1 == r2) return 0;
        return 1;

    }

}
class Solution {

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int[] cur_class:classes) {
            pq.add(new Node(cur_class[0], cur_class[1]));
        }

        while(extraStudents > 0) {
            Node c_class = pq.poll();
            c_class.a += 1;
            c_class.b += 1;
            extraStudents -= 1;
            pq.add(c_class);
        }

        return caucluateAvg(pq);

    }
    private double caucluateAvg(PriorityQueue<Node> pq) {
        double sum = 0.;
        int count = 0;
        while(!pq.isEmpty()) {
            Node c_class = pq.poll();
            sum += (c_class.a / c_class.b);
            count += 1;
        }
        return sum/count;
    }

}