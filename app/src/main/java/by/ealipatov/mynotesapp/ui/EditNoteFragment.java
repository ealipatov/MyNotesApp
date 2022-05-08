package by.ealipatov.mynotesapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.Callback;
import by.ealipatov.mynotesapp.domain.Dependencies;
import by.ealipatov.mynotesapp.domain.Note;

public class EditNoteFragment extends BottomSheetDialogFragment {

    public static final String EDIT_KEY_RESULT = "EDIT_NOTE_KEY_RESULT";
    public static final String ARG_NOTE = "ARG_NOTE";

    public static EditNoteFragment editInstance(Note note) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);

        EditNoteFragment fragment = new EditNoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Note noteToEdit = null;

        //Проверка что есть что редактировать.
        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {
            noteToEdit = getArguments().getParcelable(ARG_NOTE);
        }

        EditText title = view.findViewById(R.id.title_edit);
        EditText text = view.findViewById(R.id.text_edit);
        CheckBox checkBox = view.findViewById(R.id.checkbox_important_edit);

        if (noteToEdit != null) {
            //Предзаполняем поля редактируемой заметки
            title.setText(noteToEdit.getTitle());
            text.setText(noteToEdit.getText());
            checkBox.setChecked(noteToEdit.getImportant());

        }

        Button btnSave = view.findViewById(R.id.save);

        Note finalNoteToEdit = noteToEdit;
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSave.setEnabled(false);

                Dependencies.getNotesRepository().editNote(finalNoteToEdit, title.getText().toString(), text.getText().toString(), checkBox, new Callback<Note>() {
                    @Override
                    public void onSuccess(Note data) {

                        Bundle bundle = new Bundle();
                        bundle.putParcelable(ARG_NOTE, data);

                        getParentFragmentManager().setFragmentResult(EDIT_KEY_RESULT, bundle);

                        btnSave.setEnabled(true);

                        dismiss();

                    }

                    @Override
                    public void onError(Throwable exception) {
                        btnSave.setEnabled(true);
                    }
                });

            }
        });
    }

}
