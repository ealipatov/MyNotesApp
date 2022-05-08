package by.ealipatov.mynotesapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.Callback;
import by.ealipatov.mynotesapp.domain.Dependencies;
import by.ealipatov.mynotesapp.domain.Note;

public class SearchNoteFragment extends BottomSheetDialogFragment {

    public static final String SEARCH_KEY_RESULT = "SEARCH_NOTE_KEY_RESULT";
    public static final String ARG_NOTE = "ARG_NOTE";

    private FirebaseFirestore dataBaseNotes = FirebaseFirestore.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnSearch = view.findViewById(R.id.search_btn);
        EditText textSearch = view.findViewById(R.id.title_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSearch.setEnabled(false);

                Dependencies.getNotesRepository().searchNote(textSearch.getText().toString(), new Callback<List<Note>>() {
                    @Override
                    public void onSuccess(List<Note> data) {

                        btnSearch.setEnabled(true);

                        Toast.makeText(requireContext(), R.string.note_is_searched, Toast.LENGTH_SHORT).show();

                  //      Toast.makeText(requireContext(), R.string.note_is_not_searched, Toast.LENGTH_SHORT).show();
                        //TODO: Реализовать поиск по заголовку  и вывод заметок с базы данных.
                        dismiss();

                    }

                    @Override
                    public void onError(Throwable exception) {
                        btnSearch.setEnabled(true);
                    }
                });

            }
        });
    }

}

