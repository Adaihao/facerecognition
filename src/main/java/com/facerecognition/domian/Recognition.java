package com.facerecognition.domian;

import java.io.Serializable;

/**
 * @author luoji
 * @params
 * @since 2017/11/4 0004
 */
public class Recognition implements Serializable {

    private String filePathA;
    private String filePathB;

    public String getFilePathA() {
        return filePathA;
    }

    public void setFilePathA(String filePathA) {
        this.filePathA = filePathA;
    }

    public String getFilePathB() {
        return filePathB;
    }

    public void setFilePathB(String filePathB) {
        this.filePathB = filePathB;
    }
}
