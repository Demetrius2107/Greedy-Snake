package cn.shanyue.snake.windows.swing.learn;

import javax.swing.*;
import java.awt.*;

public class MyDiaLog extends JDialog {


    public MyDiaLog(MyFrame myFrame){
        super(myFrame,"JDiaLog Window",true);

        Container container = getContentPane();
        container.add(new Label("This is a chat Window"));
        setBounds(120,120,150,100);
    }
}
