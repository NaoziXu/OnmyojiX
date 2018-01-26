package org.naozi.OnmyojiX.task.opencv;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author lenovo
 * @date 2018/1/25
 */
public class Yuhun4SingleWithOpencv extends BaseTask {

    private static final Logger logger = LoggerFactory.getLogger(Yuhun4SingleWithOpencv.class);
    private static final double PEAK_THRESHOLD = 0.9;

    public void start(){
        try {
            Mat material = Imgcodecs.imread("C:\\Users\\lenovo\\Desktop\\template.png");
            Mat screen = getCurrentScreenShot();
            Long start = System.currentTimeMillis();
            ArrayList<Point> matchLocs = getMatchLocList(screen,material,Imgproc.TM_SQDIFF,PEAK_THRESHOLD);
            System.out.println(System.currentTimeMillis() - start);
//            Robot robot = new Robot();
//            robot.mouseMove((int)matchLoc.x + material.width() / 2,(int)matchLoc.y + material.height() / 2);
//            robot.mousePress(InputEvent.BUTTON1_MASK);
//            robot.mouseMove((int)matchLoc.x + material.width() / 2 + 500, (int)matchLoc.y + material.height() / 2);
//            robot.mouseRelease(InputEvent.BUTTON1_MASK);

//            BufferedImage resultImage = castMatToImg(result,".png");
//            ImageIO.write(resultImage, "png", new File("C:\\Users\\lenovo\\Desktop\\" + System.currentTimeMillis() + ".png"));   //将其保存在C:/imageSort/targetPIC/下
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
