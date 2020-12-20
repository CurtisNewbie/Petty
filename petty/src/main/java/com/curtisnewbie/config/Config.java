package com.curtisnewbie.config;

import java.io.Serializable;

/**
 * <p>
 * Config entity serialised/deserialized from JSON
 * </p>
 *
 * @author yongjie.zhuang
 */
public class Config implements Serializable {

    /**
     * Path to where the to-do list is saved on disk
     */
    private String savePath;

    /**
     * Record whether it's the first time the user logged in
     */
    private boolean isFirstTimeLogin;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public void setFirstTimeLogin(boolean firstTimeLogin) {
        isFirstTimeLogin = firstTimeLogin;
    }

    @Override
    public String toString() {
        return "Config{" + "savePath='" + savePath + '\'' + '}';
    }
}
