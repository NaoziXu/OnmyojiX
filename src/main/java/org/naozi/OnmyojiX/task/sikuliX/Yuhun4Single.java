package org.naozi.OnmyojiX.task.sikuliX;

import org.naozi.OnmyojiX.config.OnmyojiConfig;
import org.naozi.OnmyojiX.loader.PropertiesLoader;
import org.sikuli.script.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Naozi on 2017/8/25.
 */
public class Yuhun4Single extends BasicTask {

    private static final Logger logger = LoggerFactory.getLogger(Yuhun4Single.class);

    public void start() {
        try {
            // start a thread to refuse task from friend
            autoRefuseTaskInvite();
            boolean success = false;
            int round = 1;
            // get cycle time
            Integer cycleTime = Integer.valueOf(PropertiesLoader.getProperty("onmyoji.cycle.time"));

            // task start
            while (true){
                boolean hasNoError = true;
                if(!TEAM_LOCK){
                    // wait for ready appear
                    if(waitForAppear(OnmyojiConfig.ONMYOJI_READY,true)) {
                        // click ready btn
                        boolean getReady = clickToMakeDisappear(OnmyojiConfig.ONMYOJI_READY, true);
                        if(!getReady){
                            hasNoError = false;
                        }
                    }
                }
                if(hasNoError){
                    logger.info("get ready for start, sleep " + SLEEP_TIME + " ms...");
                    Thread.sleep(SLEEP_TIME);
                    // wait for battle result
                    String[] result = new String[]{OnmyojiConfig.ONMYOJI_LOOT};
                    Region resultRegion = waitForAppearAndGetRegion(result,false);
                    if(resultRegion != null){
                        // finish current round and get to repeat
                        if(clickRegionToGetAppear(resultRegion,OnmyojiConfig.ONMYOJI_SINGLE_START,true)){
                            // task finished
                            if(round >= cycleTime){
                                break;
                            }
                            // start again
                            clickToMakeDisappear(OnmyojiConfig.ONMYOJI_SINGLE_START,true);
                            success = true;
                        }
                    }
                }
                // result
                if(success){
                    logger.info("round success,{}",round - 1);
                }
                else{
                    logger.info("round failed,{}",round - 1);
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
