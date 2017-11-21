package com.facerecognition.domian;

/**
 * @author adaiaho
 * @params
 * @since 2017/11/13 0013
 */
public class LoginUser {
    private User user;
    private boolean loginStatus;
    private float score;
    private String thisLoginDate;
    private String lastLoginDate;

    public String getThisLoginDate() {
        return thisLoginDate;
    }

    public void setThisLoginDate(String thisLoginDate) {
        this.thisLoginDate = thisLoginDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }


    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}
