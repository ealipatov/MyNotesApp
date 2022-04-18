package by.ealipatov.mynotesapp.ui;

import static by.ealipatov.mynotesapp.ui.NotesListFragment.HELLO_SCREEN;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

import by.ealipatov.mynotesapp.R;

public class HelloScreenFragment extends Fragment {


    public HelloScreenFragment (){
       super(R.layout.fragment_hello_screen);
   }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello_screen, container, false);
       return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.hello_screen_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new NotesListFragment())
                        .addToBackStack("start")
                        .commit();

            }
        });
    }
}
