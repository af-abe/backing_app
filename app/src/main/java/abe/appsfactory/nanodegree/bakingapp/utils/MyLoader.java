package abe.appsfactory.nanodegree.bakingapp.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

class MyLoader<D> extends AsyncTaskLoader<D> {
    private final AsyncOperation.OnError mError;
    private AsyncOperation.BackgroundTask<D> mTask;

    MyLoader(Context context, AsyncOperation.BackgroundTask<D> task, AsyncOperation.OnError error) {
        super(context);
        mTask = task;
        mError = error;
    }

    @Override
    public D loadInBackground() {
        try {
            return mTask.doInBackground();
        } catch (Exception e) {
            if (mError != null) {
                mError.onError(e);
            }
        }
        return null;
    }
}