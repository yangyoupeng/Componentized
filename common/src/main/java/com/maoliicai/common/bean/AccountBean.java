package com.maoliicai.common.bean;

import java.io.Serializable;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/8
 * @description
 */
public class AccountBean implements Serializable{

    /**
     * bankAccount : 4682******8888
     * bankAccountName : 董迎真
     * bankCode : CMB
     * bankName : 招商银行
     * bindMobile : C501F94F5B463B3EAD846D02FDA1220DE4C19E2DD85A44A1139DDB2E7AAF618A1D3FC3ED56A72DCD
     * cardLast : 8888
     * cardTop : 4682
     * id : 4028808a62e138530162e142c4ef0006
     * idcard : 411*************86
     */

    private String bankAccount;//银行卡号
    private String bankAccountName;//此银行卡号的用户名字
    private String bankCode;//银行简称
    private String bankName;//银行名称
    private String bindMobile;//绑卡手机号
    private String cardLast;//银行卡后四位
    private String cardTop;//银行卡前四位
    private String id;//用于充值时需要的accountId
    private String idcard;//身份证号

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(String bindMobile) {
        this.bindMobile = bindMobile;
    }

    public String getCardLast() {
        return cardLast;
    }

    public void setCardLast(String cardLast) {
        this.cardLast = cardLast;
    }

    public String getCardTop() {
        return cardTop;
    }

    public void setCardTop(String cardTop) {
        this.cardTop = cardTop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
