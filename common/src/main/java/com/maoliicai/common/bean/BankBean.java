package com.maoliicai.common.bean;

import java.io.Serializable;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/8
 * @description
 */
public class BankBean implements Serializable{
    /**
     * bankCode : CMB
     * bankName : 招商银行
     * bankNote : 单笔5w/单日5w
     * bankStatus : 0
     * bankUri : CMB.png
     * czQuota : 50000
     * czQuotaDay : 50000
     * czQuotaMonth : 1000000
     * czRule : 1、充值零手续费。2、充值金额即时到帐（如遇网络繁忙，节假日等会有段时间延迟）。3、招商银行单笔限额50000元，单日限额50000元。4、充值错误码含义。
     * id : 9
     * index : 5
     * status : 0
     * txQuota : 200000
     * txRule : 1、提现零手续费。2、为防止恶意提现，每天只能提现一次。3、招商银行单笔提现额度不得超过200000元。4、工作日内提现：15：30之前提现当天到账；15:30之后提现顺延至下个工作日到账；节假日内提现，自动顺延至工作日到账。
     * type : 1
     */

    private String bankCode;//银行简称
    private String bankName;//银行名称
    private String bankNote;//单笔单日限制
    private String bankStatus;//状态 0-正常 1-维护中
    private String bankUri;//银行图片名称
    private int czQuota;//单笔限制金额 单位：元
    private int czQuotaDay;//单日限制金额 单位：元
    private int czQuotaMonth;//每月限制金额 单位：元
    private String czRule;//充值规则
    private int id;
    private int index;
    private String status;//是否支持银行 0-支持 1-不支持
    private int txQuota;//提现限金额 单位：元
    private String txRule;//提现规则
    private String type;

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

    public String getBankNote() {
        return bankNote;
    }

    public void setBankNote(String bankNote) {
        this.bankNote = bankNote;
    }

    public String getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(String bankStatus) {
        this.bankStatus = bankStatus;
    }

    public String getBankUri() {
        return bankUri;
    }

    public void setBankUri(String bankUri) {
        this.bankUri = bankUri;
    }

    public int getCzQuota() {
        return czQuota;
    }

    public void setCzQuota(int czQuota) {
        this.czQuota = czQuota;
    }

    public int getCzQuotaDay() {
        return czQuotaDay;
    }

    public void setCzQuotaDay(int czQuotaDay) {
        this.czQuotaDay = czQuotaDay;
    }

    public int getCzQuotaMonth() {
        return czQuotaMonth;
    }

    public void setCzQuotaMonth(int czQuotaMonth) {
        this.czQuotaMonth = czQuotaMonth;
    }

    public String getCzRule() {
        return czRule;
    }

    public void setCzRule(String czRule) {
        this.czRule = czRule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTxQuota() {
        return txQuota;
    }

    public void setTxQuota(int txQuota) {
        this.txQuota = txQuota;
    }

    public String getTxRule() {
        return txRule;
    }

    public void setTxRule(String txRule) {
        this.txRule = txRule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
