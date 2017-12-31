package org.naozi.OnmyojiX.loader;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Naozi on 2017/8/28.
 */
public class PropertiesLoader {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    private static Properties PROPTIES = new Properties();
    private static String DIR_PATH;

    static {
        // get path
        String jarPath = System.getProperty("java.class.path");
        String[] pathSplit = jarPath.split("\\\\");
//        DIR_PATH = jarPath.substring(0,jarPath.indexOf(pathSplit[pathSplit.length - 1]));
        DIR_PATH = "OnmyojiX\\"; // use for debug in IDE
    }

    public static String getDirPath(){
        return DIR_PATH;
    }

    /**
     * load properties
     */
    public static void loadProperties() {
        InputStream in = null;
        try {
            // load log path
            String logPath = DIR_PATH + "log\\OnmyojiX.log";
            System.setProperty("LOG_PATH",logPath);
            // load image and log properties
            PropertyConfigurator.configure(DIR_PATH + "properties\\log4j.properties");
            in = new FileInputStream(DIR_PATH + "properties\\config.properties");
            PROPTIES.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * get property
     * @param key
     * @return
     */
    public static String getProperty(String key){
        String value = PROPTIES.getProperty(key);
        logger.info("load property,{},{}",key,value);
        return value;
    }

    /**
     * get image path
     * @param folderName
     * @param imageKey
     * @return
     */
    public static String getImagePath(String folderName,String imageKey){
        String imageProp = PROPTIES.getProperty(imageKey).trim();
        String imagePath = DIR_PATH + "image\\" + folderName + "\\" + imageProp;
        logger.info("load image path,{},{}",imageKey,imagePath);
        return imagePath;
    }

}
