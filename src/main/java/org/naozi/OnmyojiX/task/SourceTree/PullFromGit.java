package org.naozi.OnmyojiX.task.SourceTree;

import org.naozi.OnmyojiX.config.SourceTreeConfig;
import org.naozi.OnmyojiX.task.BasicTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * test demo
 * Created by Naozi on 2017/8/24.
 */
public class PullFromGit extends BasicTask {

    private static final Logger logger = LoggerFactory.getLogger(PullFromGit.class);

    public void start() {
        try {
            boolean success = false;
            // switch app
            getAndFocusOnApp(SourceTreeConfig.SOURCETREE_APP_NAME);
            // task start
            if(waitForAppear(SourceTreeConfig.SOURCETREE_PULL,true)){
                if(clickToGetAppear(SourceTreeConfig.SOURCETREE_PULL,SourceTreeConfig.SOURCETREE_CONFIRM,true)){
                    if(clickToMakeDisappear(SourceTreeConfig.SOURCETREE_CONFIRM,true)){
                        success = true;
                    }
                }
            }
            // task result
            if(success){
                logger.info("task success");
            }
            else{
                logger.info("task failed");
            }
        } catch (Exception e) {
            logger.error("task error,{}",e);
        }
    }
}
