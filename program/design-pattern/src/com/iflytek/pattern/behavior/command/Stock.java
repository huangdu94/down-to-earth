package com.iflytek.pattern.behavior.command;

/**
 * 请求类
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:04
 */
public class Stock {
    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [ Name: " + name + ", Quantity:" + quantity + " ]bought ");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name + ", Quantity:" + quantity + " ]sold ");
    }
}
