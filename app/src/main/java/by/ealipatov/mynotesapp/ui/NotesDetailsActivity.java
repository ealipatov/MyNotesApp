package by.ealipatov.mynotesapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.Notes;

public class NotesDetailsActivity extends AppCompatActivity {

    private static final String DETAILS_NOTES ="DETAILS_NOTES";

    public static void show(Context context, Notes notes){
        Intent intent = new Intent(context, NotesDetailsActivity.class);
        intent.putExtra(DETAILS_NOTES, notes);

        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);

        if(savedInstanceState == null){

            Notes notes = getIntent().getParcelableExtra(DETAILS_NOTES);

            NoteDetailsFragment noteDetailsFragment = NoteDetailsFragment.newInstance(notes);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, noteDetailsFragment)
                    .commit();

        }

    }
}