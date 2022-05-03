package by.ealipatov.mynotesapp.domain;


import android.widget.CheckBox;

import java.util.List;

public interface NotesRepository {

    void getAll(Callback<List<Note>> callback);

    void addNote(String title, String text, CheckBox checkBox, Callback<Note> callback);

    void deleteNote(Note note, Callback<Void> callback);

    void editNote(Note note, String title, String text, CheckBox checkBox, Callback<Note> callback);

}
