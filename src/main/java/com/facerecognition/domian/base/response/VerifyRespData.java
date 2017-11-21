package com.facerecognition.domian.base.response;

/**
 * @author yintongjiang
 * @params
 * @since 2016/10/27
 */
public class VerifyRespData {
    private String userId;
    private String userName;
    private Long createTime;
    private Long expireTime;

    public VerifyRespData() {

    }

    public VerifyRespData(String userId, String userName, Long expireTime, Long createTime) {
        this.userId = userId;
        this.userName = userName;
        this.expireTime = expireTime;
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
