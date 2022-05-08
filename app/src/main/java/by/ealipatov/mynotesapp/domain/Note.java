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


    private final String id;
    private final String title;
    private final String text;
    private final boolean important;
    private final Date date;

    public Note(String id, String title, String text, Date date, boolean important) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.important = important;
    }

    protected Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        text = in.readString();
        date = new Date(in.readLong());
        important = in.readByte() != 0;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
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
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(text);
        parcel.writeLong(date.getTime());
        parcel.writeByte((byte) (important ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return important == note.important && Objects.equals(id, note.id) && Objects.equals(title, note.title) && Objects.equals(text, note.text) && Objects.equals(date, note.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, important, date);
    }
}
