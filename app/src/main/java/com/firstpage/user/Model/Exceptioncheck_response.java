package com.firstpage.user.Model;

public class Exceptioncheck_response {
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;

    @Override
    public String toString() {
        return "Exceptioncheck_response{" +
                "status=" + status +
                ", data='" + data + '\'' +
                '}';
    }
}
