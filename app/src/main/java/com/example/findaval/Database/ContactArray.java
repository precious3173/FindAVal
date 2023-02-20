package com.example.findaval.Database;

public class ContactArray {

    String nameText;
    int profile;
    String status;

    public ContactArray(String nameText, int profile, String status) {
        this.nameText = nameText;
        this.profile = profile;
        this.status = status;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
