package com.firstpage.user.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Ongoing_response implements Serializable {
    private ArrayList<Ongoing> data;

    private boolean status;

    public ArrayList<Ongoing> getData() {
        return data;
    }

    public void setData(ArrayList<Ongoing> data) {
        this.data = data;
    }





    public boolean getStatus ()
    {
        return status;
    }

    public void setStatus (boolean status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }
}
