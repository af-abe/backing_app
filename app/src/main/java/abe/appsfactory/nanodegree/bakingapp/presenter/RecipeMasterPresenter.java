package abe.appsfactory.nanodegree.bakingapp.presenter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;
import abe.appsfactory.nanodegree.bakingapp.presenter.items.RecipeIngredientsItemPresenter;
import abe.appsfactory.nanodegree.bakingapp.presenter.items.RecipeItem;
import abe.appsfactory.nanodegree.bakingapp.presenter.items.RecipeStepItemPresenter;

public class RecipeMasterPresenter extends BasePresenter {
    private final ObservableArrayList<RecipeItem> mItems = new ObservableArrayList<>();
    public final ObservableField<String> mTitle = new ObservableField<>("");

    public RecipeMasterPresenter(IRecipe model) {
        fillData(model);
    }

    private void fillData(IRecipe model) {
        mTitle.set(model.getName());
        mItems.clear();
        mItems.add(new RecipeIngredientsItemPresenter());
        for (IStep step : model.getSteps()) {
            mItems.add(new RecipeStepItemPresenter(step, model.getId()));
        }
    }

    public ObservableArrayList<RecipeItem> getItems() {
        return mItems;
    }
}
