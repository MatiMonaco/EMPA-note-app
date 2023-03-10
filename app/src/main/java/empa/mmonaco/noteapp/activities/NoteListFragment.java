package empa.mmonaco.noteapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Collections;

import empa.mmonaco.noteapp.R;
import empa.mmonaco.noteapp.activities.adapters.NoteListAdapter;
import empa.mmonaco.noteapp.databinding.FragmentNoteListBinding;
import empa.mmonaco.noteapp.models.Note;
import empa.mmonaco.noteapp.models.NoteListViewModel;

public class NoteListFragment extends Fragment implements NoteListActionListener {



    private FragmentNoteListBinding binding;

    private NoteListAdapter adapter;

    private NoteListViewModel viewModel;

    private final ActivityResultLauncher<Long> actionEditNote = registerForActivityResult(
            new EditNoteActivity.Contract(),
            this::onEditNoteActivityReceived
    );

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("NOTE LIST ON CREATE");
        super.onCreate(savedInstanceState);
        viewModel = NoteListViewModel.get(requireActivity());
        System.out.println(viewModel.getNoteList());
        System.out.println("list has observers: "+viewModel.getNoteList().hasObservers());
        adapter = new NoteListAdapter(Collections.emptyList(),this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("NOTE LIST ON CREATE VIEW");
        binding = FragmentNoteListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("NOTE LIST ON VIEW CREATED");
        binding.recyclerViewNoteList.setHasFixedSize(true);
        binding.recyclerViewNoteList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.fab.setOnClickListener(view1 -> NavHostFragment.findNavController(NoteListFragment.this)
                .navigate(R.id.action_NoteListFragment_to_AddNoteFragment));
        binding.recyclerViewNoteList.setAdapter(adapter);
        viewModel.getNoteList().observe(this,notes -> {
            adapter.setData(notes);
            refreshView(!notes.isEmpty());
            System.out.println("NOTES CHANGED: "+notes);
        });
    }

    private void refreshView(boolean notesFound) {
        binding.recyclerViewNoteList.setVisibility(notesFound ? View.VISIBLE : View.GONE);
        binding.textViewEmptyNotes.setVisibility(notesFound ? View.GONE : View.VISIBLE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onNoteClicked(@NonNull Note note) {
        System.out.println("NOTE :"+note.getId() +" clicked");
        actionEditNote.launch(note.getId());
    }

    public void onEditNoteActivityReceived(@NonNull Integer resultCode){
        System.out.println("EDITING NOTE RESUTLED WITH RESULT CODE: "+resultCode);
    }
}