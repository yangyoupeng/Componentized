package com.maoliicai.common.bean;

import java.io.Serializable;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/8
 * @description
 */
public class MoneyDataBean implements Serializable{
    /**
     * addUpMoney : 496.72
     * allMoney : 1390204.72
     * freezeMoney : 0
     * frostMoney : 520
     * investmentMoney : 82700
     * leftMoney : 1306488
     * phone :
     * toBeSettledMoney : 496.72
     * totalMoney : 0
     */

    private String addUpMoney;//累计收益
    private String allMoney;//总资产
    private String frostMoney;//冻结金额
    private String investmentMoney;//投资中金额(待收本金)
    private String leftMoney;//可用余额
    private String toBeSettledMoney;//待收收益
    private String totalMoney;//用户总收益

    public String getAddUpMoney() {
        return addUpMoney;
    }

    public void setAddUpMoney(String addUpMoney) {
        this.addUpMoney = addUpMoney;
    }

    public String getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(String allMoney) {
        this.allMoney = allMoney;
    }

    public String getFrostMoney() {
        return frostMoney;
    }

    public void setFrostMoney(String frostMoney) {
        this.frostMoney = frostMoney;
    }

    public String getInvestmentMoney() {
        return investmentMoney;
    }

    public void setInvestmentMoney(String investmentMoney) {
        this.investmentMoney = investmentMoney;
    }

    public String getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(String leftMoney) {
        this.leftMoney = leftMoney;
    }

    public String getToBeSettledMoney() {
        return toBeSettledMoney;
    }

    public void setToBeSettledMoney(String toBeSettledMoney) {
        this.toBeSettledMoney = toBeSettledMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
}
