package com.example.ledgersystem;

public class notification_data {
    String msg;
    String from;

    public notification_data(String msg, String from, String remark) {
        this.msg = msg;
        this.from = from;
        Remark = remark;
    }

    String Remark;

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
