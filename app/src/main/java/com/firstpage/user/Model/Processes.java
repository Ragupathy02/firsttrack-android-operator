package com.firstpage.user.Model;

import java.io.Serializable;

public class Processes implements Serializable {
    private String company_id;

    private String process_name;

    private String process_desc;

    private String created_at;

    private String _id;

    private String status;

    private String order;

    public String getCompany_id ()
    {
        return company_id;
    }

    public void setCompany_id (String company_id)
    {
        this.company_id = company_id;
    }

    public String getProcess_name ()
    {
        return process_name;
    }

    public void setProcess_name (String process_name)
    {
        this.process_name = process_name;
    }

    public String getProcess_desc ()
    {
        return process_desc;
    }

    public void setProcess_desc (String process_desc)
    {
        this.process_desc = process_desc;
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

    public String getOrder ()
    {
        return order;
    }

    public void setOrder (String order)
    {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Processes{" +
                "company_id='" + company_id + '\'' +
                ", process_name='" + process_name + '\'' +
                ", process_desc='" + process_desc + '\'' +
                ", created_at='" + created_at + '\'' +
                ", _id='" + _id + '\'' +
                ", status='" + status + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
