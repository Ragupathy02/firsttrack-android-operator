package com.firstpage.user.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Purchase_order implements Serializable {

    public ArrayList<String> getLine_number() {
        return line_number;
    }

    public void setLine_number(ArrayList<String> line_number) {
        this.line_number = line_number;
    }

    private ArrayList<String> line_number;

    private String purchase_order_number;

    private String created_at;

    private String _id;

    private String status;



    public String getPurchase_order_number ()
    {
        return purchase_order_number;
    }

    public void setPurchase_order_number (String purchase_order_number)
    {
        this.purchase_order_number = purchase_order_number;
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

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Purchase_order{" +
                "line_number=" + line_number +
                ", purchase_order_number='" + purchase_order_number + '\'' +
                ", created_at='" + created_at + '\'' +
                ", _id='" + _id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
