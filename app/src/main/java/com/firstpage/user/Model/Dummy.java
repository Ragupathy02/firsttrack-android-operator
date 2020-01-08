package com.firstpage.user.Model;

public class Dummy {
    public String name;
    public Dummy(String name)
    {
        this.name=name;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }
}
