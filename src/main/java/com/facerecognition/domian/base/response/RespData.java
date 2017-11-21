package com.facerecognition.domian.base.response;


import java.io.Serializable;


/**
 * @author yintongjiang
 * @params
 * @since 2016/10/21
 */
public class RespData implements Serializable {

    private static final long serialVersionUID = -5385196012551768394L;

    // 与RespCode的code对应
    private Integer code;

    // 与RespCode的msg对应
    private String msg;

    private Object data;


    public RespData() {
        super();
    }


    public RespData(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public RespData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Object ok(Object data) {
        return new RespData(RespCode.RESP_SUCCESS.getCode(), RespCode.RESP_SUCCESS.getMsg(), data);
    }

    public static Object ok() {
        return new RespData(RespCode.RESP_SUCCESS.getCode(), RespCode.RESP_SUCCESS.getMsg());
    }

    public static Object error(int code) {
        return new RespData(code, RespCode.getMsgByCode(code));
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }


    // 2016/10/26 增加返回封装 Add by gaoyanlei

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
