package abe.appsfactory.nanodegree.bakingapp.logic.models;

import android.os.Parcelable;

public interface IStep extends Parcelable {
    int getId();

    String getShortDescription();

    String getDescription();

    String getVideoURL();

    String getThumbnailURL();
}
