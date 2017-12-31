package org.naozi.OnmyojiX.task;

import org.naozi.OnmyojiX.config.OnmyojiConfig;
import org.naozi.OnmyojiX.loader.PropertiesLoader;
import org.sikuli.script.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by Naozi on 2017/8/24.
 */
public abstract class BasicTask {

    private static final Logger logger = LoggerFactory.getLogger(BasicTask.class);

    private static final long STEP_INTERVAL = 100L;
    private static final double STEP_TIME = 0.1;
    private static final long ACTION_TIME = Long.parseLong(PropertiesLoader.getProperty("onmyoji.action.time"));
    protected static final long SLEEP_TIME = Long.parseLong(PropertiesLoader.getProperty("onmyoji.sleep.time"));
    protected static final boolean TEAM_LOCK = Boolean.parseBoolean(PropertiesLoader.getProperty("onmyoji.lock"));

    private Screen screen = new Screen();
    private boolean taskContinue = true;

    /**
     * make thread sleep to wait sikuliX finish current step
     */
    private void takeABreak(){
        try {
            long timeFloat = 0;
            Thread.sleep(STEP_INTERVAL + timeFloat);
        } catch (Exception e){
            logger.error("thread sleep failed,{}",e);
        }
    }

    /**
     * judge whether an action is overtime
     * @param startDate
     * @return
     */
    private boolean actionTimeOut(Date startDate){
        long startTime = startDate.getTime();
        long currentTime = new Date().getTime();
        return currentTime - startTime > ACTION_TIME;
    }

    /**
     * click target
     * @param target
     * @return
     */
    private boolean clickTarget(String target){
        try {
            screen.click(target);
            return true;
        } catch (FindFailed findFailed) {
            logger.info("target cannot found,{}",target);
            return false;
        }
    }

    /**
     * find target
     * @param target
     * @return
     */
    private boolean targetExists(String target){
        Match appear = screen.exists(target, STEP_TIME);
        return appear != null;
    }

