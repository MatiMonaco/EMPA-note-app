package empa.mmonaco.noteapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import empa.mmonaco.noteapp.databinding.FragmentNoteViewBinding;
import empa.mmonaco.noteapp.models.Note;
import empa.mmonaco.noteapp.models.NoteListViewModel;

public class NoteViewFragment extends Fragment {

    private FragmentNoteViewBinding binding;

    private Note note;

    private NoteListViewModel viewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = NoteListViewModel.get(getActivity());
        System.out.println(viewModel.getNoteList());
        viewModel.getNoteList().observe(this,notes -> {
            System.out.println("NOTES: "+notes);
        });

    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNoteViewBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}