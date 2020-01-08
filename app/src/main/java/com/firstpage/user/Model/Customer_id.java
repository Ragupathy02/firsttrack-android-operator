package com.firstpage.user.Model;

import java.io.Serializable;

public class Customer_id implements Serializable {
    private String image;

    private String password;

    private String company_id;

    private String customer_number;

    private String customer_email;

    private String created_at;

    private String _id;

    private String customer_name;

    private String customer_code;

    private String status;

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getCompany_id ()
    {
        return company_id;
    }

    public void setCompany_id (String company_id)
    {
        this.company_id = company_id;
    }

    public String getCustomer_number ()
    {
        return customer_number;
    }

    public void setCustomer_number (String customer_number)
    {
        this.customer_number = customer_number;
    }

    public String getCustomer_email ()
    {
        return customer_email;
    }

    public void setCustomer_email (String customer_email)
    {
        this.customer_email = customer_email;
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

    public String getCustomer_name ()
    {
        return customer_name;
    }

    public void setCustomer_name (String customer_name)
    {
        this.customer_name = customer_name;
    }

    public String getCustomer_code ()
    {
        return customer_code;
    }

    public void setCustomer_code (String customer_code)
    {
        this.customer_code = customer_code;
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
        return "ClassPojo [image = "+image+", password = "+password+", company_id = "+company_id+", customer_number = "+customer_number+", customer_email = "+customer_email+", created_at = "+created_at+", _id = "+_id+", customer_name = "+customer_name+", customer_code = "+customer_code+", status = "+status+"]";
    }



}
