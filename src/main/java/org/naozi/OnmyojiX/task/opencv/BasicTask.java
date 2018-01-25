package org.naozi.OnmyojiX.task.opencv;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

/**
 * @author lenovo
 * @date 2018/1/25
 */
public class BasicTask {

    /**
     * get current screen shot
     */
    protected Mat getCurrentScreenShot(){
        try {
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0,
                    0,(int)dimension.getWidth(),(int)dimension.getHeight()));
            return castImgToMat(screenshot, BufferedImage.TYPE_3BYTE_BGR, CvType.CV_8UC3);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Mat类型转化成BufferedImage类型
     * @param mat Mat对象
     * @param fileExtension 文件扩展名
     * @return
     */
    public static BufferedImage castMatToImg(Mat mat, String fileExtension) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(fileExtension, mat, mob);
        byte[] byteArray = mob.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImage;
    }

    /**
     * 将BufferedImage类型转换成Mat类型
     * @param original 已有的实例
     * @param imgType bufferedImage的类型 如 BufferedImage.TYPE_3BYTE_BGR
     * @param matType 转换成mat的type 如 CvType.CV_8UC3
     * @return
     */
    public static Mat castImgToMat(BufferedImage original, int imgType, int matType) {

        if (original == null) {
            throw new IllegalArgumentException("original == null");
        }

        BufferedImage image = original;

        // Don't convert if it already has correct type
        if (original.getType() != imgType) {
            // Create a buffered image
            image = new BufferedImage(original.getWidth(), original.getHeight(), imgType);
            // Draw the image onto the new buffer
            Graphics2D g = image.createGraphics();
            try {
                g.setComposite(AlphaComposite.Src);
                g.drawImage(original, 0, 0, null);
            }
            finally {
                g.dispose();
            }
        }

        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = Mat.eye(image.getHeight(), image.getWidth(), matType);
        mat.put(0, 0, pixels);

        return mat;
    }

}
