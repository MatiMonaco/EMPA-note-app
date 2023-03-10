package empa.mmonaco.noteapp.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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

    private Editable titleInput,bodyInput;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = NoteListViewModel.get(requireActivity());


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button addButton = view.findViewById(R.id.button_add);
        addButton.setOnClickListener(view1 -> {
            addNewNote();
            NavHostFragment.findNavController(AddNoteFragment.this)
                    .navigate(R.id.action_AddNoteFragment_to_NoteListFragment);
        });


        bodyInput = ((TextInputEditText)view.findViewById(R.id.text_input_note_body)).getText();
        titleInput = ((TextInputEditText)view.findViewById(R.id.text_input_note_title)).getText();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void addNewNote(){
        System.out.println("ADDING NOTE");
        viewModel.addNewNote(new Note(null,defaultTitle(titleInput.toString()),defaultBody(bodyInput.toString()),new Date(),new Date()));

    }

    private String defaultTitle(String title){

        return title != null ? title: "Empty title";
    }

    private String defaultBody(String body){
        return body != null ? body : "";
    }

}