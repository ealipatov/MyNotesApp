package by.ealipatov.mynotesapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {

    private final String name;
    private final String description;
    private final String date;
    private final boolean important;

    public Notes(String name, String description, String date, boolean important) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.important = important;
    }

    protected Notes(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = in.readString();
        important = in.readByte() != 0;
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

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
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(date);
        parcel.writeByte((byte) (important ? 1 : 0));
    }
}
