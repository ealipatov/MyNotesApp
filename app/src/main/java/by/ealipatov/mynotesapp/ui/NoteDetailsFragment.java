package by.ealipatov.mynotesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.Notes;

public class NoteDetailsFragment extends Fragment {

    private static final String ARG_NOTES = "ARG_NOTES";
    private TextView name;
    private TextView description;
    private TextView date;
    private ImageView important;

    public NoteDetailsFragment() {
        super(R.layout.fragmen_notes_details);
    }

    public static NoteDetailsFragment newInstance(Notes notes) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTES, notes);

        NoteDetailsFragment fragment = new NoteDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.name);
        description = view.findViewById(R.id.description);
        date = view.findViewById(R.id.date);
        important = view.findViewById(R.id.important);

        getParentFragmentManager()
                .setFragmentResultListener(NotesListFragment.NOTES_CLICKED_KEY, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Notes notes = result.getParcelable(NotesListFragment.SELECTED_NOTES);

                        showNotes(notes);
                    }
                });

        if (getArguments() != null && getArguments().containsKey(ARG_NOTES)) {
            Notes notes = getArguments().getParcelable(ARG_NOTES);

            showNotes(notes);
        }
    }

    private void showNotes(Notes notes) {
        name.setText(notes.getName());
        description.setText(notes.getDescription());
        date.setText(notes.getDate());

        if (!notes.isImportant()) {
            important.setImageResource(R.drawable.ic_baseline_assignment_24);
        } else{
            important.setImageResource(R.drawable.ic_baseline_assignment_late_24);
        }
    }

}
