package abe.appsfactory.nanodegree.bakingapp.persistence.models;

import android.os.Parcel;
import android.os.Parcelable;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DbStep extends RealmObject implements IStep, Parcelable {

    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public DbStep() {
    }

    public DbStep(IStep model) {
        id = model.getId();
        shortDescription = model.getShortDescription();
        description = model.getDescription();
        videoURL = model.getVideoURL();
        thumbnailURL = model.getThumbnailURL();
    }

    private DbStep(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DbStep> CREATOR = new Creator<DbStep>() {
        @Override
        public DbStep createFromParcel(Parcel in) {
            return new DbStep(in);
        }

        @Override
        public DbStep[] newArray(int size) {
            return new DbStep[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}