package com.firstpage.user.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Ongoingjob implements Serializable {
    private Line_numbers line_numbers;

    private Purchase_order purchase_order;

    private String line_number;

    private String created_at;

    private String _id;




    public ArrayList<Order_process> getOrder_process() {
        return order_process;
    }

    public void setOrder_process(ArrayList<Order_process> order_process) {
        this.order_process = order_process;
    }

    private ArrayList<Order_process> order_process;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

    public Line_numbers getLine_numbers ()
    {
        return line_numbers;
    }

    public void setLine_numbers (Line_numbers line_numbers)
    {
        this.line_numbers = line_numbers;
    }

    public Purchase_order getPurchase_order ()
    {
        return purchase_order;
    }

    public void setPurchase_order (Purchase_order purchase_order)
    {
        this.purchase_order = purchase_order;
    }

    public String getLine_number ()
    {
        return line_number;
    }

    public void setLine_number (String line_number)
    {
        this.line_number = line_number;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }



    @Override
    public String toString()
    {
        return "ClassPojo [line_numbers = "+line_numbers+", purchase_order = "+purchase_order+", line_number = "+line_number+", created_at = "+created_at+", _id = "+_id+", order_process = "+order_process+", status = "+status+"]";
    }



}
