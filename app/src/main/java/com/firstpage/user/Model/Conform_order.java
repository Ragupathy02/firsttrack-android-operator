package com.firstpage.user.Model;

public class Conform_order {

    private String _id;
    private int status;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Conform_order{" +
                "_id='" + _id + '\'' +
                ", status=" + status +
                '}';
    }
}
