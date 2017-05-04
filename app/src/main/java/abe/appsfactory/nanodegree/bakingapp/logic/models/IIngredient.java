package abe.appsfactory.nanodegree.bakingapp.logic.models;

import android.os.Parcelable;

public interface IIngredient extends Parcelable{
    float getQuantity();


    String getMeasure();


    String getIngredient();
}
