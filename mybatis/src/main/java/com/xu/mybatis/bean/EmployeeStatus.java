package com.xu.mybatis.bean;

/**
 * Created by xj on 2018/10/15.
 */
/*
*
* 希望数据库保存的是100,200的状态码，而不是0,1下标，或者枚举的名
* */
public enum EmployeeStatus {
    LOGIN(100,"用户登录"),LOGOUT(200,"用户登出"),REMOVE(300,"用户不存在");
    private Integer code;
    private String msg;
    private EmployeeStatus(Integer code,String msg){
        this.code=code;
        this.msg=msg;
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

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //按照状态码返回枚举对象
    public static EmployeeStatus getEmpStatusBycode(Integer code){
        switch (code){
            case 100:
                return LOGIN;
            case 200:
                return LOGOUT;
            case 300:
                return REMOVE;
            default:
                return LOGOUT;
        }
    }
}
