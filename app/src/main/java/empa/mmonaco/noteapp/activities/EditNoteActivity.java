package empa.mmonaco.noteapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import empa.mmonaco.noteapp.databinding.ActivityEditNoteBinding;
import empa.mmonaco.noteapp.models.Note;
import empa.mmonaco.noteapp.models.NoteViewModel;

public class EditNoteActivity extends AppCompatActivity {

    public static final String PARAM_EDIT_NOTE = "editNoteId";
    private ActivityEditNoteBinding binding;
    private NoteViewModel viewModel;

    private Long noteId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = NoteViewModel.get(this);
        binding = ActivityEditNoteBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.buttonSave.setOnClickListener(v->onSaveClicked());

        Intent args = getIntent();
        noteId = args.getLongExtra(PARAM_EDIT_NOTE,-1);
        if(noteId == -1)
            throw new IllegalStateException("EditNoteActivity did not receive note ID as argument.");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewModel.loadNote(noteId);
        System.out.println("TITLE: "+ binding.editTextInputNoteTitle.getText());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void onSaveClicked(){
        System.out.println("SAVING CLICKED");

        // Verify fields

        //Save into DB
        viewModel.saveNote();
        // Return result
        setResult(Activity.RESULT_OK);
        finish();
    }

    public static class Contract extends ActivityResultContract<Long, Integer> {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Long noteId) {
            Intent intent = new Intent(context, EditNoteActivity.class);
            intent.putExtra(PARAM_EDIT_NOTE, noteId);
            return intent;
        }

        @Override
        public Integer parseResult(int resultCode, @Nullable Intent intent) {
           return resultCode;
        }
    }



}