package abe.appsfactory.nanodegree.bakingapp.presenter.items;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.ui.activites.RecipeActivity;
import abe.appsfactory.nanodegree.bakingapp.ui.widget.WidgetProvider;

public class RecipeCardItemPresenter extends BaseObservable implements Parcelable {
    private final IRecipe mModel;

    public RecipeCardItemPresenter(IRecipe recipe) {
        mModel = recipe;
    }

    private RecipeCardItemPresenter(Parcel in) {
        mModel = in.readParcelable(IRecipe.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mModel, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecipeCardItemPresenter> CREATOR = new Creator<RecipeCardItemPresenter>() {
        @Override
        public RecipeCardItemPresenter createFromParcel(Parcel in) {
            return new RecipeCardItemPresenter(in);
        }

        @Override
        public RecipeCardItemPresenter[] newArray(int size) {
            return new RecipeCardItemPresenter[size];
        }
    };

    @Bindable
    public String getName() {
        return mModel.getName();
    }

    public void onClick(Context context){
        Intent i = new Intent(context, RecipeActivity.class);
        i.putExtra(RecipeActivity.EXTRA_RECIPE, mModel);
        context.startActivity(i);

        Intent widgetUpdate = new Intent(WidgetProvider.ACTION_UPDATE);
        widgetUpdate.putExtra(WidgetProvider.EXTRA_RECIPE_ID, mModel.getId());

        context.sendBroadcast(widgetUpdate);
    }
}
