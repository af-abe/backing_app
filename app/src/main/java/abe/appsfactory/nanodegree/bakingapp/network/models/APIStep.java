package abe.appsfactory.nanodegree.bakingapp.network.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;

public class APIStep implements IStep {

    @SerializedName("id")
    private int id;
    @SerializedName("shortDescription")
    private String shortDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("videoURL")
    private String videoURL;
    @SerializedName("thumbnailURL")
    private String thumbnailURL;

    @SuppressWarnings("unused")
    public APIStep() {
    }

    private APIStep(Parcel in) {
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

    public static final Creator<APIStep> CREATOR = new Creator<APIStep>() {
        @Override
        public APIStep createFromParcel(Parcel in) {
            return new APIStep(in);
        }

        @Override
        public APIStep[] newArray(int size) {
            return new APIStep[size];
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