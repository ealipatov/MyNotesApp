package by.ealipatov.mynotesapp.domain;

import android.content.Context;

import android.os.Looper;
import android.os.Handler;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InMemoryNotesRepository implements NotesRepository {

    private static NotesRepository INSTANCE;
    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());
    private ArrayList<Note> data = new ArrayList<>();

    public InMemoryNotesRepository(Context context) {
    }

    public static NotesRepository getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new InMemoryNotesRepository(context);
        }
        return INSTANCE;
    }

    public InMemoryNotesRepository() {

    }


    @Override
    public void getAll(Callback<List<Note>> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(data);
                    }
                });
            }
        });

    }

    @Override
    public void addNote(String title, String text, CheckBox checkBox, Callback<Note> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                boolean important = false;
                if(checkBox.isChecked()){
                    important = true;
                }

                Note note = new Note(title, text, new Date(), important);
                data.add(note);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(note);
                    }
                });
            }
        });
    }
}
