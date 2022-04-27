package by.ealipatov.mynotesapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import by.ealipatov.mynotesapp.R;

public class AddNewNoteFragment extends Fragment {

    public AddNewNoteFragment() {
        super(R.layout.fragment_add_new_note);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_save) {
                    Snackbar.make(view, "Заметка сохранена", Snackbar.LENGTH_INDEFINITE).show();
                    return true;
                }
                return false;
            }
        });
    }
}
