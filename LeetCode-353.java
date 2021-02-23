/*
353. Design Snake Game

Design a Snake game that is played on a device with screen size height x width. Play the game online if you are not familiar with the game.
The snake is initially positioned at the top left corner (0, 0) with a length of 1 unit.
You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.
Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until the snake eats the first piece of food.
When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body occupies after moving (i.e. a snake of length 4 cannot run into itself).

Implement the SnakeGame class:
    SnakeGame(int width, int height, int[][] food) Initializes the object with a screen of size height x width and the positions of the food.
    int move(String direction) Returns the score of the game after applying one direction move by the snake. If the game is over, return -1.


Example 1:
Input
["SnakeGame", "move", "move", "move", "move", "move", "move"]
[[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
Output
[null, 0, 0, 1, 1, 2, -1]

Explanation
SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
snakeGame.move("R"); // return 0
snakeGame.move("D"); // return 0
snakeGame.move("R"); // return 1, snake eats the first piece of food. The second piece of food appears
                     // at (0, 1).
snakeGame.move("U"); // return 1
snakeGame.move("L"); // return 2, snake eats the second food. No more food appears.
snakeGame.move("U"); // return -1, game over because snake collides with border


Constraints:
1 <= width, height <= 104
1 <= food.length <= 50
food[i].length == 2
0 <= ri < height
0 <= ci < width
direction.length == 1
direction is 'U', 'D', 'L', or 'R'.
At most 104 calls will be made to move.
 */
class SnakeGame {
    int score, width, height, food_pos;
    int[] position = new int[]{0,0};
    int[][] food;
    Queue<List<Integer>> queue;

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        score = 0;
        position = new int[]{0,0};
        food_pos = 0;
        this.width = width;
        this.height = height;
        this.food = food;
        queue = new LinkedList<>();
        queue.add(Arrays.asList(0,0));

    }
    private boolean isOver1(int x, int y) {
        return x < 0 || y < 0 || x >= this.width || y >= this.height;
    }

    private boolean isOver2(int x, int y) {
        return this.queue.contains(Arrays.asList(y,x));
    }
    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int dir = 0;
        int x = this.position[1];
        int y = this.position[0];
        switch (direction) {
            case "U":
                y -= 1;
                break;
            case "L":
                x -= 1;
                break;
            case "R":
                x += 1;
                break;
            case "D":
                y += 1;
                break;
        }

        if (isOver1(x, y)) {
            return -1;
        }
        // not out of board
        if (food_pos>= 0 && x == this.food[food_pos][1] && y == this.food[food_pos][0]) {
            //next position is to eat food
            this.score += 1;
            if (food_pos<food.length-1) {food_pos += 1;}else {food_pos=-1;}
        } else {
            // just move
            queue.poll();
            if (isOver2(x, y)) {
                return -1;
            }
        }
        this.position[1] = x;
        this.position[0] = y;
        queue.add(Arrays.asList(y, x));
        return this.score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */