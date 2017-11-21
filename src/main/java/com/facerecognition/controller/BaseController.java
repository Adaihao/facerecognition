package com.facerecognition.controller;


import com.facerecognition.domian.base.response.RespData;


/**
 * BaseController
 *
 * @author gaoyanlei
 * @since 2016/10/26
 */
public class BaseController {

    public Object ok(Object data) {
        return RespData.ok(data);
    }

    public Object error(int code) {
        return RespData.error(code);
    }

    public Object ok() {
        return RespData.ok(null);
    }

}