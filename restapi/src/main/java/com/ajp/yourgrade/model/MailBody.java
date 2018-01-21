package com.ajp.yourgrade.model;

public class MailBody {

    private String userToken;
    private int groupId;

    //Dummy constructor
    public MailBody(){

    }

    public MailBody(String userToken, int groupId) {
        this.userToken = userToken;
        this.groupId = groupId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
