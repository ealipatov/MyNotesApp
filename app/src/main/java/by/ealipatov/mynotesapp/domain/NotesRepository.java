package by.ealipatov.mynotesapp.domain;

import java.util.List;

public interface NotesRepository {

    void getAll(Callback<List<Note>> callback);

}
