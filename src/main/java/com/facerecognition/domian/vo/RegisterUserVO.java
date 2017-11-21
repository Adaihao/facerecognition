package com.facerecognition.domian.vo;

/**
 * @author luoji
 * @params
 * @since 2017/11/11 0011
 */
public class RegisterUserVO {
    private String userName;
    private String faceImgPath;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFaceImgPath() {
        return faceImgPath;
    }

    public void setFaceImgPath(String faceImgPath) {
        this.faceImgPath = faceImgPath;
    }
}
