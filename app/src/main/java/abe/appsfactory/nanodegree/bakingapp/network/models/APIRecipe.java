package abe.appsfactory.nanodegree.bakingapp.network.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IIngredient;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;

public class APIRecipe implements IRecipe {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("ingredients")
    private List<APIIngredient> ingredients = null;
    @SerializedName("steps")
    private List<APIStep> steps = null;
    @SerializedName("servings")
    private int servings;
    @SerializedName("image")
    private String image;

    @SuppressWarnings("unused")
    public APIRecipe() {
    }

    private APIRecipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.createTypedArrayList(APIIngredient.CREATOR);
        steps = in.createTypedArrayList(APIStep.CREATOR);
        servings = in.readInt();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
        dest.writeInt(servings);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<APIRecipe> CREATOR = new Creator<APIRecipe>() {
        @Override
        public APIRecipe createFromParcel(Parcel in) {
            return new APIRecipe(in);
        }

        @Override
        public APIRecipe[] newArray(int size) {
            return new APIRecipe[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<? extends IIngredient> getIngredients() {
        return ingredients;
    }

    public List<? extends IStep> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}