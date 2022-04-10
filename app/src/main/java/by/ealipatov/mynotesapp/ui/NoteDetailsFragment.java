package by.ealipatov.mynotesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.Notes;

public class NoteDetailsFragment extends Fragment {

    private static final String ARG_NOTES = "ARG_NOTES";

    public static NoteDetailsFragment newInstance(Notes notes) {
        
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTES, notes);
        
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    

    public NoteDetailsFragment(){
        super(R.layout.fragmen_notes_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = view.findViewById(R.id.name);
        TextView description = view.findViewById(R.id.description);
        TextView date = view.findViewById(R.id.date);
        ImageView important = view.findViewById(R.id.important);

        Notes notes = getArguments().getParcelable(ARG_NOTES);

        name.setText(notes.getName());
        description.setText(notes.getDescription());
        date.setText(notes.getDate());

        if(!notes.isImportant()){
            important.setImageResource(R.drawable.ic_baseline_assignment_24);
        } else
            important.setImageResource(R.drawable.ic_baseline_assignment_late_24);

    }
}
