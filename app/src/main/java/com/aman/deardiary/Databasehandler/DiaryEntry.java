package com.aman.deardiary.Databasehandler;

import java.util.Date;

/**
 * Created by aman on 30/12/15.
 */
public class DiaryEntry {
    //private int ID;
    private Date date;
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    private String content;

    /*public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
