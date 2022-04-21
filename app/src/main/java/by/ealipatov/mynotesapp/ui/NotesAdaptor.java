package by.ealipatov.mynotesapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.Note;

public class NotesAdaptor extends RecyclerView.Adapter<NotesAdaptor.NotesViewHolder> {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM, HH:mm", Locale.getDefault());
    private OnNoteClicked noteClicked;
    private List<Note> dataNotes = new ArrayList<>();

    public OnNoteClicked getNoteClicked() {
        return noteClicked;
    }

    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    public void setDataNotes(Collection<Note> notes) {
        dataNotes.addAll(notes);
    }

    //Делаем "слепок" элемента списка
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        NotesViewHolder notesViewHolder = new NotesViewHolder(itemView);

        return notesViewHolder;
    }

    //Создаем сам саписок
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Note note = dataNotes.get(position);

        holder.name.setText(note.getName());
        holder.date.setText(simpleDateFormat.format(note.getDate()));

        if (!note.isImportant()) {
            holder.important.setImageResource(R.drawable.ic_baseline_assignment_24);
        } else {
            holder.important.setImageResource(R.drawable.ic_baseline_assignment_late_24);
        }
    }

    //Передаем количество элементов списка
    @Override
    public int getItemCount() {
        return dataNotes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView date;
        ImageView important;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            important = itemView.findViewById(R.id.important);

            itemView.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (noteClicked != null) {
                        int clickedPosition = getAdapterPosition();
                        noteClicked.onNoteClicked(dataNotes.get(clickedPosition));
                    }
                }
            });
        }
    }
}

