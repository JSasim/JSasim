package com.codegym.games.snake;

import com.codegym.engine.cell.Game;
import org.junit.jupiter.api.*;

import static com.codegym.games.snake.Direction.LEFT;
import static com.codegym.games.snake.SnakeGame.HEIGHT;
import static com.codegym.games.snake.SnakeGame.WIDTH;

class SnakeTest {
    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll");
    }

    @Test
    //checking if test can fail
    void wrongTest() {
    int result = 2+2;
        Assertions.assertEquals(1, result, "This test is meant to be failed");

    }
    @Test
    @DisplayName("Testing size of the board")
    void realTest() {
        SnakeGame snakeGame = new SnakeGame();
        Assertions.assertEquals(15, snakeGame.WIDTH, "Width should equal 15");
        Assertions.assertEquals(15, snakeGame.HEIGHT, "Height should equal 15");
    }
    @Test
    @DisplayName("Testing Snake")
    void realTest2() {
        //testing if snake class returns correct values

        Snake snake = new Snake(1, 1);
        Assertions.assertEquals(true, snake.isAlive, "snake should be alive");
        snake.checkCollision(new GameObject(1, 1));
        Assertions.assertEquals(true, snake.checkCollision(new GameObject(1, 1)));
        Assertions.assertEquals(3, snake.getLength());
    }
    @Test
    @DisplayName("Testing Apple")
    void realTest3() {
        Apple apple = new Apple(1,1);
        Assertions.assertEquals(true, apple.isAlive,"Apple should be alive");;
        }
    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }


    }

