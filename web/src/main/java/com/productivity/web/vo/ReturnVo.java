package com.productivity.web.vo;

public class ReturnVo {

    /**
     * 是否成功
     */
    private boolean success = true;
    /**
     * 提示信息
     */
    private String msg = "";

    private Object obj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
