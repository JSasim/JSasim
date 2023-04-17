package com.codegym.games.snake;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static String HEAD_SIGN = "\uD83D\uDC39";
    private final static String BODY_SIGN = "\uD83D\uDC14";


    private List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    //when snake hits the end of the board, it dies
    public void move(Apple apple) {
        GameObject newHead = createNewHead();
        if (newHead.x >= SnakeGame.WIDTH
                || newHead.x < 0
                || newHead.y >= SnakeGame.HEIGHT
                || newHead.y < 0) {
            isAlive = false;
            return;
        }
       if (checkCollision(newHead)){
           isAlive = false;
           return;
        }
        snakeParts.add(0, newHead);

        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        } else {
            removeTail();
        }
    }
    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {
        Color color = isAlive ? Color.DARKORANGE : Color.RED;

        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject part = snakeParts.get(i);
            String sign = (i != 0) ? BODY_SIGN : HEAD_SIGN;
            game.setCellValueEx(part.x, part.y, Color.NONE, sign, color, 75);
        }
    }

    public void setDirection(Direction direction) {
        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && snakeParts.get(0).x == snakeParts.get(1).x)
        { return; }
        if ((this.direction == Direction.DOWN || this.direction == Direction.UP) && snakeParts.get(0).y == snakeParts.get(1).y)
        {return; }



       //snake cannot turn around to go the opposite direction
        if  ((direction == Direction.UP && this.direction == Direction.DOWN)
            || (direction == Direction.DOWN && this.direction == Direction.UP)
            || (direction == Direction.RIGHT && this.direction == Direction.LEFT)
            || (direction == Direction.LEFT && this.direction == Direction.RIGHT))
            return;
            this.direction=direction;

    }
    public GameObject createNewHead() {
        GameObject oldHead = snakeParts.get(0);
        if (direction == Direction.LEFT) {
            return new GameObject(oldHead.x - 1, oldHead.y);
        } else if (direction == Direction.RIGHT) {
            return new GameObject(oldHead.x + 1, oldHead.y);
        } else if (direction == Direction.UP) {
            return new GameObject(oldHead.x, oldHead.y - 1);
        } else {
            return new GameObject(oldHead.x, oldHead.y + 1);
        }
    }
    public void removeTail() {
        snakeParts.remove(snakeParts.size() -1);
    }
    public boolean checkCollision (GameObject gameObject) {
        for (GameObject part : snakeParts) {
            if (part.x == gameObject.x && part.y == gameObject.y) {
                return true;
            }
        }
        return false;
    }
    public int getLength(){
        return snakeParts.size();
    }
}



