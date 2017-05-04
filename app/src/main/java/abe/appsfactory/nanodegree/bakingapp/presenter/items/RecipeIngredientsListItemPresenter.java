package abe.appsfactory.nanodegree.bakingapp.presenter.items;


import android.databinding.Bindable;

import abe.appsfactory.nanodegree.bakingapp.BR;
import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IIngredient;

public class RecipeIngredientsListItemPresenter extends RecipeItem {
    private final IIngredient mModel;

    public RecipeIngredientsListItemPresenter(IIngredient model) {
        mModel = model;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_ingredients;
    }

    @Override
    public int getBindingRes() {
        return BR.item;
    }

    @Bindable
    public String getName() {
        return mModel.getIngredient();
    }

    @Bindable
    public String getMeasure() {
        return mModel.getMeasure();
    }

    @Bindable
    public float getQuantity() {
        return mModel.getQuantity();
    }
}
