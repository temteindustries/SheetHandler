package com.temteindustries.sheethandler.model;

public class GuestObject {

    private String fName,lName,ceremony,reception;

    public void setFName(String name){
        this.fName = name;
    }
    public void setLName(String name){
        this.lName = name;
    }
    public void setCeremony(String rsvp){
        this.ceremony = rsvp;
    }
    public void setReception(String rsvp){
        this.reception = rsvp;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getCeremony() {
        return ceremony;
    }

    public String getReception() {
        return reception;
    }

    public String toString(){
        return "{\"firstname\":\""+getfName()+"\"," +
                "\"lastname\":\""+getlName()+"\"," +
                "\"ceremony\":\""+getCeremony()+"\"," +
                "\"reception\":\""+getReception()+"\"}";
    }
}
