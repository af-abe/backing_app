package abe.appsfactory.nanodegree.bakingapp.ui.activites;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.List;

import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IIngredient;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;
import abe.appsfactory.nanodegree.bakingapp.ui.fragments.RecipeIngredientsDetailFragment;
import abe.appsfactory.nanodegree.bakingapp.ui.fragments.RecipeMasterFragment;
import abe.appsfactory.nanodegree.bakingapp.ui.fragments.RecipeStepDetailFragment;
import abe.appsfactory.nanodegree.bakingapp.utils.ExoPlayerInstanceHolder;

public class RecipeActivity extends AppCompatActivity {

    private static final String TAG_DETAIL = "tag.detail";
    private static final String TAG_MASTER = "tag.master";
    public static final String EXTRA_RECIPE = "extra.recipe";

    private boolean isTabletLandscape;
    private IRecipe mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        isTabletLandscape = getResources().getBoolean(R.bool.is_tablet);

        if (savedInstanceState == null) {
            setupMasterFragment();
            if (isTabletLandscape) {
                openIngDetailFragment(false);
            }
        } else {
            Fragment detailFragment = getSupportFragmentManager().findFragmentByTag(TAG_DETAIL);
            if (!isTabletLandscape && detailFragment != null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
            //fix back stacking on orientation changed
            if (isTabletLandscape) {
                Fragment master = getSupportFragmentManager().findFragmentByTag(TAG_MASTER);
                if (master != null) {
                    FragmentManager fm = getSupportFragmentManager();
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    FragmentTransaction tr = fm.beginTransaction();
                    tr.remove(master);
                    tr.commit();
                    openIngDetailFragment(false);

                    if (detailFragment != null) {
                        Bundle args = detailFragment.getArguments();
                        args.putBoolean(RecipeStepDetailFragment.ARGS_SKIP_EXO_RESET, true);
                        tr = fm.beginTransaction();
                        tr.replace(R.id.fragment_container, detailFragment, TAG_DETAIL);
                        tr.addToBackStack(null);
                        tr.commit();
                    }
                }
            } else {
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    setupMasterFragment();
                    openIngDetailFragment(true);
                }
            }
        }
    }

    private void setupMasterFragment() {
        if (!isTabletLandscape) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tr = fm.beginTransaction();
            tr.replace(R.id.fragment_container, RecipeMasterFragment.newInstance(getModel()), TAG_MASTER);
            tr.commit();
        }
    }

    public void openDetailFragment(IStep model, int parentId) {
        if (isTabletLandscape) {
            replaceDetailFragment(model, parentId);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tr = fm.beginTransaction();
            tr.replace(R.id.fragment_container,
                    RecipeStepDetailFragment.newInstance(model, parentId), TAG_DETAIL);
            tr.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            tr.addToBackStack(null);
            tr.commit();
        }
    }

    public void openIngDetailFragment() {
        if (isTabletLandscape) {
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            openIngDetailFragment(true);
        }
    }

    public void openIngDetailFragment(boolean backStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        tr.replace(R.id.fragment_container, new RecipeIngredientsDetailFragment());
        tr.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (backStack) {
            tr.addToBackStack(null);
        }
        tr.commit();
    }

    public void replaceDetailFragment(IStep model, int parentId) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        FragmentTransaction tr = fm.beginTransaction();
        tr.replace(R.id.fragment_container,
                RecipeStepDetailFragment.newInstance(model, parentId), TAG_DETAIL);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ExoPlayerInstanceHolder.getInstance().destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ExoPlayerInstanceHolder holder = ExoPlayerInstanceHolder.getInstance();
        if (holder.hasPlayer()) {
            holder.pausePlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExoPlayerInstanceHolder holder = ExoPlayerInstanceHolder.getInstance();
        if (holder.hasPlayer()) {
            holder.startPlayer();
        }
    }

    public IRecipe getModel() {
        if (mModel == null) {
            mModel = getIntent().getExtras().getParcelable(EXTRA_RECIPE);
        }
        return mModel;
    }
}
