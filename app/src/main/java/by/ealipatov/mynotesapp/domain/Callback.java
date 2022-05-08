package by.ealipatov.mynotesapp.domain;

public interface Callback<T> {

    void onSuccess(T data);

    void onError(Throwable exception);
}
