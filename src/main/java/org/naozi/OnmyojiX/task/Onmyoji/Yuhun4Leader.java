package org.naozi.OnmyojiX.task.Onmyoji;

import org.naozi.OnmyojiX.config.OnmyojiConfig;
import org.naozi.OnmyojiX.loader.PropertiesLoader;
import org.naozi.OnmyojiX.task.BasicTask;
import org.sikuli.script.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Naozi on 2017/8/25.
 */
public class Yuhun4Leader extends BasicTask {

    private static final Logger logger = LoggerFactory.getLogger(Yuhun4Leader.class);

    public void start() {
        try {
            // start a thread to refuse task from friend
            autoRefuseTaskInvite();
            boolean success = false;
            int round = 1;
            // get cycle time
            Integer cycleTime = Integer.valueOf(PropertiesLoader.getProperty("onmyoji.cycle.time"));
            // get member number
            Integer memberNum = Integer.valueOf(PropertiesLoader.getProperty("onmyoji.member.number"));

            // task start
            while (true){
                // wait for battle result
                String[] winOrLose = new String[]{OnmyojiConfig.ONMYOJI_LOOT};
                Region resultRegion = waitForAppearAndGetRegion(winOrLose,false);
                if(resultRegion != null){
                    // get to team step
                    if(clickRegionToGetAppear(resultRegion,OnmyojiConfig.ONMYOJI_LEADER_LEAVE,true)){
                        // task finished
                        if(round >= cycleTime){
                            break;
                        }
                        // 2 players
                        if(memberNum == 2){
                            // wait for member
                            if(waitForAppear(OnmyojiConfig.ONMYOJI_LEADER_START,true)){
                                // start again
                                clickToMakeDisappear(OnmyojiConfig.ONMYOJI_LEADER_START,true);
                                success = true;
                            }
                        }
                        //3 players
                        else if(memberNum == 3){
                            // wait for member
                            if(waitForDisappear(OnmyojiConfig.ONMYOJI_LEADER_INVITE,true)){
                                // start again
                                clickToMakeDisappear(OnmyojiConfig.ONMYOJI_LEADER_START,true);
                                success = true;
                            }
                        }
                    }
                }
                // result
                if(success){
                    logger.info("leader round success,{}",round);
                    logger.info("get ready for start, sleep " + SLEEP_TIME + " ms...");
                    Thread.sleep(SLEEP_TIME);
                }
                else{
                    logger.info("round failed,{}",round);
                    break;
                }
                round ++;
            }
            logger.info("Task finished...shut down...");
            stopAutoRefuse();
            clickToMakeDisappear(OnmyojiConfig.ONMYOJI_EXIT,true);
        } catch (Exception e) {
            logger.error("task error,{}",e);
        }
    }
}
