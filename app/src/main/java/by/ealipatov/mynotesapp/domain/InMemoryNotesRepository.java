package by.ealipatov.mynotesapp.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryNotesRepository implements NotesRepository {

    private static NotesRepository INSTANCE;

    private InMemoryNotesRepository(Context context) {
    }

    public static NotesRepository getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new InMemoryNotesRepository(context);
        }
        return INSTANCE;
    }

    @Override
    public List<Note> getAll() {
        ArrayList<Note> result = new ArrayList<>();

        result.add(new Note("Test1", "тестовая запись 1", new Date(), false));
        result.add(new Note("Test2", "тестовая запись 2", new Date(), true));
        result.add(new Note("Test3", "тестовая запись 3", new Date(), false));
        result.add(new Note("Test4", "тестовая запись 4", new Date(), true));
        result.add(new Note("Test5", "тестовая запись 5", new Date(), false));
        result.add(new Note("Test6", "тестовая запись 6", new Date(), false));
        result.add(new Note("Test7", "тестовая запись 7", new Date(), false));
        result.add(new Note("Test8", "тестовая запись 8", new Date(), false));
        result.add(new Note("Test9", "тестовая запись 9", new Date(), false));
        result.add(new Note("Test10", "тестовая запись 10", new Date(), false));
        result.add(new Note("Test11", "тестовая запись 10", new Date(), false));
        result.add(new Note("Test12", "тестовая запись 10", new Date(), false));
        result.add(new Note("Test13", "тестовая запись 10", new Date(), false));
        result.add(new Note("Test14", "тестовая запись 10", new Date(), false));
        result.add(new Note("Test15", "тестовая запись 10", new Date(), false));

        return result;
    }

    @Override
    public void add(Note notes) {

    }
}
