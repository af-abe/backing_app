package abe.appsfactory.nanodegree.bakingapp.logic.models;

import android.os.Parcelable;

import java.util.List;

public interface IRecipe  extends Parcelable {

    int getId();

    String getName();

    List<? extends IIngredient> getIngredients();


    List<? extends IStep> getSteps();

    int getServings();


    String getImage();

}
