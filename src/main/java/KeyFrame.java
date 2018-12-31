import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

/**
 * 这是窗口类，用来设置窗口
 *
 * @author Stanley
 */

public class KeyFrame extends JFrame implements Runnable {
    /**
     *
     */
//    private ExecutorService pool;
//    public KeyFrame(ExecutorService pool){
//        this.pool = pool;
//    }
    private static final long serialVersionUID = 1L;
    // 初始提示
    private JLabel startLabel = new JLabel("鼠标初始位置");
    // 初始位置
    private JTextArea startArea = new JTextArea();
    // 第二次提示
    private JLabel secLabel = new JLabel("鼠标第二次点击");
    // 第二次确认位置
    private JTextArea secArea = new JTextArea();
    // 第三次提示
    private JLabel endLabel = new JLabel("鼠标最终位置");
    // 第三次确认位置
    private JTextArea endArea = new JTextArea();
    // 输入时间范围
    private JLabel timeLabel = new JLabel("请输入每个周期时间范围(s)");
    private JLabel space = new JLabel("");
    private JLabel minTimeLabel = new JLabel("请输入最小时间(s)");
    private JLabel maxTimeLabel = new JLabel("请输入最大时间(s)");
    private JLabel timeIntervalLabel = new JLabel("切换yys时间间隔(s)");
    private JLabel timeWaitLabel = new JLabel("结算页面间隔(s)");
    private JLabel timeStartLabel = new JLabel("结算-点击开始间隔(s)");
    private JLabel timeEndLabel = new JLabel("开始-结算间隔(s)");
    private JLabel timesLabel = new JLabel("请输入周期次数");
    private JLabel lessTimes = new JLabel("剩余次数");
    // 最小时间
    private JTextField minTimeTextField = new JTextField();
    // 最大时间
    private JTextField maxTimeTextField = new JTextField();
    // 时间间隔
    private JTextField timeIntervalTextField = new JTextField();
    // 结算
    private JTextField timeWaitField = new JTextField();
    private JTextField timeStartField = new JTextField();
    private JTextField timeEndField = new JTextField();
    // 周期次数
    private JTextField timesTextField = new JTextField();
    // 剩余次数
    //private JTextArea lessTimeArea = new JTextArea();
    private JLabel lessTimeArea = new JLabel();
    // 目前状态
    public static JLabel state = new JLabel("当前状态");
    // 鼠标当前坐标
    double pointY = 0;
    double pointX = 0;
    // 鼠标起始坐标
    double startX = 0;
    double startY = 0;
    // 鼠标最终坐标
    double secX = 0;
    double secY = 0;
    // 鼠标最终坐标
    double endX = 0;
    double endY = 0;
    // 线程运行标记
    boolean autoFlag = false;
    // 设置时间范围
    double minTime = 1000;
    double maxTime = 2000;
    double timeInterval = 300;
    double timeWait = 2000;
    double timeStart = 5000;
    double timeEnd = 35000;
    int n = 0;
    int i = 0;


