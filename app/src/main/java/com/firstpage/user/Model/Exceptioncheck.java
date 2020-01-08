package com.firstpage.user.Model;

public class Exceptioncheck {
    public String line_number;
    public String comment;
    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Exceptioncheck{" +
                "line_number='" + line_number + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }


}
