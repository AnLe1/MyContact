package com.lca.app.mycontact.model;

/**
 * Created by Admin on 03/03/2018.
 */

public class Contact {
    private boolean isMale;
    private String name;
    private String phone;

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact(boolean isMale, String name, String phone) {

        this.isMale = isMale;
        this.name = name;
        this.phone = phone;
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
