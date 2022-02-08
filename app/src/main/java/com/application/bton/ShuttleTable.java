package com.application.bton;

public class ShuttleTable {
    private String category; // notice or schedule
    private String textPost; // notice text or schedule tex
    private String tag; // local van or paran shuttle
    private String uploader; // account from which the text is uploaded

    public ShuttleTable(){
        category = null;
        textPost = null;
        tag = null;
        uploader = null;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
