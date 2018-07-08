package com.hhb.jd.pojo;


/**
 * @ClassName : Product
 * @Author : River
 * @Description (这里用一句话描述这个类的作用)
 * @Date: Create in 19:14 2018/7/7
 * @Modified By:
 */

public class Product {
    private String pid;

    private String name;

    private String price;

    private String picture;

    @Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}