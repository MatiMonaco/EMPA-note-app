package empa.mmonaco.noteapp.db;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {NoteDb.class},version=1)
@TypeConverters({DateConverter.class})
public abstract class NotesDatabase extends RoomDatabase{


    private static NotesDatabase notesDatabase;

    public static NotesDatabase getInstance(Context applicationContext) {
        System.out.println("DATABASE GET INSTANCE");
        if(notesDatabase == null){
            System.out.println("CREATING DATABASE");
            notesDatabase = Room.databaseBuilder(applicationContext,
                    NotesDatabase.class,"notesDb").build();
        }

        return notesDatabase;
    }
    public abstract NoteListDao noteListDao();
}
