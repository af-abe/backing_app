package abe.appsfactory.nanodegree.bakingapp.network.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IIngredient;

public class APIIngredient implements IIngredient {

    @SerializedName("quantity")
    private float quantity;
    @SerializedName("measure")
    private String measure;
    @SerializedName("ingredient")
    private String ingredient;

    @SuppressWarnings("unused")
    public APIIngredient() {
    }

    private APIIngredient(Parcel in) {
        quantity = in.readFloat();
        measure = in.readString();
        ingredient = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<APIIngredient> CREATOR = new Creator<APIIngredient>() {
        @Override
        public APIIngredient createFromParcel(Parcel in) {
            return new APIIngredient(in);
        }

        @Override
        public APIIngredient[] newArray(int size) {
            return new APIIngredient[size];
        }
    };

    public float getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}