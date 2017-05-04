package abe.appsfactory.nanodegree.bakingapp.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.databinding.FragmentRecipeMasterBinding;
import abe.appsfactory.nanodegree.bakingapp.databinding.FragmentRecipeStepDetailBinding;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;
import abe.appsfactory.nanodegree.bakingapp.presenter.RecipeMasterPresenter;
import abe.appsfactory.nanodegree.bakingapp.presenter.RecipeStepDetailPresenter;
import abe.appsfactory.nanodegree.bakingapp.ui.adapter.RecipeRecyclerAdapter;
import abe.appsfactory.nanodegree.bakingapp.utils.ExoPlayerInstanceHolder;

public class RecipeStepDetailFragment extends Fragment {

    private static final String ARGS_STEP = "arg.step";
    private static final String ARGS_PARENT_ID = "arg.parentId";
    public static final String ARGS_SKIP_EXO_RESET = "arg.skipExoReset";

    public static RecipeStepDetailFragment newInstance(IStep step, int parentId) {
        Bundle args = new Bundle();
        args.putParcelable(ARGS_STEP, step);
        args.putInt(ARGS_PARENT_ID, parentId);
        RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecipeStepDetailPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        IStep model = null;
        int parentId = -1;
        boolean skipExoReset = false;
        if(args != null && args.containsKey(ARGS_STEP)) {
            model = args.getParcelable(ARGS_STEP);
            parentId = args.getInt(ARGS_PARENT_ID, -1);
            skipExoReset = args.getBoolean(ARGS_SKIP_EXO_RESET, false);
        }

        mPresenter = new RecipeStepDetailPresenter(model, parentId);

        if(savedInstanceState == null && !skipExoReset){
            ExoPlayerInstanceHolder.getInstance().destroy();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.loadSteps(getContext(), getLoaderManager());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);
        FragmentRecipeStepDetailBinding binding = DataBindingUtil.bind(view);
        binding.setPresenter(mPresenter);
        return view;
    }
}
