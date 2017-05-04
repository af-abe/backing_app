package abe.appsfactory.nanodegree.bakingapp.ui.activites;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.databinding.ActivityMainBinding;
import abe.appsfactory.nanodegree.bakingapp.presenter.MainPresenter;
import abe.appsfactory.nanodegree.bakingapp.ui.adapter.GenericGridRecyclerAdapter;

public class MainActivity extends AppCompatActivity {
    private MainPresenter mPresenter;
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new MainPresenter();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setPresenter(mPresenter);


        setupGridView(mBinding.recipeGrid);

        if (savedInstanceState == null) {
            mPresenter.loadReciepes(this, getSupportLoaderManager());
        } else {
            mPresenter.restoreState(savedInstanceState);
        }
    }

    private void setupGridView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new GenericGridRecyclerAdapter<>(mPresenter.getItems(), R.layout.item_recipe_card));
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.grid_colums)));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.saveState(outState);
    }
}
