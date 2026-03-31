package cn.shanyue.snake.domain;

import cn.shanyue.snake.enums.Direction;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Snake {


    /**
     * 个性化参数
     */
    private String name;

    private Color color;


    /**
     * 核心参数
     */

    private List<Point> body;

    private Direction direction;

    private boolean isAlive;

    private int speed;

    private int length;

    private int score;


    public Snake(String name, Color color) {
        this.name = name;
        this.color = color;
        this.speed = 100;
        this.isAlive = true;
        this.score = 0;


        body = new LinkedList<>();
        body.add(new Point(10, 10));
        body.add(new Point(9, 10));
        body.add(new Point(8, 10));
        this.direction = Direction.RIGHT;

    }

    public int getLength() {
        return body.size();
    }

    // 修复BUG2：吃到食物，添加尾部坐标（正确写法）
    public void eatFood() {
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail.x, tail.y));
        score += 10;
    }

    public void move() {
        if (!isAlive) return;

        Point head = body.get(0);
        Point newHead = new Point();

        switch (direction) {
            case UP:
                newHead = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                newHead = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                newHead = new Point(head.x - 1, head.y);
                break;
            case RIGHT:
                newHead = new Point(head.x + 1, head.y);
                break;
            default:
                newHead = head;
        }

        body.add(0, newHead);
        body.remove(body.size() - 1);

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public List<Point> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getSpeed() {
        return speed;
    }

    public int getScore() {
        return score;
    }


    public Point getHead() {
        return body.get(0);
    }
}
