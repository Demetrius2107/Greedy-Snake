package cn.shanyue.snake.panel;

import cn.shanyue.snake.domain.Food;
import cn.shanyue.snake.domain.Snake;
import cn.shanyue.snake.enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private static final int CELL_SIZE = 20;

    /**
     * 游戏核心对象
     */
    private Snake snake;
    private Food food;

    // 定时器、自动移动+刷新画面
    private Timer timer;


    public GamePanel() {

        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        // 创建蛇和食物
        snake = new Snake("小绿蛇", Color.GREEN);
        food = new Food();

        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();
                Direction now = snake.getDirection();
                // 控制台打印，测试按键是否被识别
                System.out.println("按下按键：" + key + " 当前方向：" + now);

                if (key == KeyEvent.VK_UP && now != Direction.DOWN) {
                    snake.setDirection(Direction.UP);
                }
                if (key == KeyEvent.VK_DOWN && now != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                }
                if (key == KeyEvent.VK_LEFT && now != Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                }
                if (key == KeyEvent.VK_RIGHT && now != Direction.LEFT) {
                    snake.setDirection(Direction.RIGHT);
                }
            }
        });


        timer = new Timer(150, e -> gameLogic());
        timer.start();

        // 【核心】延迟获取焦点
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }


    /**
     * 游戏每帧执行的核心逻辑
     */
    private void gameLogic() {

        if (!snake.isAlive()) {
            return;
        }

        snake.move();
        Point head = snake.getHead();

        // 撞墙死亡
        if (head.x < 0 || head.x >= Food.MAX_GRID_X || head.y < 0 || head.y >= Food.MAX_GRID_Y) {
            snake.setAlive(false);
        }

        // 撞自己死亡
        java.util.List<Point> body = snake.getBody();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                snake.setAlive(false);
                break;
            }
        }


        // 吃到食物
        if (head.equals(food.getLocation())) {
            snake.eatFood();
            food.randomLocation(snake.getBody());
        }

        // 刷新页面
        repaint();
    }


    /**
     * 绘图 蛇 食物 分数 死亡提示
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(snake == null || food == null){
            return;
        }

        // 画蛇
        g.setColor(snake.getColor());
        for (Point p : snake.getBody()) {
            g.fillRect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
        }

        // 画食物
        g.setColor(Color.RED);
        Point f = food.getLocation();
        if (f != null) {
            g.fillRect(f.x * CELL_SIZE, f.y * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
        }
        // 画分数
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("分数：" + snake.getScore(), 20, 30);

        // 死亡提示
        if (!snake.isAlive()) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("游戏结束！", 200, 200);
        }
    }
}
