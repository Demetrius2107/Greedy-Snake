package cn.shanyue.snake.domain;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Food {


    private Point location;

    private static final Random RANDOM = new Random();

    public static final int MAX_GRID_X = 30;

    public static final int MAX_GRID_Y = 20;

    // 修复1：构造方法必须调用随机，开局生成食物
    public Food(){
        randomLocation();
    }

    // 基础随机生成坐标
    public void randomLocation(){
        int x = RANDOM.nextInt(MAX_GRID_X);
        int y = RANDOM.nextInt(MAX_GRID_Y);
        location = new Point(x,y);
    }

    // 生成食物、避免蛇的身体
    // 修复2：加重试次数，防止死循环
    public void randomLocation(List<Point> snakeBody){
        int count = 0;
        do{
            randomLocation();
            count++;
            // 最多重试50次，强制退出，避免卡死
            if(count > 50){
                break;
            }
        }while(snakeBody.contains(location));
    }

    // 修复3：空值兜底，保证永远返回有效坐标
    public Point getLocation(){
        if(location == null){
            randomLocation();
        }
        return location;
    }

}
