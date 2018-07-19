package com.maoliicai.common.bean;

import java.io.Serializable;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/8
 * @description
 */

public class MyAccountBean implements Serializable {

    /**
     * qq : 493463269
     * contactUs : {"id":"3.3.0","insertTime":1524479174000,"officialAccounts":"公众号","officialWebsite":"http://www.ibrjf.com","serviceTel":"400-060-8387","updateTime":1524479177000}
     * bank : {"bankCode":"CMB","bankName":"招商银行","bankNote":"单笔5w/单日5w","bankStatus":"0","bankUri":"CMB.png","czQuota":50000,"czQuotaDay":50000,"czQuotaMonth":1000000,"czRule":"1、充值零手续费。2、充值金额即时到帐（如遇网络繁忙，节假日等会有段时间延迟）。3、招商银行单笔限额50000元，单日限额50000元。4、充值错误码含义。","id":9,"index":5,"status":"0","txQuota":200000,"txRule":"1、提现零手续费。2、为防止恶意提现，每天只能提现一次。3、招商银行单笔提现额度不得超过200000元。4、工作日内提现：15：30之前提现当天到账；15:30之后提现顺延至下个工作日到账；节假日内提现，自动顺延至工作日到账。","type":"1"}
     * couponNumber : 7
     * moneyData : {"addUpMoney":496.72,"allMoney":1390204.72,"freezeMoney":0,"frostMoney":520,"investmentMoney":82700,"leftMoney":1306488,"phone":"","toBeSettledMoney":496.72,"totalMoney":0}
     * user : {"buyFreshmanProduct":"0","buyProduct":"1","cardType":"移动","city":"黄冈","id":29625,"insertTime":1524197465000,"lastTime":1525659834000,"password":"B56466F3774602E8178EDE54B1BF5226EB71FFBACA326464","payPassword":"B56466F3774602E8178EDE54B1BF5226EB71FFBACA326464","phone":"C501F94F5B463B3EAD846D02FDA1220DE4C19E2DD85A44A1139DDB2E7AAF618A1D3FC3ED56A72DCD","province":"湖北","registChannel":"0","registPlatform":"1","registType":"0","serviceId":47,"updatePasswordTime":1524197682000,"updateTime":1524197465000,"userInfoId":29601,"userStatus":"0","userType":0,"username":"15007257895","usersInfo":{"accountStatus":"1","age":"40","birthday":257875200000,"freezeMoney":8322000,"id":29601,"idcard":"AD35EC49F511E0E18B1D5362B9A0DADE80D166FC9A1A03D90E0A7E242EF6FC5D2EDD4712FA6B1BCD168E9549302AA009061E5184BE4D6746","insertTime":1524197465000,"investCount":9,"inviteEarnMoney":0,"isBindBank":"1","isVerifyIdcard":"1","isVerifyPhone":"1","leftMoney":130648800,"level":"0","nickName":"投资用户","phone":"1500****7895","realName":"董迎真","sex":"女","totalMoney":138970800,"totalPoint":2792,"totalProfit":0,"updateTime":1525677748000,"usersId":29625}}
     * account : {"bankAccount":"84F4E9BFA0E20590E7358764A90D6AA9B0EC1797E02C59D75573F16717B11F70EEEF82C884621FC8E7142E248625B239240F7A7D9F471BF0","bankAccountName":"董迎真","bankCode":"CMB","bankName":"招商银行","baofooBindStatus":"0","baofooBindTime":1524197762000,"bindId":"201804201215571000009166618","bindMobile":"C501F94F5B463B3EAD846D02FDA1220DE4C19E2DD85A44A1139DDB2E7AAF618A1D3FC3ED56A72DCD","cardLast":"8888","cardTop":"4682","id":"4028808a62e138530162e142c4ef0006","idcard":"AD35EC49F511E0E18B1D5362B9A0DADE80D166FC9A1A03D90E0A7E242EF6FC5D2EDD4712FA6B1BCD168E9549302AA009061E5184BE4D6746","identityId":"29625","identityType":2,"insertTime":1524197672000,"note":"【手机】已绑定","phone":"C501F94F5B463B3EAD846D02FDA1220DE4C19E2DD85A44A1139DDB2E7AAF618A1D3FC3ED56A72DCD","requestId":"201804201214319826","status":"0","type":"2","updateTime":1524197762000,"usersId":29625}
     */

    private String qq;//分配的客服QQ
    private ContactUsBean contactUs;//联系我们相关的信息
    private BankBean bank;//绑定的银行卡的银行相关信息
    private String couponNumber;//可用券包
    private MoneyDataBean moneyData;//资产信息
    private UserBean user;//登录用户信息
    private AccountBean account;//实名认证信息
    private String useRule; //我的投资  ---使用规则url

    public String getUseRule() {
        return useRule;
    }

    public void setUseRule(String useRule) {
        this.useRule = useRule;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public ContactUsBean getContactUs() {
        return contactUs;
    }

    public void setContactUs(ContactUsBean contactUs) {
        this.contactUs = contactUs;
    }

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }

    public MoneyDataBean getMoneyData() {
        return moneyData;
    }

    public void setMoneyData(MoneyDataBean moneyData) {
        this.moneyData = moneyData;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

}
