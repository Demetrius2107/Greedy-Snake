package cn.shanyue;


import cn.shanyue.snake.panel.GamePanel;

import javax.swing.*;

public class GameMain {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(620, 450);
        frame.setLocationRelativeTo(null); // 窗口居中
        frame.setResizable(false);

        // 把游戏面板放进窗口
        frame.add(new GamePanel());

        // 显示窗口
        frame.setVisible(true);

    }
}