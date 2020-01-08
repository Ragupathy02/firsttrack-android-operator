package com.firstpage.user.Model;


public class Conform_order_response {
    private String data;
    private boolean status;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Conform_order_response{" +
                "data='" + data + '\'' +
                ", status=" + status +
                '}';
    }


}