package org.naozi.OnmyojiX.task.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author lenovo
 * @date 2018/1/25
 */
public class Yuhun4SingleWithOpencv extends BasicTask {

    private static final Logger logger = LoggerFactory.getLogger(Yuhun4SingleWithOpencv.class);

    public void start(){
        try {
            Mat material = Imgcodecs.imread("C:\\Users\\lenovo\\Desktop\\template.png");
            Mat screen = getCurrentScreenShot();

            int result_cols = screen.cols() - material.cols() + 1;
            int result_rows = screen.rows() - material.rows() + 1;

            int match_method = Imgproc.TM_SQDIFF;

            Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
            Imgproc.matchTemplate(screen,material,result,match_method);

            Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

            Point matchLoc;
            if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
                matchLoc = mmr.minLoc;
            }
            else {
                matchLoc = mmr.maxLoc;
            }


            Robot robot = new Robot();
            robot.mouseMove((int)matchLoc.x + material.width() / 2,(int)matchLoc.y + material.height() / 2);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseMove((int)matchLoc.x + material.width() / 2 + 500, (int)matchLoc.y + material.height() / 2);
//            robot.mouseRelease(InputEvent.BUTTON1_MASK);

//            BufferedImage resultImage = castMatToImg(result,".png");
//            ImageIO.write(resultImage, "png", new File("C:\\Users\\lenovo\\Desktop\\" + System.currentTimeMillis() + ".png"));   //将其保存在C:/imageSort/targetPIC/下
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
