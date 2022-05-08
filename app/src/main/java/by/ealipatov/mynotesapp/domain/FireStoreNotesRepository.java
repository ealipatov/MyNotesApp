package by.ealipatov.mynotesapp.domain;

import android.widget.CheckBox;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FireStoreNotesRepository implements NotesRepository {

    private static final String KEY_TITLE = "title";
    private static final String KEY_TEXT = "text";
    private static final String KEY_DATE = "date";
    private static final String KEY_IMPORTANT = "important";

    private static final String NOTES = "notes";

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    public void getAll(Callback<List<Note>> callback) {

        firestore.collection(NOTES)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<Note> result = new ArrayList<>();

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {

                            String id = documentSnapshot.getId();

                            String title = documentSnapshot.getString(KEY_TITLE);
                            String text = documentSnapshot.getString(KEY_TEXT);
                            Date date = documentSnapshot.getDate(KEY_DATE);
                            boolean important = documentSnapshot.getBoolean(KEY_IMPORTANT);

                            result.add(new Note(id, title, text, date, important));
                        }

                        callback.onSuccess(result);

                    }
                });

    }

    @Override
    public void addNote(String title, String text, CheckBox checkBox, Callback<Note> callback) {

        HashMap<String, Object> data = new HashMap<>();

        Date date = new Date();

        boolean important = checkBox.isChecked();

        data.put(KEY_TITLE, title);
        data.put(KEY_TEXT, text);
        data.put(KEY_DATE, date);
        data.put(KEY_IMPORTANT, important);

        firestore.collection(NOTES)
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        callback.onSuccess(new Note(documentReference.getId(), title, text, date, important));
                    }
                });

    }

    @Override
    public void deleteNote(Note note, Callback<Void> callback) {

        firestore.collection(NOTES)
                .document(note.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(unused);
                    }
                });

    }

    @Override
    public void editNote(Note note, String title, String text, CheckBox checkBox, Callback<Note> callback) {

        HashMap<String, Object> data = new HashMap<>();

        boolean important = checkBox.isChecked();

        data.put(KEY_TITLE, title);
        data.put(KEY_TEXT, text);
        data.put(KEY_IMPORTANT, important);

        firestore.collection(NOTES)
                .document(note.getId())
                .update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Note result = new Note(note.getId(), title, text, note.getDate(), important);

                        callback.onSuccess(result);
                    }
                });

    }
}
