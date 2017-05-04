package abe.appsfactory.nanodegree.bakingapp.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.databinding.FragmentIngredientsDetailBinding;
import abe.appsfactory.nanodegree.bakingapp.databinding.FragmentRecipeStepDetailBinding;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IIngredient;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IStep;
import abe.appsfactory.nanodegree.bakingapp.presenter.RecipeIngredientsDetailPresenter;
import abe.appsfactory.nanodegree.bakingapp.presenter.RecipeMasterPresenter;
import abe.appsfactory.nanodegree.bakingapp.presenter.RecipeStepDetailPresenter;
import abe.appsfactory.nanodegree.bakingapp.ui.activites.RecipeActivity;
import abe.appsfactory.nanodegree.bakingapp.ui.adapter.GenericGridRecyclerAdapter;
import abe.appsfactory.nanodegree.bakingapp.ui.adapter.RecipeRecyclerAdapter;
import abe.appsfactory.nanodegree.bakingapp.utils.ExoPlayerInstanceHolder;

public class RecipeIngredientsDetailFragment extends Fragment {

    private RecipeIngredientsDetailPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity ac = getActivity();
        IRecipe model;
        if(ac instanceof RecipeActivity){
            model = ((RecipeActivity) ac).getModel();
            mPresenter =new RecipeIngredientsDetailPresenter(model.getIngredients());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients_detail, container, false);
        FragmentIngredientsDetailBinding binding = DataBindingUtil.bind(view);
        binding.setPresenter(mPresenter);
        setupRecyclerView(binding.recyclerView);

        return view;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        if (mPresenter != null) {
            recyclerView.setAdapter(new GenericGridRecyclerAdapter<>(mPresenter.getItems(), R.layout.item_list_ingredients));
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
    }
}
