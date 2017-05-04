package abe.appsfactory.nanodegree.bakingapp.presenter;

import android.databinding.ObservableArrayList;

import java.util.List;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IIngredient;
import abe.appsfactory.nanodegree.bakingapp.presenter.items.RecipeIngredientsListItemPresenter;

public class RecipeIngredientsDetailPresenter extends BasePresenter {
    private ObservableArrayList<RecipeIngredientsListItemPresenter> mItems = new ObservableArrayList<>();

    public RecipeIngredientsDetailPresenter(List<? extends IIngredient> array) {
        for(IIngredient ingredient : array){
            mItems.add(new RecipeIngredientsListItemPresenter(ingredient));
        }
    }

    public ObservableArrayList<RecipeIngredientsListItemPresenter> getItems() {
        return mItems;
    }
}
