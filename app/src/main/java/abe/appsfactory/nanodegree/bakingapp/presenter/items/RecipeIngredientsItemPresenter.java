package abe.appsfactory.nanodegree.bakingapp.presenter.items;


import android.content.Context;

import abe.appsfactory.nanodegree.bakingapp.BR;
import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.ui.activites.RecipeActivity;

public class RecipeIngredientsItemPresenter extends RecipeItem {

    @Override
    public int getLayoutRes() {
        return R.layout.item_ingredients;
    }

    @Override
    public int getBindingRes() {
        return BR.item;
    }

    public void onClickCard(Context context) {
        if (context instanceof RecipeActivity) {
            ((RecipeActivity) context).openIngDetailFragment();
        }
    }
}
