package abe.appsfactory.nanodegree.bakingapp.presenter.items;

import android.databinding.BaseObservable;
import android.support.annotation.LayoutRes;

public abstract class RecipeItem extends BaseObservable {
    @LayoutRes
    public abstract int getLayoutRes();

    public abstract int getBindingRes();
}
