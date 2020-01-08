package com.firstpage.user.Model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Loginresponse {


    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    private ArrayList<Data> data;

    private String status;
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }
}
