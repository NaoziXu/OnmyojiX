package org.naozi.OnmyojiX.config;

import org.naozi.OnmyojiX.loader.PropertiesLoader;

/**
 * Created by Naozi on 2017/8/24.
 */
public class SourceTreeConfig {
    // image folder name
    private static final String FOLDER_NAME = "sourceTree";
    // image material path
    public static final String SOURCETREE_APP_NAME = PropertiesLoader.getProperty("sourceTree.app.name");
    public static final String SOURCETREE_PULL = PropertiesLoader.getImagePath(FOLDER_NAME,"sourceTree.pull");
    public static final String SOURCETREE_CONFIRM = PropertiesLoader.getImagePath(FOLDER_NAME,"sourceTree.confirm");
}
