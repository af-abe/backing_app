package abe.appsfactory.nanodegree.bakingapp.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;


/**
 * wraps a loader
 *
 * @param <T> result Object
 */

public class AsyncOperation<T> {
    private final Loader<T> mTask;
    private OnSuccess<T> mSuccess;
    private OnError mError;

    public AsyncOperation(Context context, LoaderManager loaderManager, int loaderId, BackgroundTask<T> task) {
        mTask = loaderManager.restartLoader(loaderId, null, new LoaderManager.LoaderCallbacks<T>() {
            @Override
            public Loader<T> onCreateLoader(int id, Bundle args) {
                return new MyLoader<>(context, task, throwable -> {
                    if (mError != null) {
                        new Handler(Looper.getMainLooper()).post(() -> mError.onError(throwable));
                    }
                });
            }

            @Override
            public void onLoadFinished(Loader<T> loader, T data) {
                if (mSuccess != null && data != null) {
                    new Handler(Looper.getMainLooper()).post(() -> mSuccess.onSuccess(data));
                }
            }

            @Override
            public void onLoaderReset(Loader<T> loader) {

            }
        });
    }

    public AsyncOperation setOnSuccess(OnSuccess<T> success) {
        mSuccess = success;
        return this;
    }

    public AsyncOperation setOnError(OnError error) {
        mError = error;
        return this;
    }

    public void execute() {
        if (mTask != null) {
            mTask.forceLoad();
        }
    }

    @FunctionalInterface
    public interface BackgroundTask<T> {
        T doInBackground() throws Exception;
    }

    @FunctionalInterface
    public interface OnSuccess<T> {
        void onSuccess(T data);
    }

    @FunctionalInterface
    public interface OnError {
        void onError(Throwable throwable);
    }
}
