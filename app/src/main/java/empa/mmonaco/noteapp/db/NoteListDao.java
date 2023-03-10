package empa.mmonaco.noteapp.db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import empa.mmonaco.noteapp.models.Note;


@Dao
public interface NoteListDao {

    @Insert
    long insert(NoteDb noteDb);

    @Delete
    void delete(NoteDb noteDb);

    @Update
    void update(NoteDb noteDb);

    @Nullable
    @Query("SELECT * FROM " + NoteDb.TABLE_NAME + " where id = :id")
    NoteDb findNoteById(@NonNull Long id);

    @Query("SELECT id,title,createdAt,updatedAt FROM "+ NoteDb.TABLE_NAME + " ORDER BY createdAt ASC")
    LiveData<List<Note>> findNotes();

    @Query("SELECT COUNT(*) FROM " + NoteDb.TABLE_NAME)
    long getNoteCount();



}
