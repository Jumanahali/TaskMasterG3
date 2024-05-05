package com.example.taskmaster;

public class ordermod {
    private int orderID;
    private int serviceID;
    private String clientID;
    private String location;
    private int rate;
    private String time;
    private String workerName;
    private String orderStatus;
    private String workerPhone;

    public ordermod() {
    }

    public ordermod(int orderID, int serviceID, String clientID, String location, int rate, String time, String workerName, String orderStatus, String workerPhone) {
        this.orderID = orderID;
        this.serviceID = serviceID;
        this.clientID = clientID;
        this.location = location;
        this.rate = rate;
        this.time = time;
        this.workerName = workerName;
        this.orderStatus = orderStatus;
        this.workerPhone = workerPhone;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }


}

