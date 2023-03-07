package empa.mmonaco.noteapp.activities.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import empa.mmonaco.noteapp.R;
import empa.mmonaco.noteapp.databinding.NoteListItemBinding;
import empa.mmonaco.noteapp.models.Note;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>{

    private List<Note> noteList;

    public NoteListAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_item, parent, false);
        final NoteViewHolder viewHolder = new NoteViewHolder(view);

        view.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) {
                return;
            }

            openNote(position);
        });


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void openNote(final int position){
        if(position == RecyclerView.NO_POSITION){
            return;
        }
        Note note = noteList.get(position);
        System.out.println("Opening NOTE #"+position);
        System.out.println("Title: "+note.getText());
        System.out.println("Created at: "+note.getCreatedAt());
        System.out.println("Updated at: "+note.getUpdatedAt());
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder{
        public NoteListItemBinding binding;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NoteListItemBinding.bind(itemView);
        }
        public void bind(Note note) {

            binding.textViewNoteTitle.setText(note.getTitle());
            binding.textViewNoteTitle.setVisibility(View.VISIBLE);

            String createdString = null;
            if (note.getCreatedAt() != null) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                createdString = df.format(note.getCreatedAt());
            }

            if (createdString != null) {
                binding.textViewNoteCreated.setVisibility(View.VISIBLE);
                binding.textViewNoteCreated.setText(createdString);
            } else {
                binding.textViewNoteCreated.setText("");
                binding.textViewNoteCreated.setVisibility(View.GONE);
            }



            String updatedString = null;
            if (note.getUpdatedAt() != null) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                updatedString = df.format(note.getUpdatedAt());
            }

            if (updatedString != null) {
                binding.textViewNoteUpdated.setVisibility(View.VISIBLE);
                binding.textViewNoteUpdated.setText(updatedString);
            } else {
                binding.textViewNoteUpdated.setText("");
                binding.textViewNoteUpdated.setVisibility(View.GONE);
            }
        }
    }
}
