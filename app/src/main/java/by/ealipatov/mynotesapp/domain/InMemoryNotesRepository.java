package by.ealipatov.mynotesapp.domain;

import android.content.Context;

import android.os.Looper;
import android.os.Handler;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InMemoryNotesRepository implements NotesRepository {

    private static NotesRepository INSTANCE;
    boolean important = false;
    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());
    private ArrayList<Note> data = new ArrayList<>();

    public InMemoryNotesRepository(Context context) {
    }

    public InMemoryNotesRepository() {

    }

    public static NotesRepository getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new InMemoryNotesRepository(context);
        }
        return INSTANCE;
    }

    @Override
    public void getAll(Callback<List<Note>> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {

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

                important = checkBox.isChecked();


                Note note = new Note(UUID.randomUUID().toString(), title, text, new Date(), important);
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

    @Override
    public void deleteNote(Note note, Callback<Void> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                data.remove(note);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(null);
                    }
                });
            }
        });
    }

    @Override
    public void editNote(Note note, String title, String text, CheckBox checkBox, Callback<Note> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                important = checkBox.isChecked();

                Note newNote = new Note(note.getId(), title, text, note.getDate(), important);

                int index = data.indexOf(note);

                data.set(index, newNote);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(newNote);
                    }
                });
            }
        });
    }

    @Override
    public void searchNote(String search, Callback<List<Note>> callback) {
        //TODO: реализовать поиск
    }

}
