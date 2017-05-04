package abe.appsfactory.nanodegree.bakingapp.utils;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

public class OnObservableListChangedCallback extends ObservableList.OnListChangedCallback {
    private final RecyclerView.Adapter<?> mAdapter;

    public OnObservableListChangedCallback(RecyclerView.Adapter<?> adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onChanged(ObservableList observableList) {

    }

    @Override
    public void onItemRangeChanged(ObservableList tItems, final int i, final int i1) {
        mAdapter.notifyItemRangeChanged(i, i1);
    }

    @Override
    public void onItemRangeInserted(ObservableList tItems, final int i, final int i1) {
        mAdapter.notifyItemRangeInserted(i, i1);
    }

    @Override
    public void onItemRangeMoved(ObservableList tItems, final int i, final int i1, int i2) {
        mAdapter.notifyItemMoved(i, i1);
    }

    @Override
    public void onItemRangeRemoved(ObservableList tItems, final int i, final int i1) {
        mAdapter.notifyItemRangeRemoved(i, i1);
    }
}
