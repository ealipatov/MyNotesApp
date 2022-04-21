package by.ealipatov.mynotesapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {

    private final String name;
    private final String description;
    private Date date;
    private final boolean important;

    public Note(String name, String description, Date date, boolean important) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.important = important;
    }

    protected Note(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = new Date();              // in.readString();
        important = in.readByte() != 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
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
        parcel.writeString(date.toString());
        parcel.writeByte((byte) (important ? 1 : 0));
    }
}
