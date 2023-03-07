package empa.mmonaco.noteapp.models;

import java.util.Date;

public class Note {

    private static final int NOTE_TITLE_MAX_LENGTH = 250;
    private static final int NOTE_TEXT_MAX_LENGTH = 1500;
    private Long id;
    private String title;
    private String text;
    private Date createdAt;
    private Date updatedAt;

    public Note(){}

    public Note(Long id, String title, String text, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
