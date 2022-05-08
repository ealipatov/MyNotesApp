package by.ealipatov.mynotesapp.domain;

public class Dependencies {

    private static final NotesRepository NOTES_REPOSITORY = new FireStoreNotesRepository();

    public static NotesRepository getNotesRepository() {
        return NOTES_REPOSITORY;
    }
}