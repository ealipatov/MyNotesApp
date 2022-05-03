package by.ealipatov.mynotesapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

public class Note implements Parcelable {

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

    private final String name;
    private final String description;
    private final boolean important;
    private final Date date;

    public Note(String name, String description, Date date, boolean important) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.important = important;
    }

    protected Note(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = new Date(in.readLong());
        important = in.readByte() != 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public boolean getImportant() {
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
        parcel.writeLong(date.getTime());
        parcel.writeByte((byte) (important ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return important == note.important && Objects.equals(name, note.name) && Objects.equals(description, note.description) && Objects.equals(date, note.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, important, date);
    }
}
