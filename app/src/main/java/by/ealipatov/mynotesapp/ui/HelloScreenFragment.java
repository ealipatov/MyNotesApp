package by.ealipatov.mynotesapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

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
}
