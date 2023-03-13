package empa.mmonaco.noteapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import empa.mmonaco.noteapp.R;
import empa.mmonaco.noteapp.databinding.ActivityEditNoteBinding;
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
        setContentView(binding.getRoot());
        setSupportActionBar(binding.editToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.buttonSave.setOnClickListener(v->onSaveClicked());
        binding.buttonDelete.setOnClickListener(v->onDeleteClicked());

        Intent args = getIntent();
        noteId = args.getLongExtra(PARAM_EDIT_NOTE,-1);
        if(noteId == -1)
            throw new IllegalStateException("EditNoteActivity did not receive note ID as argument.");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewModel.getTitle().observe(this, title->{
            System.out.println("TITLE CHANGED: "+title);
            binding.editTextInputNoteTitle.setText(title);
        });
        viewModel.getBody().observe(this, body->{
            System.out.println("BODY CHANGED: "+body);
            binding.exitTextInputNoteBody.setText(body);
        });
        viewModel.loadNote(noteId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void onDeleteClicked(){
        System.out.println("ON DELETE CLICKED");
        MessageUtils.showDialog("",getString(R.string.dialog_confirm_delete),()->{
            viewModel.deleteNote();
            MessageUtils.showToast(getString(R.string.note_deleted),getApplicationContext());
            finish();
        },EditNoteActivity.this);

    }

    private void onSaveClicked(){
        System.out.println("ON SAVE CLICKED");

        // Verify fields
        String newTitle = binding.editTextInputNoteTitle.getText() == null ? viewModel.getTitle().getValue() : binding.editTextInputNoteTitle.getText().toString();
        String newBody = binding.exitTextInputNoteBody.getText() == null ? viewModel.getBody().getValue() : binding.exitTextInputNoteBody.getText().toString();
        //Save into DB

        viewModel.saveNote(newTitle,newBody);
        // Return result
        setResult(Activity.RESULT_OK);
        MessageUtils.showToast(getString(R.string.note_updated),getApplicationContext());
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