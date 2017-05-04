package abe.appsfactory.nanodegree.bakingapp.presenter;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.support.v4.app.LoaderManager;
import android.text.TextUtils;
import android.view.View;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;
import abe.appsfactory.nanodegree.bakingapp.persistence.RealmHelper;
import abe.appsfactory.nanodegree.bakingapp.ui.activites.RecipeActivity;
import abe.appsfactory.nanodegree.bakingapp.utils.AsyncOperation;
import abe.appsfactory.nanodegree.bakingapp.utils.ExoPlayerInstanceHolder;

public class RecipeStepDetailPresenter extends BasePresenter {
    private final IStep mModel;
    private IStep mNextModel = null;
    private IStep mPrevModel = null;
    private final int mParentId;

    public ObservableBoolean showPrev = new ObservableBoolean(false);
    public ObservableBoolean showNext = new ObservableBoolean(false);

    public RecipeStepDetailPresenter(IStep model, int parentId) {
        mModel = model;
        mParentId = parentId;
    }

    @Bindable
    public String getDescription() {
        return mModel.getDescription();
    }

    @Bindable
    public String getTitle() {
        return mModel.getShortDescription();
    }

    @Bindable
    public String getVideoUrl() {
        return mModel.getVideoURL();
    }

    public void onClickNext(Context context) {
        if(mNextModel != null && context instanceof RecipeActivity){
            ((RecipeActivity) context).replaceDetailFragment(mNextModel, mParentId);
        }
    }

    public void onClickPrev(Context context) {
        if(mPrevModel != null && context instanceof RecipeActivity){
            ((RecipeActivity) context).replaceDetailFragment(mPrevModel, mParentId);
        }
    }

    @BindingAdapter("player")
    public static void setPlayer(SimpleExoPlayerView view, String url) {
        if (TextUtils.isEmpty(url)) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
            ExoPlayerInstanceHolder holder = ExoPlayerInstanceHolder.getInstance();
            if (!holder.hasPlayer()) {
                holder.init(view.getContext(), url);
            }
            view.setPlayer(holder.getPlayer());
        }
    }


    public void loadSteps(Context context, LoaderManager loaderManager) {
        new AsyncOperation<>(context, loaderManager, 0, () -> RealmHelper.getRecipe(mParentId))
                .setOnSuccess(data -> {
                    int prevId = mModel.getId() - 1;
                    int nextId = mModel.getId() + 1;

                    for (IStep step : data.getSteps()) {
                        if (step.getId() == prevId) {
                            mPrevModel = step;
                            showPrev.set(true);
                        } else if (step.getId() == nextId) {
                            mNextModel = step;
                            showNext.set(true);
                        }
                    }
                }).setOnError(Throwable::printStackTrace).execute();
    }
}
