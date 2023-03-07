package empa.mmonaco.noteapp.models;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import empa.mmonaco.noteapp.db.NoteDb;
import empa.mmonaco.noteapp.db.NotesDatabase;

public class NoteListViewModel extends ViewModel {

    private final NotesDatabase db;

    public NoteListViewModel(NotesDatabase db){
        this.db = db;
    }

    public static NoteListViewModel get(FragmentActivity activity) {
        return new ViewModelProvider(activity, new Factory(activity)).get(NoteListViewModel.class);
    }

    public void addNewNote(@NonNull Note note){
        db.noteListDao().insert(new NoteDb(note));
    }

    public LiveData<List<Note>> getNoteList(){
        return db.noteListDao().findNotes();
    }

    public Long getNoteCount(){
        return db.noteListDao().getNoteCount();
    }

    @NonNull
    private NoteDb findNote(Long noteId) {
        NoteDb note = db.noteListDao().findNoteById(noteId);
        if (note == null) {
            throw new IllegalStateException("Could not fetch note with ID "+noteId);
        }
        return note;
    }
    private static class Factory implements ViewModelProvider.Factory {
        private final Context context;

        Factory(Context context) {
            this.context = context.getApplicationContext();
        }

        @SuppressWarnings("unchecked")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

            return (T) new NoteListViewModel(NotesDatabase.getInstance(context));
        }
    }
}
