/*
1244. Design A Leaderboard

Design a Leaderboard class, which has 3 functions:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
Initially, the leaderboard is empty.



Example 1:

Input:
["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
Output:
[null,null,null,null,null,null,73,null,null,null,141]

Explanation:
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
leaderboard.top(1);           // returns 73;
leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3);           // returns 141 = 51 + 51 + 39;


Constraints:

1 <= playerId, K <= 10000
It's guaranteed that K is less than or equal to the current number of players.
1 <= score <= 100
There will be at most 1000 function calls.
 */

class Leaderboard {
    Map<Integer, Integer> score_map = new HashMap<>();
    Map<Integer, Integer> rank = new TreeMap<>(Collections.reverseOrder());
    public Leaderboard() {
        score_map.clear();
        rank.clear();
    }

    public void addScore(int playerId, int score) {

        if(score_map.containsKey(playerId)) {
            int old_score = score_map.get(playerId);
            int new_score = old_score + score;
            score_map.put(playerId, new_score);
            if (rank.get(old_score) == 1) {
                rank.remove(old_score);
            } else {
                rank.put(old_score, rank.get(old_score)-1);
            }
            rank.put(new_score, rank.getOrDefault(new_score, 0) + 1);

        } else {
            score_map.put(playerId, score);
            rank.put(score, rank.getOrDefault(score, 0) + 1);
        }

    }

    public int top(int K) {
        Iterator<Map.Entry<Integer, Integer>> it = rank.entrySet().iterator();
        int i = 0;
        int sum = 0;
        while (it.hasNext() && i < K) {
            Map.Entry<Integer, Integer> entry = it.next();
            int score = entry.getKey();
            int count = entry.getValue();
            while (count > 0 && i < K) {
                sum += score;
                i += 1;
                count -= 1;
            }
        }
        return sum;
    }

    public void reset(int playerId) {
        int curr_score = score_map.get(playerId);
        score_map.remove(playerId, curr_score);
        if (rank.get(curr_score) == 1) {
            rank.remove(curr_score);
        } else {
            rank.put(curr_score, rank.get(curr_score)-1);
        }
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */