package by.ealipatov.mynotesapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.Callback;
import by.ealipatov.mynotesapp.domain.InMemoryNotesRepository;
import by.ealipatov.mynotesapp.domain.Note;

public class AddNewNoteFragment extends Fragment {

    public static final String KEY_RESULT = "ADD_NEW_NOTE_KEY_RESULT";
    public static final String ARG_NOTE = "ARG_NOTE";

    public AddNewNoteFragment() {
        super(R.layout.fragment_add_new_note);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        EditText title = view.findViewById(R.id.new_note_title);
        EditText text = view.findViewById(R.id.new_note_text);
        CheckBox checkBox = view.findViewById(R.id.checkbox_important);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_save) {

                    InMemoryNotesRepository.getInstance(requireContext()).addNote(title.getText().toString(), text.getText().toString(), checkBox, new Callback<Note>() {
                        @Override
                        public void onSuccess(Note data) {

                            Bundle bundle = new Bundle();
                            bundle.putParcelable(ARG_NOTE, data);

                            getParentFragmentManager().setFragmentResult(KEY_RESULT, bundle);
                        }

                        @Override
                        public void onError(Throwable exception) {

                        }
                    });

                    Snackbar.make(view, "Заметка сохранена", Snackbar.LENGTH_INDEFINITE).show();
                    return true;
                }
                return false;
            }
        });
    }
}