    public void showPanel() {
        // 设置窗口大小
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 布置窗口内容
        JPanel panel = new JPanel();
        // 设置边框
        startArea.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        secArea.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        endArea.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        lessTimeArea.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        // 布置格式
        panel.setLayout(new GridLayout(16, 2));
        panel.add(startLabel);
        panel.add(startArea);
        // panel.add(startButton);
        panel.add(secLabel);
        panel.add(secArea);
        panel.add(endLabel);
        panel.add(endArea);
        panel.add(timeLabel);
        panel.add(space);
        panel.add(minTimeLabel);
        panel.add(minTimeTextField);
        panel.add(maxTimeLabel);
        panel.add(maxTimeTextField);
        panel.add(timeIntervalLabel);
        panel.add(timeIntervalTextField);
        panel.add(timeWaitLabel);
        panel.add(timeWaitField);
        panel.add(timeStartLabel);
        panel.add(timeStartField);
        panel.add(timeEndLabel);
        panel.add(timeEndField);
        panel.add(timesLabel);
        panel.add(timesTextField);
        panel.add(lessTimes);
        panel.add(lessTimeArea);
        // 布置操作提示
        JLabel metionLabel1 = new JLabel("F1确认鼠标初始位置");
        JLabel metionLabel2 = new JLabel("F2确认鼠标第二位置");
        JLabel metionLabel3 = new JLabel("F3确认鼠标最终位置");
        JLabel metionLabel4 = new JLabel("");
        JLabel metionLabel5 = new JLabel("F4开始结束 ");
        JLabel metionLabel6 = new JLabel("F5退出 ");
        JLabel metionLabel7 = new JLabel("F6最小化");
        JLabel metionLabel8 = new JLabel("F7恢复窗口");
        panel.add(metionLabel1);
        panel.add(metionLabel2);
        panel.add(metionLabel3);
        panel.add(metionLabel4);
        panel.add(metionLabel5);
        panel.add(metionLabel6);
        panel.add(metionLabel7);
        panel.add(metionLabel8);
        //默认数据
        minTimeTextField.setText("0.5");
        maxTimeTextField.setText("1");
        timeIntervalTextField.setText("0.3");
        timeWaitField.setText("2");
        timeStartField.setText("3");
        timeEndField.setText("31.8");
        timesTextField.setText("100");
        // 显示窗口
        JPanel statePanel = new JPanel();
        statePanel.setLayout(new FlowLayout());
        statePanel.add(state);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(statePanel, BorderLayout.SOUTH);

        // 0表示F1，1表示F2，2表示F3，3表示F4,4表示F5,5表示F6,6表示F7
        JIntellitype.getInstance().registerHotKey(0, 0, 112);
        JIntellitype.getInstance().registerHotKey(1, 0, 113);
        JIntellitype.getInstance().registerHotKey(2, 0, 114);
        JIntellitype.getInstance().registerHotKey(3, 0, 115);
        JIntellitype.getInstance().registerHotKey(4, 0, 116);
        JIntellitype.getInstance().registerHotKey(5, 0, 117);
        JIntellitype.getInstance().registerHotKey(6, 0, 118);
        // 增加键盘监听
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            @Override
            public void onHotKey(int arg0) {
                // TODO Auto-generated method stub
                Point mousePoint = MouseInfo.getPointerInfo().getLocation();
                pointX = mousePoint.getX();
                pointY = mousePoint.getY();
                // 确认起始坐标
                if (arg0 == 0) {
                    startArea.setText("x:" + pointX + "  y:" + pointY);
                    startX = pointX;
                    startY = pointY;
                    // 确认最终始坐标
                } else if (arg0 == 1) {
                    secArea.setText("x:" + pointX + "  y:" + pointY);
                    secX = pointX;
                    secY = pointY;

                } else if (arg0 == 2) {
                    endArea.setText("x:" + pointX + "  y:" + pointY);
                    endX = pointX;
                    endY = pointY;
                } else if (arg0 == 3 && autoFlag == false) {
                    //第二第三坐标没指定
                    if (secX == 0 && secY == 0) {
                        secX = startX;
                        secY = startY;
                    }
                    if (endX == 0 && endY == 0) {
                        endX = startX;
                        endY = startY;
                    }
                    //获取时间和次数，转换毫秒
                    try {
                        maxTime = Double.valueOf(maxTimeTextField.getText()) * 1000;
                        minTime = Double.valueOf(minTimeTextField.getText()) * 1000;
                        timeInterval = Double.valueOf(timeIntervalTextField.getText()) * 1000;
                        timeWait = Double.valueOf(timeWaitField.getText()) * 1000;
                        timeStart = Double.valueOf(timeStartField.getText()) * 1000;
                        timeEnd = Double.valueOf(timeEndField.getText()) * 1000;
                        n = Integer.valueOf(timesTextField.getText());
                    } catch (Exception e) {
                        minTime = 1000;
                        maxTime = 2000;
                        timeInterval = 300;
                        timeWait = 2000;
                        timeStart = 5000;
                        timeEnd = 35000;
                        n = 10;
                    }
                    if ((startX == 0 && startY == 0)) {
                        autoFlag = false;
                    } else {
                        autoFlag = true;
                        state.setText("开始执行");
                    }
                    // 最小化
                    // setExtendedState(JFrame.ICONIFIED);
                    // autoFlag 为true,表示程序运行，按F4 暂停程序
                } else if (arg0 == 3 && autoFlag == true) {
                    i = 0;
                    autoFlag = false;
                    state.setText("暂停执行");
                    // 窗口正常
                    // setExtendedState(JFrame.NORMAL);
                    // F5退出程序
                } else if (arg0 == 4) {
                    System.exit(0);
                    //最小化
                } else if (arg0 == 5) {
                    setExtendedState(JFrame.ICONIFIED);
                    //恢复窗口
                } else if (arg0 == 6) {
                    setExtendedState(JFrame.NORMAL);
                }
            }
        });
        setTitle("yys按键精灵");
        this.setVisible(true);
        // 窗口一直在上面
        this.setAlwaysOnTop(true);

        //双线程执行
//        while (true) {
//            Thread t1 = new Thread(this);
//            Thread t2 = new Thread(this);
//            t1.setPriority(Thread.MAX_PRIORITY);
//            t1.setPriority(Thread.MAX_PRIORITY);
//            try {
//                t1.start();
//                t2.start();
//                t1.join();
//                t2.join();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            while (autoFlag && i <= n) {

                lessTimeArea.setText(n - i + "次");
                i++;
                double time = timeCal(minTime, maxTime);
                //三个随机坐标
                RobotOperation.mouseOp(startX * (Math.random() * 0.05 + 1), startY * (Math.random() * 0.05 + 1), secX * (Math.random() * 0.05 + 1), secY * (Math.random() * 0.05 + 1), endX * (Math.random() * 0.001 + 1), endY * (Math.random() * 0.001 + 1), timeInterval, timeWait, timeStart, timeEnd);
                try {
                    Thread.sleep((long) time);
//                    count.countDown();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally {
                    break;
                }
            }
            if (autoFlag) {
                break;
            }
        }
//        }
    }

    // 计算循环间隔时间
    public double timeCal(double minTime, double maxTime) {
        double timeDev = maxTime - minTime;
        double time = (timeDev * (Math.random())) + minTime;
        return time;
    }
}
