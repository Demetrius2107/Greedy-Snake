package cn.shanyue.snake.windows.swing.learn;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {


    public void createJFrame(String title) {

        JFrame frame = new JFrame(title);
        Container container = frame.getContentPane();
        container.setBackground(Color.BLUE);


        JLabel jLabel = new JLabel("This is a JFrame Window");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jLabel);

        frame.setVisible(true);
        frame.setBounds(800, 600, 800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton jButton = new JButton("Click me");
        jButton.setBounds(10,10,150,20);


        // 添加鼠标单击事件
        jButton.addActionListener(e->{
            new MyDiaLog(MyFrame.this).setVisible(true);
        });

        container.add(jButton);
        container.setBackground(Color.WHITE);
        setSize(400,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);


    }

    public MyFrame(){
        Container container = getContentPane();
        container.setLayout(null);

        JLabel label = new JLabel("这是一个JFrame窗体");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(label);

        JButton button = new JButton("点我弹出对话框");
        button.setBounds(10,10,150,20);

        button.addActionListener(e -> {
            new MyDiaLog(MyFrame.this).setVisible(true);
        });

        container.add(button);
        container.setBackground(Color.WHITE);
        setSize(400,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

    }


    public static void main(String[] args) {
        //new MyFrame().createJFrame("This is a JFrame Window");
        new MyFrame();
    }

}
