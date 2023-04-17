package com.codegym.games.snake;

import com.codegym.engine.cell.*;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;

    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private int score;

    private void createNewApple(){
        Apple newApple;
        do {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            newApple = new Apple(x, y);
            apple = newApple;
        }while (snake.checkCollision(apple));
        apple = newApple;
    }


    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        turnDelay = 300;
        setTurnTimer(turnDelay);
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();
        isGameStopped = false;
        drawScene();
        score = 0;
        setScore(score);


    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.LIGHTBLUE, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn (int step) {
        snake.move(apple);
        if (apple.isAlive == false) {
            createNewApple();
            score +=5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        if (apple.isAlive == false){
            createNewApple();
        }
        if (snake.isAlive == false) {
            gameOver();
        }
        if(snake.getLength() > GOAL) {
            win();
        }
        
        drawScene();
    }
    //making snake movement possible
    @Override
    public void onKeyPress (Key key){
        if (key == Key.SPACE && isGameStopped == true) {
            createGame();
        }

        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        }
    }
    private void gameOver(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.RED, "You LOST!", Color.BLACK, 50);
    }
    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.DARKGREEN,"YOU WIN!", Color.BLACK, 50);
    }



}
