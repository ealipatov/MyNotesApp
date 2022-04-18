package by.ealipatov.mynotesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import by.ealipatov.mynotesapp.R;

public class AddNewNoteFragment extends Fragment {

    public AddNewNoteFragment (){
        super(R.layout.fragment_add_new_note);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Сохраняем заметку", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
