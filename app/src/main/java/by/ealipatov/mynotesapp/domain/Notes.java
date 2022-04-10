package by.ealipatov.mynotesapp.domain;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Notes implements Parcelable{

    private final String name;
    private final String description;
    private final String date;
    private boolean important = false;

    public Notes(String name, String description, String date, boolean important) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.important = important;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public boolean isImportant() {
        return important;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
