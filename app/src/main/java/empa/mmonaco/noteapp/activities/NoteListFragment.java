package empa.mmonaco.noteapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import empa.mmonaco.noteapp.R;
import empa.mmonaco.noteapp.databinding.FragmentNoteListBinding;

public class NoteListFragment extends Fragment {

    private FragmentNoteListBinding binding;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}