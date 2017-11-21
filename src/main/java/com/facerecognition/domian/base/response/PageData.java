package com.facerecognition.domian.base.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author adaiaho
 * @params
 * @since 2016/10/26 0026
 */
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 804566633940490216L;

    private List<T> data = new ArrayList<T>();

    private Integer total = 0;


    public PageData() {
    }


    public PageData(List<T> data, int total) {
        this.data = data;
        this.total = total;
    }


    public Integer getTotal() {
        return total;
    }


    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
