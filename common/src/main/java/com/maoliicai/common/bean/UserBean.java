package com.maoliicai.common.bean;

import java.io.Serializable;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/8
 * @description
 */
public class UserBean implements Serializable{

    /**
     * id : 29625
     * isBindBank : 1
     * username : 15007257895
     * payPassword : 1
     */

    private int id;
    private String isBindBank;//是否绑定银行卡 1-绑定 0-未绑定
    private String username;//手机号
    private String payPassword;//是否设置交易密码 1-设置 0-未设置

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsBindBank() {
        return isBindBank;
    }

    public void setIsBindBank(String isBindBank) {
        this.isBindBank = isBindBank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
}
