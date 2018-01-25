package org.naozi.OnmyojiX;

import org.apache.commons.lang3.StringUtils;
import org.naozi.OnmyojiX.loader.PropertiesLoader;
import org.naozi.OnmyojiX.task.sikuliX.Yuhun4Leader;
import org.naozi.OnmyojiX.task.sikuliX.Yuhun4Member;
import org.naozi.OnmyojiX.task.sikuliX.Yuhun4Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Naozi on 2017/8/29.
 */
public class Starter {

    private static final Logger logger = LoggerFactory.getLogger(Starter.class);

    /**
     * application entry
     * @param args
     */
    public static void main(String[] args){
        try {
            // judge user authentication
            String dirPath = PropertiesLoader.getDirPath();
            if(StringUtils.isEmpty(dirPath)){
                System.out.println("please start task with system admin...shut down...");
            }
            else{
                // load properties
                PropertiesLoader.loadProperties();
                // get start
                startTask();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * get mode and start task
     */
    private static void startTask(){
        Integer mode = Integer.valueOf(PropertiesLoader.getProperty("onmyoji.mode"));
        if(mode == 0){
            logger.info("Task start! sikuliX yuhun for single...");
            Yuhun4Single yuhun4Single = new Yuhun4Single();
            yuhun4Single.start();
        }
        else if(mode == 1){
            logger.info("Task start! sikuliX yuhun for leader...");
            Yuhun4Leader yuhun4Leader = new Yuhun4Leader();
            yuhun4Leader.start();
        }
        else if(mode == 2){
            logger.info("Task start! sikuliX yuhun for member...");
            Yuhun4Member yuhun4Member = new Yuhun4Member();
            yuhun4Member.start();
        }
    }
}
