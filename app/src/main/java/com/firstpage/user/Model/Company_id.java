package com.firstpage.user.Model;

public class Company_id {
    private String image;

    private String password;

    private String role;

    private String email_verified;

    private String company_name;

    private String created_at;

    private String phone_number;

    private String industry_type;

    private String _id;

    private String email;

    private String status;

    private String token;

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

    public String getRole ()
    {
        return role;
    }

    public void setRole (String role)
    {
        this.role = role;
    }

    public String getEmail_verified ()
    {
        return email_verified;
    }

    public void setEmail_verified (String email_verified)
    {
        this.email_verified = email_verified;
    }

    public String getCompany_name ()
    {
        return company_name;
    }

    public void setCompany_name (String company_name)
    {
        this.company_name = company_name;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getPhone_number ()
    {
        return phone_number;
    }

    public void setPhone_number (String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getIndustry_type ()
    {
        return industry_type;
    }

    public void setIndustry_type (String industry_type)
    {
        this.industry_type = industry_type;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
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
        return "ClassPojo [image = "+image+", password = "+password+", role = "+role+", email_verified = "+email_verified+", company_name = "+company_name+", created_at = "+created_at+", phone_number = "+phone_number+", industry_type = "+industry_type+", _id = "+_id+", email = "+email+", status = "+status+", token = "+token+"]";
    }


}
