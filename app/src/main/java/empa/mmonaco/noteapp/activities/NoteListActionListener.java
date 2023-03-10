package empa.mmonaco.noteapp.activities;

import androidx.annotation.NonNull;

import empa.mmonaco.noteapp.models.Note;

public interface NoteListActionListener {

    void onNoteClicked(@NonNull Note note);
}
