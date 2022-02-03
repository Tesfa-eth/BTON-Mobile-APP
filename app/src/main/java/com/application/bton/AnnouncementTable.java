package com.application.bton;

public class AnnouncementTable {
    private String dateAndTime;
    private String messageFrom;
    private String messageContent;
    private String posterEmail; // email of the person who posted the announcement

    public AnnouncementTable(){
        dateAndTime = null;
        messageFrom = null;
        messageContent = null;
        posterEmail = null;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getPosterEmail() {
        return posterEmail;
    }

    public void setPosterEmail(String senderEmail) {
        this.posterEmail = senderEmail;
    }
}
