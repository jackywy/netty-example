package com.wy.example.netty.example_http_xml_103.pojo;

/**
 * http+xml协议栈开发
 *
 * @author Jacky
 * @version 1.0
 * @create 2017-01-05  12:40
 **/
public class Customer {
    private long customerNumber;
    private String firstName;

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
