package com.ktiuu.spring01;

/**
 * @Create by pankun
 * @DATE 2020/6/28
 */
public class Phone {
    private String color;
    private int size;
    private String remark;


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "color='" + color + '\'' +
                ", size=" + size +
                ", remark='" + remark + '\'' +
                '}';
    }
}
