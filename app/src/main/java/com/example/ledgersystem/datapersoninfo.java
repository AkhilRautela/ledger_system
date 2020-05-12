package com.example.ledgersystem;

public class datapersoninfo {
    String from;
    String money;
    String remark;
    String giveottake;

    public datapersoninfo(String from, String money, String remark, String giveottake) {
        this.from = from;
        this.money = money;
        this.remark = remark;
        this.giveottake = giveottake;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGiveottake() {
        return giveottake;
    }

    public void setGiveottake(String giveottake) {
        this.giveottake = giveottake;
    }



}
