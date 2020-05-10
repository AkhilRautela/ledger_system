package com.example.ledgersystem;

public class money_split_data {
    String name;
    String money;

    money_split_data(String name,String money){
        this.name=name;
        this.money=money;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
