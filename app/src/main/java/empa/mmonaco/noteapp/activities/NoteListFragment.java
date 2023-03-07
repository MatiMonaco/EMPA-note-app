package empa.mmonaco.noteapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import empa.mmonaco.noteapp.R;
import empa.mmonaco.noteapp.activities.adapters.NoteListAdapter;
import empa.mmonaco.noteapp.databinding.FragmentNoteListBinding;
import empa.mmonaco.noteapp.models.Note;

public class NoteListFragment extends Fragment {

    private FragmentNoteListBinding binding;

    private NoteListAdapter adapter;

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
        adapter = new NoteListAdapter(List.of(new Note(1L,"Title 1","Text 1",new Date(),new Date())));
        binding.recyclerViewNoteList.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}