package com.facerecognition.domian.base;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseDomain
 *
 * @author adaiaho
 * @since 2016/10/25
 */


public abstract class BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private String creator;

    private Date createDate;

    private int status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
