package org.naozi.OnmyojiX.task.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * @author lenovo
 * @date 2018/1/12
 */
public class Demo extends BasicTask {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径
        // -Djava.library.path=$PROJECT_DIR$\opencv\x64
    }

    public static void main(String[] args) {
        // 注意图片路径中不能出现中文
//        System.out.println(System.currentTimeMillis());
//        Mat mat = Imgcodecs.imread("C:\\Users\\lenovo\\Desktop\\riri.jpg");
//        System.out.println(System.currentTimeMillis());
//        System.out.println(mat);

        Yuhun4SingleWithOpencv task = new Yuhun4SingleWithOpencv();
        task.start();

//        try {
//            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//            Robot robot = new Robot();
//            robot.mouseMove(screenSize.width - 300, 5);
//            robot.delay(1000);
//            robot.mousePress(InputEvent.BUTTON1_MASK);
//            robot.delay(300);
//            robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        }
//        catch (AWTException e) {
//            e.printStackTrace();
//        }
    }
}
