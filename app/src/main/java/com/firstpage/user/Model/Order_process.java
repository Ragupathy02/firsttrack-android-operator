package com.firstpage.user.Model;

import java.io.Serializable;

public class Order_process implements Serializable {


    public Processes getProcess() {
        return process;
    }

    public void setProcess(Processes process) {
        this.process = process;
    }

    private Processes process;

    private String slack_time;

    private String created_at;

    private String _id;

    private String process_time;

    private int status;

    private String order;



    public String getSlack_time ()
    {
        return slack_time;
    }

    public void setSlack_time (String slack_time)
    {
        this.slack_time = slack_time;
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

    public String getProcess_time ()
    {
        return process_time;
    }

    public void setProcess_time (String process_time)
    {
        this.process_time = process_time;
    }

    public int getStatus ()
    {
        return status;
    }

    public void setStatus (int status)
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
        return "Order_process{" +
                "process=" + process +
                ", slack_time='" + slack_time + '\'' +
                ", created_at='" + created_at + '\'' +
                ", _id='" + _id + '\'' +
                ", process_time='" + process_time + '\'' +
                ", status='" + status + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
