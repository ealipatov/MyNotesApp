package by.ealipatov.mynotesapp.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryNotesRepository implements NotesRepository{

    private static NotesRepository INSTANCE;

    private final Context context;

    private InMemoryNotesRepository(Context context) {
        this.context = context;
    }

    public static NotesRepository getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = new InMemoryNotesRepository(context);
        }

        return INSTANCE;
    }

    @Override
    public List<Note> getAll() {
        ArrayList<Note> result = new ArrayList<>();

        result.add(new Note("Test", "тестовая запись", new Date(), false));
        result.add(new Note("Test2", "тестовая запись 2", new Date(), true));
        result.add(new Note("Test3", "тестовая запись 3", new Date(), false));
        result.add(new Note("Test4", "тестовая запись 4", new Date(), true));

        return result;
    }

    @Override
    public void add(Note notes) {

    }
}
