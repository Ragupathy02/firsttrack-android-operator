package com.firstpage.user.Model;

import java.util.ArrayList;

public class Data {
    private String operator_name;

    private String image;

    private String password;

    public ArrayList<String> getRole() {
        return role;
    }

    public void setRole(ArrayList<String> role) {
        this.role = role;
    }

    private ArrayList<String> role;

    private Company_id company_id;

    private String operator_email;

    private String created_at;

    private String _id;

    private String operator_code;

    private String status;

    private String token;

    public String getOperator_name ()
    {
        return operator_name;
    }

    public void setOperator_name (String operator_name)
    {
        this.operator_name = operator_name;
    }

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


    public Company_id getCompany_id ()
    {
        return company_id;
    }

    public void setCompany_id (Company_id company_id)
    {
        this.company_id = company_id;
    }

    public String getOperator_email ()
    {
        return operator_email;
    }

    public void setOperator_email (String operator_email)
    {
        this.operator_email = operator_email;
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

    public String getOperator_code ()
    {
        return operator_code;
    }

    public void setOperator_code (String operator_code)
    {
        this.operator_code = operator_code;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [operator_name = "+operator_name+", image = "+image+", password = "+password+", role = "+role+", company_id = "+company_id+", operator_email = "+operator_email+", created_at = "+created_at+", _id = "+_id+", operator_code = "+operator_code+", status = "+status+", token = "+token+"]";
    }

}
