package abe.appsfactory.nanodegree.bakingapp.presenter.items;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.android.databinding.library.baseAdapters.BR;

import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;
import abe.appsfactory.nanodegree.bakingapp.ui.activites.RecipeActivity;
import abe.appsfactory.nanodegree.bakingapp.utils.ThumbNailHelper;

public class RecipeStepItemPresenter extends RecipeItem {
    private final IStep mModel;
    private final int mParentId;

    public RecipeStepItemPresenter(IStep model, int parentId) {
        mModel = model;
        mParentId = parentId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_step;
    }

    @Override
    public int getBindingRes() {
        return BR.item;
    }

    public void onClick(Context context) {
        if (context instanceof RecipeActivity) {
            ((RecipeActivity) context).openDetailFragment(mModel, mParentId);
        }
    }

    @Bindable
    public String getShortDescription() {
        return mModel.getShortDescription();
    }

    @Bindable
    public String getThumbnail() {
        return mModel.getVideoURL();
    }

    @Bindable
    public String getStep() {
        return "Step " + (mModel.getId() + 1);
    }

    @BindingAdapter("thumbnail")
    public static void getThumbnailImage(final ImageView view, String url) {
        ThumbNailHelper.loadThumbnailFromUrlOrCache(view, url);
    }
}
