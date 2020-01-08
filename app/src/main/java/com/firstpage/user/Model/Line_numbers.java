package com.firstpage.user.Model;

import java.io.Serializable;

public class Line_numbers implements Serializable {
    public String getTentative_days() {
        return tentative_days;
    }

    public void setTentative_days(String tentative_days) {
        this.tentative_days = tentative_days;
    }

    private String tentative_days;

    private String quantity;

    public String getTentative_date() {
        return tentative_date;
    }

    public void setTentative_date(String tentative_date) {
        this.tentative_date = tentative_date;
    }

    private String tentative_date;

    private String part_number;

    private String created_at;

    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
    }

    private String line_number;

    public String getIs_exception() {
        return is_exception;
    }

    public void setIs_exception(String is_exception) {
        this.is_exception = is_exception;
    }

    private String is_exception;


    private String _id;

    private String job_number;

    private Customer_id customer_id;

    private String status;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;


    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }


    public String getPart_number ()
    {
        return part_number;
    }

    public void setPart_number (String part_number)
    {
        this.part_number = part_number;
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

    public String getJob_number ()
    {
        return job_number;
    }

    public void setJob_number (String job_number)
    {
        this.job_number = job_number;
    }

    public Customer_id getCustomer_id ()
    {
        return customer_id;
    }

    public void setCustomer_id (Customer_id customer_id)
    {
        this.customer_id = customer_id;
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
        return "Line_numbers{" +
                "tentative_days='" + tentative_days + '\'' +
                ", quantity='" + quantity + '\'' +
                ", tentative_date='" + tentative_date + '\'' +
                ", part_number='" + part_number + '\'' +
                ", created_at='" + created_at + '\'' +
                ", line_number='" + line_number + '\'' +
                ", is_exception='" + is_exception + '\'' +
                ", _id='" + _id + '\'' +
                ", job_number='" + job_number + '\'' +
                ", customer_id=" + customer_id +
                ", status='" + status + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
