package empa.mmonaco.noteapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Collections;
import java.util.Queue;

import empa.mmonaco.noteapp.R;
import empa.mmonaco.noteapp.activities.adapters.NoteListAdapter;
import empa.mmonaco.noteapp.databinding.FragmentNoteListBinding;
import empa.mmonaco.noteapp.models.Note;
import empa.mmonaco.noteapp.models.NoteListViewModel;

public class NoteListFragment extends Fragment {

    private FragmentNoteListBinding binding;

    private NoteListAdapter adapter;

    private NoteListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = NoteListViewModel.get(getActivity());
        System.out.println(viewModel.getNoteList());
        viewModel.getNoteList().observe(this,notes -> {
            adapter.setData(notes);
            refreshView(!notes.isEmpty());
            System.out.println("NOTES CHANGED: "+notes);
        });

    }



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNoteListBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fab.setOnClickListener(view1 -> NavHostFragment.findNavController(NoteListFragment.this)
                .navigate(R.id.action_NoteListFragment_to_AddNoteFragment));

        binding.recyclerViewNoteList.setHasFixedSize(true);
        binding.recyclerViewNoteList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new NoteListAdapter(Collections.emptyList());
        binding.recyclerViewNoteList.setAdapter(adapter);

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

}