package com.maoliicai.common.bean;

import java.io.Serializable;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/8
 * @description
 */
public class ContactUsBean implements Serializable {
    /**
     * id : 3.3.0
     * insertTime : 1524479174000
     * officialAccounts : 公众号
     * officialWebsite : http://www.ibrjf.com
     * serviceTel : 400-060-8387
     * updateTime : 1524479177000
     * customerServiceHours : 工作日 （09:00-17:00）
     */

    private String id;
    private long insertTime;
    private String officialAccounts;//公众号
    private String officialWebsite;//官网
    private String serviceTel;//联系电话
    private long updateTime;
    private String customerServiceHours;//工作时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    public String getOfficialAccounts() {
        return officialAccounts;
    }

    public void setOfficialAccounts(String officialAccounts) {
        this.officialAccounts = officialAccounts;
    }

    public String getOfficialWebsite() {
        return officialWebsite;
    }

    public void setOfficialWebsite(String officialWebsite) {
        this.officialWebsite = officialWebsite;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerServiceHours() {
        return customerServiceHours;
    }

    public void setCustomerServiceHours(String customerServiceHours) {
        this.customerServiceHours = customerServiceHours;
    }
}
