package empa.mmonaco.noteapp.models;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import empa.mmonaco.noteapp.db.NoteDb;
import empa.mmonaco.noteapp.db.NotesDatabase;

public class NoteViewModel extends ViewModel {

    private Long id;
    private final MutableLiveData<String> title = new MutableLiveData<>(null);
    private final MutableLiveData<String> body = new MutableLiveData<>(null);
    private Date createdAt;

    private final NotesDatabase db;

    ExecutorService executor = Executors.newSingleThreadExecutor();

    public NoteViewModel(NotesDatabase db){
        this.db = db;
    }

    public void loadNote(@NonNull Long noteId){

        executor.execute(()->{
            System.out.println("LOADING NOTE "+noteId);
            NoteDb noteDb = db.noteListDao().findNoteById(noteId);
            if(noteDb == null)
                throw new IllegalStateException(String.format("NoteViewModel did not found note with ID %d.",noteId));

            Note note = noteDb.toModel();
            System.out.println("LOADED NOTE: "+note);
            id = note.getId();
            System.out.println("NOTE TITLE ASD: "+note.getTitle());
            title.postValue(note.getTitle());
            body.postValue(note.getBody());
            createdAt = note.getCreatedAt();
            System.out.println("FINISHED LOADING");
        });
    }

    public void saveNote(){
        executor.execute(()->{
            System.out.println("SAVING NOTE "+id);
            db.noteListDao().update(new NoteDb(new Note(id, title.getValue(), body.getValue(), createdAt,new Date())));
        });
    }

    public static NoteViewModel get(FragmentActivity activity) {
        System.out.println("GET NOTE VIEW MODEL");
        return new ViewModelProvider(activity, new Factory(activity)).get(NoteViewModel.class);
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
            System.out.println("NOTE FACTORY CREATE");
            return (T) new NoteViewModel(NotesDatabase.getInstance(context));
        }
    }

    public Long getId() {
        return id;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getBody() {
        return body;
    }

}
