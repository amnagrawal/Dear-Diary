package com.aman.deardiary;

/**
 * Created by aman on 26/12/15.
 */
public class ListItemInfo {
    public String date;
    public String content;
    public int imageID;

    public ListItemInfo(int imageID, String date, String content) {
        this.imageID = imageID;
        this.content = content;
        this.date = date;
    }

    public ListItemInfo(String date, String content) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
