import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 鼠标自动控制类
 *
 * @author Stanley
 *
 */

public class RobotOperation {

    public static void mouseOp(double startX, double startY, double secX,double secY, double endX, double endY,double timeInterval,double timeWait,double timeStart,double timeEnd) {
        Random rand = new Random();
        try {
            Robot rbt = new Robot();
            rbt.delay((int) timeInterval + (rand.nextInt(100)));
            KeyFrame.state.setText("点击yys1经验结算");
            rbt.mouseMove((int) startX, (int) startY);// 移动到初始位置
            rbt.delay(200);// 休息0.1秒按鼠标
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);// 按下鼠标
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);// 放开鼠标
            rbt.delay(200);
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);// 按下鼠标
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);// 放开鼠标
            rbt.delay((int) timeInterval);// 间隔时间：默认300ms

            KeyFrame.state.setText("点击yys2经验结算");

            rbt.mouseMove((int) secX, (int) secY);// 移动到第二位置
            rbt.delay(200);// 休息0.1秒按鼠标
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);// 按下鼠标
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);// 放开鼠标
            rbt.delay(200);
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);// 按下鼠标
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);// 放开鼠标
            rbt.delay((int)timeWait + (rand.nextInt(500)));//todo

            KeyFrame.state.setText("点击yys1御魂结算");
            rbt.mouseMove((int) startX, (int) startY);// 移动到初始位置
            rbt.delay(200);// 休息0.1秒按鼠标
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);// 按下鼠标
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);// 放开鼠标
            rbt.delay(300);
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);// 按下鼠标
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);// 放开鼠标
            rbt.delay((int) timeInterval);// 休息间隔时间

            KeyFrame.state.setText("点击yys2御魂结算");
            rbt.mouseMove((int) secX, (int) secY);
            rbt.delay(200);
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
            rbt.delay(300);
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);// 按下鼠标
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);// 放开鼠标
            int time1 = (int)timeStart + (rand.nextInt(500));
            KeyFrame.state.setText("等待点击开始 " + time1 +"ms");
            rbt.delay(time1);//等待开始


            rbt.mouseMove((int) endX, (int) endY);//点击开始
            rbt.delay(200);
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
            rbt.delay(300);
            rbt.mousePress(KeyEvent.BUTTON1_DOWN_MASK);// 按下鼠标
            rbt.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);// 放开鼠标
            int time2 = (int)timeEnd + (rand.nextInt(500));
            KeyFrame.state.setText("等待点击结算 "+ time2 +"ms");
            rbt.delay(time2);//等待结算

        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
