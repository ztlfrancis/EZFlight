package com.tianlin.booking.Entity;

public class Customer {
    private String Name;
    private String money;

    public Customer(String name, String money) {
        this.Name = name;
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public String getName() {
        return Name;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setName(String name) {
        Name = name;
    }
}
