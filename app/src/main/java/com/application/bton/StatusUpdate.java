package com.application.bton;

public class StatusUpdate {
    private String tag;
    private String status; // can be changed to boolean later, if no conflict occurs.

    public StatusUpdate() {
        tag = null;
        status = null;
    }

    public String getTag() {
        return tag;
    }

    public String getStatus() {
        return status;
    }
}
