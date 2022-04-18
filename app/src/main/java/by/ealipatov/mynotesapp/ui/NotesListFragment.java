package by.ealipatov.mynotesapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.util.List;

import by.ealipatov.mynotesapp.MainActivity;
import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.InMemoryNotesRepository;
import by.ealipatov.mynotesapp.domain.Notes;

public class NotesListFragment extends Fragment {

    public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
    public static final String SELECTED_NOTES = "SELECTED_NOTES";
    public static final String HELLO_SCREEN = "HELLO_SCREEN";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add_new_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new AddNewNoteFragment())
                        .addToBackStack("new_note")
                        .commit();
            }
        });

        List<Notes> note = InMemoryNotesRepository.getInstance(requireContext()).getAll();

        LinearLayout container = view.findViewById(R.id.container);

        for (Notes notes : note){
            View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);

            itemView.findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

                        Bundle bundle = new Bundle();
                        bundle.putParcelable(SELECTED_NOTES, notes);
                        getParentFragmentManager()
                                .setFragmentResult(NOTES_CLICKED_KEY, bundle);

                    } else {


                         NoteDetailsFragment noteDetailsFragment = NoteDetailsFragment.newInstance(notes);

                            getParentFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, noteDetailsFragment)
                                    .addToBackStack("detail_notes")
                                    .commit();
                    }
                }
            });



            TextView name = itemView.findViewById(R.id.name);
            name.setText(notes.getName());

            TextView date = itemView.findViewById(R.id.date);
            date.setText(notes.getDate());

            ImageView important = itemView.findViewById(R.id.important);
            if(!notes.isImportant()){
                important.setImageResource(R.drawable.ic_baseline_assignment_24);
            } else{
                important.setImageResource(R.drawable.ic_baseline_assignment_late_24);
            }

            container.addView(itemView);
        }
    }
}
