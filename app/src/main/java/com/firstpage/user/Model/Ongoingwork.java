package com.firstpage.user.Model;

public class Ongoingwork {
    public int job_no;
    public int quantity;
    public int line_no;

    public Ongoingwork(int job_no, int quantity, int line_no)
    {
        this.job_no=job_no;
        this.quantity=quantity;
        this.line_no=line_no;
    }

    public int getJob_no() {
        return job_no;
    }

    public void setJob_no(int job_no) {
        this.job_no = job_no;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLine_no() {
        return line_no;
    }

    public void setLine_no(int line_no) {
        this.line_no = line_no;
    }
}
