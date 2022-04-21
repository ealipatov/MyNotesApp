package by.ealipatov.mynotesapp.domain;

import java.util.List;

public interface NotesRepository {

    List<Note> getAll();

    void add(Note notes);
}
