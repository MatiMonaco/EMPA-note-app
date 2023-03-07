package empa.mmonaco.noteapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

import empa.mmonaco.noteapp.R;
import empa.mmonaco.noteapp.databinding.FragmentAddNoteBinding;
import empa.mmonaco.noteapp.models.Note;
import empa.mmonaco.noteapp.models.NoteListViewModel;

public class AddNoteFragment extends Fragment {

    private FragmentAddNoteBinding binding;

    private NoteListViewModel viewModel;

    private TextInputEditText titleInput,bodyInput;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = NoteListViewModel.get(getActivity());

    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddNoteBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button addButton = view.findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(view1 -> {
            addNewNote();
            NavHostFragment.findNavController(AddNoteFragment.this)
                    .navigate(R.id.action_AddNoteFragment_to_NoteListFragment);
        });
        bodyInput = view.findViewById(R.id.textInputNoteBody);
        titleInput = view.findViewById(R.id.textInputNoteTitle);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void addNewNote(){

        String noteTitle = titleInput.getText() != null ? titleInput.getText().toString() : defaultTitle();
        String noteBody = bodyInput.getText() != null ? bodyInput.getText().toString() : defaultBody();
        viewModel.addNewNote(new Note(null,noteTitle,noteBody,new Date(),new Date()));
        System.out.println("ADDING NOTE");
    }

    private String defaultTitle(){
        long newNoteNumber = viewModel.getNoteCount() +1;
        return "Note #"+newNoteNumber;
    }

    private String defaultBody(){
        return "";
    }

}