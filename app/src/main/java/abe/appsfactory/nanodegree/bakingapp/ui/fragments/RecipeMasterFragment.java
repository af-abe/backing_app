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

import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.databinding.FragmentRecipeMasterBinding;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.presenter.RecipeMasterPresenter;
import abe.appsfactory.nanodegree.bakingapp.ui.activites.RecipeActivity;
import abe.appsfactory.nanodegree.bakingapp.ui.adapter.RecipeRecyclerAdapter;

public class RecipeMasterFragment extends Fragment {

    private static final String ARGS_MODEL = "args.model";
    private RecipeMasterPresenter mPresenter;


    public static RecipeMasterFragment newInstance(IRecipe model) {

        Bundle args = new Bundle();

        args.putParcelable(ARGS_MODEL, model);
        RecipeMasterFragment fragment = new RecipeMasterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity ac = getActivity();
        IRecipe model;
        if(ac instanceof RecipeActivity){
            model = ((RecipeActivity) ac).getModel();
            mPresenter = new RecipeMasterPresenter(model);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_master, container, false);
        FragmentRecipeMasterBinding binding = DataBindingUtil.bind(view);
        binding.setPresenter(mPresenter);
        setupRecyclerView(binding.recyclerView);
        return view;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new RecipeRecyclerAdapter(mPresenter.getItems()));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }
}
