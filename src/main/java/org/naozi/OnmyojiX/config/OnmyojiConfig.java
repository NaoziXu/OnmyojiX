package org.naozi.OnmyojiX.config;

import org.naozi.OnmyojiX.loader.PropertiesLoader;

/**
 * Created by Naozi on 2017/8/24.
 */
public class OnmyojiConfig {
    // image folder name
    private static final String FOLDER_NAME = "onmyoji";
    // image material path
    public static final String ONMYOJI_APP_NAME = PropertiesLoader.getProperty("onmyoji.app.name");
    public static final String ONMYOJI_READY = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.ready");
    public static final String ONMYOJI_WIN = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.win");
    public static final String ONMYOJI_LOSE = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.lose");
    public static final String ONMYOJI_LOOT = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.loot");
    public static final String ONMYOJI_LOOT_SINGLE = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.loot.single");
    public static final String ONMYOJI_LOOT_TEAM = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.loot.team");
    public static final String ONMYOJI_LEADER_REPEAT = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.leader.repeat");
    public static final String ONMYOJI_LEADER_START = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.leader.start");
    public static final String ONMYOJI_LEADER_LEAVE = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.leader.leave");
    public static final String ONMYOJI_LEADER_INVITE = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.leader.invite");
    public static final String ONMYOJI_MENBER_START = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.member.start");
    public static final String ONMYOJI_SINGLE_START = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.single.start");
    public static final String ONMYOJI_EXIT = PropertiesLoader.getImagePath(FOLDER_NAME,"onmyoji.exit");
}
