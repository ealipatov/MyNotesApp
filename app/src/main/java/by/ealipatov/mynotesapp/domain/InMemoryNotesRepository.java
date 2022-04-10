package by.ealipatov.mynotesapp.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotesRepository implements NotesRepository{

    private static NotesRepository INSTANCE;

    private Context context;

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
    public List<Notes> getAll() {
        ArrayList<Notes> result = new ArrayList<>();

        result.add(new Notes("Test", "тестовая запись", "10.04.2022", false));

        return result;
    }

    @Override
    public void add(Notes notes) {

    }
}
