package by.ealipatov.mynotesapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

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
                    Toast.makeText(requireContext(), "Сохраняем заметку", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

    }
}
