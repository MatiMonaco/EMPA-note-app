package empa.mmonaco.noteapp.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import empa.mmonaco.noteapp.models.Note;

@Entity(
        tableName = NoteDb.TABLE_NAME
)
public class NoteDb {
    public static final String TABLE_NAME = "notes";


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NonNull
    private String title;

    private String body;

    @NonNull
    private Date createdAt;

    private Date updatedAt;

    public NoteDb() {
    }

    @Ignore
    public NoteDb(Note model) {
        this.id = model.getId();
        this.title = model.getTitle();
        this.body = model.getBody();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();
    }

    public Note toModel(){
       return new Note(id,title,body,createdAt,updatedAt);
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @NonNull
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
