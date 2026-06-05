package com.dorm.common;

public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public R(){}

    // getter setter
    public Integer getCode() {return code;}
    public void setCode(Integer code) {this.code = code;}
    public String getMsg() {return msg;}
    public void setMsg(String msg) {this.msg = msg;}
    public T getData() {return data;}
    public void setData(T data) {this.data = data;}

    // ==========成功返回==========
    // 带数据成功
    public static <T> R<T> ok(T data){
        R<T> r = new R<>();
        r.setCode(200);
        r.setMsg("success");
        r.setData(data);
        return r;
    }
    // 无数据成功
    public static <T> R<T> ok(){
        return ok(null);
    }

    // ==========失败返回：fail原有（自定义编码+信息）==========
    public static <T> R<T> fail(Integer code,String msg){
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    // ==========新增error系列（默认500异常码，业务最常用）==========
    // 1.默认500+默认提示
    public static <T> R<T> error(){
        return error(500,"操作失败");
    }
    // 2.默认500+自定义提示【解决R.error("用户名不存在")报错】
    public static <T> R<T> error(String msg){
        return error(500,msg);
    }
    // 3.自定义错误码+自定义提示
    public static <T> R<T> error(Integer code,String msg){
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}