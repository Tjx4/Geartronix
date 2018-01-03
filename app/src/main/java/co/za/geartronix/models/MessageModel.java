package co.za.geartronix.models;

import android.graphics.Bitmap;
import java.util.Date;

public class MessageModel {
    private String message, header;
    private Date date;
    private Bitmap imageAttachment;
    private boolean read;
    private int sender;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bitmap getImageAttachment() {
        return imageAttachment;
    }

    public void setImageAttachment(Bitmap imageAttachment) {
        this.imageAttachment = imageAttachment;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

}