    /**
     * focus on an app
     * @param appName
     * @return
     */
    protected App getAndFocusOnApp(String appName){
        try {
            App app = new App(appName);
            app.focus();
            return app;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get target region
     * @param target
     * @return
     */
    protected Region getTargetRegion(String target){
        Date startTime = new Date();
        while(true){
            Match appear = screen.exists(target, STEP_TIME);
            if(appear == null){
                // judge overtime
                if(actionTimeOut(startTime)){
                    return null;
                }
                else{
                    continue;
                }
            }
            else{
                Location location  = appear.getTarget();
                return new Region(location.getX(),location.getY(),0,0);
            }
        }
    }

    /**
     * click region to get target appear
     * @param region
     * @param target
     * @param repeatClick
     * @return
     */
    protected boolean clickRegionToGetAppear(Region region,String target,boolean repeatClick){
        try {
            Date startTime = new Date();
            if(region == null){
                logger.info("invalid region,{}",region);
                return false;
            }
            region.click();
            while(true) {
                if (!targetExists(target)) {
                    logger.info("wait for target appear,{}",target);
                    if(repeatClick){
                        region.click();
                    }
                }
                else {
                    logger.info("target appeared,{}",target);
                    return true;
                }
                // judge overtime
                if(actionTimeOut(startTime)){
                    return false;
                }
                takeABreak();
            }
        } catch (Exception e) {
            logger.error("click region failed,{}",e);
            return false;
        }
    }

    /**
     * click to get target appear
     * @param trigger
     * @param target
     * @param repeatClick
     * @return
     */
    protected boolean clickToGetAppear(String trigger,String target,boolean repeatClick){
        try {
            Date startTime = new Date();
            if(targetExists(trigger)){
                logger.info("find trigger,{},{}",Thread.currentThread().getId(),trigger);
                clickTarget(trigger);
                while(true) {
                    if (!targetExists(target)) {
                        logger.info("wait for target appear,{}",target);
                        if(repeatClick){
                            clickTarget(trigger);
                        }
                    }
                    else {
                        logger.info("target appeared,{}",target);
                        return true;
                    }
                    // judge overtime
                    if(actionTimeOut(startTime)){
                        return false;
                    }
                    takeABreak();
                }
            }
            else{
                logger.info("cannot find trigger,{}",trigger);
                return false;
            }
        } catch (Exception e) {
            logger.error("click appear failed,{}",e);
            return false;
        }
    }

    /**
     * click to make target disappear
     * @param target
     * @param repeatClick
     */
    protected boolean clickToMakeDisappear(String target,boolean repeatClick){
        try {
            Date startTime = new Date();
            clickTarget(target);
            while(true) {
                if (targetExists(target)) {
                    logger.info("wait for target disappear,{}",target);
                    if(repeatClick){
                        clickTarget(target);
                    }
                }
                else {
                    logger.info("target disappeared,{}",target);
                    return true;
                }
                // judge overtime
                if(actionTimeOut(startTime)){
                    return false;
                }
                takeABreak();
            }
        } catch (Exception e) {
            logger.error("click disappear failed,{}",e);
            return false;
        }
    }

    /**
     * wait for target appear
     * @param target
     * @param setTimeout
     * @return
     */
    protected boolean waitForAppear(String target,boolean setTimeout){
        try {
            Date startTime = new Date();
            while(true) {
                if (!targetExists(target)) {
                    logger.info("wait for target appear,{}",target);
                }
                else {
                    logger.info("target appeared,{}",target);
                    return true;
                }
                // judge overtime
                if(setTimeout && actionTimeOut(startTime)){
                    return false;
                }
                takeABreak();
            }
        } catch (Exception e) {
            logger.error("wait for appear failed,{}",e);
            return false;
        }
    }

    /**
     * wait for one of targets appear and get its region
     * @param targets
     * @param setTimeout
     * @return
     */
    protected Region waitForAppearAndGetRegion(String[] targets,boolean setTimeout){
        try {
            Date startTime = new Date();
            while(true) {
                for(String target : targets){
                    if (!targetExists(target)) {
                        logger.info("wait for target appear,{}",target);
                    }
                    else {
                        logger.info("target appeared,{}",target);
                        return getTargetRegion(target);
                    }
                }
                // judge overtime
                if(setTimeout && actionTimeOut(startTime)){
                    return null;
                }
                takeABreak();
            }
        } catch (Exception e) {
            logger.error("wait for appear failed,{}",e);
            return null;
        }
    }

    /**
     * wait for target disappear
     * @param target
     * @param setTimeout
     * @return
     */
    protected boolean waitForDisappear(String target,boolean setTimeout){
        try {
            Date startTime = new Date();
            while(true) {
                if (targetExists(target)) {
                    logger.info("wait for target disappear,{}",target);
                }
                else {
                    logger.info("target disappeared,{}",target);
                    return true;
                }
                // judge overtime
                if(setTimeout && actionTimeOut(startTime)){
                    return false;
                }
                takeABreak();
            }
        } catch (Exception e) {
            logger.error("wait for disappear failed,{}",e);
            return false;
        }
    }

    /**
     * start a new thread to refuse task invite
     */
    protected void autoRefuseTaskInvite(){
        Boolean autoRefuse = Boolean.parseBoolean(PropertiesLoader.getProperty("onmyoji.auto.refuse"));
        if(autoRefuse){
            taskContinue = true;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(taskContinue){
                        // find task invite from friend
                        if(targetExists(OnmyojiConfig.ONMYOJI_TASK_REFUSE)){
                            // refuse
                            clickToMakeDisappear(OnmyojiConfig.ONMYOJI_TASK_REFUSE,true);
                        }
                        try {
                            Thread.sleep(300L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }
    }

    /**
     * stop the thread to refuse task
     */
    protected void stopAutoRefuse(){
        taskContinue = false;
    }
}
