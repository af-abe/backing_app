package abe.appsfactory.nanodegree.bakingapp.persistence.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IIngredient;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DbRecipe extends RealmObject implements IRecipe, Parcelable {

    @PrimaryKey
    private int id;
    private String name;
    private RealmList<DbIngredient> ingredients = null;
    private RealmList<DbStep> steps = null;
    private int servings;
    private String image;

    public DbRecipe(IRecipe model) {
        id = model.getId();
        name = model.getName();
        servings = model.getServings();
        image = model.getImage();
        ingredients = new RealmList<>();
        for(IIngredient ingredient : model.getIngredients()){
            ingredients.add(new DbIngredient(ingredient));
        }
        steps = new RealmList<>();
        for(IStep stap : model.getSteps()){
            steps.add(new DbStep(stap));
        }
    }

    public DbRecipe() {
    }

    private DbRecipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ArrayList<DbIngredient> ing = in.createTypedArrayList(DbIngredient.CREATOR);
        ArrayList<DbStep> st = in.createTypedArrayList(DbStep.CREATOR);

        ingredients = new RealmList<>();
        ingredients.addAll(ing);

        steps = new RealmList<>();
        steps.addAll(st)
        ;
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

    public static final Creator<DbRecipe> CREATOR = new Creator<DbRecipe>() {
        @Override
        public DbRecipe createFromParcel(Parcel in) {
            return new DbRecipe(in);
        }

        @Override
        public DbRecipe[] newArray(int size) {
            return new DbRecipe[size];
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