package abe.appsfactory.nanodegree.bakingapp.persistence.models;

import android.os.Parcel;
import android.os.Parcelable;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IIngredient;
import io.realm.RealmObject;

public class DbIngredient extends RealmObject implements IIngredient, Parcelable {

    private float quantity;
    private String measure;
    private String ingredient;

    public DbIngredient() {
    }

    public DbIngredient(IIngredient model) {
        quantity = model.getQuantity();
        measure = model.getMeasure();
        ingredient = model.getIngredient();
    }

    private DbIngredient(Parcel in) {
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

    public static final Creator<DbIngredient> CREATOR = new Creator<DbIngredient>() {
        @Override
        public DbIngredient createFromParcel(Parcel in) {
            return new DbIngredient(in);
        }

        @Override
        public DbIngredient[] newArray(int size) {
            return new DbIngredient[size];
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