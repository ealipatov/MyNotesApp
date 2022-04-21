package by.ealipatov.mynotesapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.Note;

public class NoteDetailsFragment extends Fragment {
    private static final String ARG_NOTES = "ARG_NOTES";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM, HH:mm", Locale.getDefault());
    private TextView name;
    private TextView description;
    private TextView date;
    private ImageView important;

    public NoteDetailsFragment() {
        super(R.layout.fragment_notes_details);
    }

    public static NoteDetailsFragment newInstance(Note notes) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTES, notes);

        NoteDetailsFragment fragment = new NoteDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof ToolbarHolder) {
            ((ToolbarHolder) requireActivity()).setToolbar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_send:
                        Toast.makeText(requireContext(), "Отправить", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_remind:
                        Toast.makeText(requireContext(), "Напомнить", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                getParentFragmentManager()
                        .popBackStack();

                return true;
            }
        });

        name = view.findViewById(R.id.name);
        description = view.findViewById(R.id.description);
        date = view.findViewById(R.id.date);
        important = view.findViewById(R.id.important);

        getParentFragmentManager()
                .setFragmentResultListener(NotesListFragment.NOTES_CLICKED_KEY, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note notes = result.getParcelable(NotesListFragment.SELECTED_NOTES);

                        showNotes(notes);
                    }
                });

        if (getArguments() != null && getArguments().containsKey(ARG_NOTES)) {
            Note notes = getArguments().getParcelable(ARG_NOTES);

            showNotes(notes);
        }
    }

    public void showNotes(Note notes) {
        name.setText(notes.getName());
        description.setText(notes.getDescription());
        date.setText(simpleDateFormat.format(notes.getDate()).toString());


        if (!notes.isImportant()) {
            important.setImageResource(R.drawable.ic_baseline_assignment_24);
        } else {
            important.setImageResource(R.drawable.ic_baseline_assignment_late_24);
        }
    }

}
