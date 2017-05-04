package abe.appsfactory.nanodegree.bakingapp.presenter;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.view.View;

import java.util.ArrayList;

import abe.appsfactory.nanodegree.bakingapp.logic.DataLogic;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.presenter.items.RecipeCardItemPresenter;
import abe.appsfactory.nanodegree.bakingapp.utils.AsyncOperation;

public class MainPresenter extends BasePresenter {
    private static final int LOADER_ID = 0;
    private final ObservableArrayList<RecipeCardItemPresenter> mItems = new ObservableArrayList<>();
    public final ObservableInt mLoadingVisibility = new ObservableInt(View.GONE);

    public void loadReciepes(final Context context, LoaderManager supportLoaderManager) {
        mLoadingVisibility.set(View.VISIBLE);

        new AsyncOperation<>(context, supportLoaderManager, LOADER_ID, DataLogic::getRecipes)
                .setOnSuccess(data -> {
                    mLoadingVisibility.set(View.GONE);
                    mItems.clear();
                    for (IRecipe recipe : data) {
                        mItems.add(new RecipeCardItemPresenter(recipe));
                    }
                })
                .setOnError(throwable -> {
                    throwable.printStackTrace();
                })
                .execute();
    }

    public ObservableArrayList<RecipeCardItemPresenter> getItems() {
        return mItems;
    }

    @Override
    public void saveState(Bundle out) {
        out.putParcelableArrayList(RecipeCardItemPresenter.class.getCanonicalName(), mItems);
    }

    @Override
    public void restoreState(Bundle state) {
        ArrayList<RecipeCardItemPresenter> arrayList = state.getParcelableArrayList(RecipeCardItemPresenter.class.getCanonicalName());
        if (arrayList != null) {
            mItems.addAll(arrayList);
        }
    }
}
