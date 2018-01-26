package org.naozi.OnmyojiX.task.opencv;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author lenovo
 * @date 2018/1/25
 */
public abstract class BaseTask {

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
     * get matched min location points
     * @param pictureMat
     * @param templateMat
     * @param matchMethod
     * @param peakThreshold
     * @return
     */
    protected ArrayList<Point> getMatchLocList(Mat pictureMat, Mat templateMat, int matchMethod, double peakThreshold){
        int resultCols = pictureMat.cols() - templateMat.cols() + 1;
        int resultRows = pictureMat.rows() - templateMat.rows() + 1;

        Mat result = new Mat(resultRows, resultCols, CvType.CV_32FC1);
        Imgproc.matchTemplate(pictureMat,templateMat,result,matchMethod);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        if (matchMethod == Imgproc.TM_SQDIFF || matchMethod == Imgproc.TM_SQDIFF_NORMED) {
            Imgproc.threshold(result,result,0.1,1,Imgproc.THRESH_BINARY_INV);
        }
        else {
            Imgproc.threshold(result,result,0.9,1,Imgproc.THRESH_TOZERO);
        }

        ArrayList<Point> minLocResultList = new ArrayList<>();
        double maxVal = 1;
        while (maxVal > peakThreshold) {
            Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result,new Mat());
            maxVal = minMaxLocResult.maxVal;
            if(maxVal > peakThreshold){
                minLocResultList.add(new Point(minMaxLocResult.maxLoc.x, minMaxLocResult.maxLoc.y));
                Imgproc.rectangle(result,
                        new Point(minMaxLocResult.maxLoc.x - templateMat.width()/2,
                                minMaxLocResult.maxLoc.y - templateMat.height()/2),
                        new Point(minMaxLocResult.maxLoc.x + templateMat.width()/2,
                                minMaxLocResult.maxLoc.y + templateMat.height()/2), Scalar.all(0),-1);
            }
        }
        return minLocResultList;
    }

    /**
     * cast a Mat to a BufferedImage
     * @param mat
     * @param fileExtension
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
     * cast a BufferedImage to a Mat
     * @param original
     * @param imgType
     * @param matType
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
