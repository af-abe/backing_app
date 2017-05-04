package abe.appsfactory.nanodegree.bakingapp.ui.adapter;

import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abe.appsfactory.nanodegree.bakingapp.BR;
import abe.appsfactory.nanodegree.bakingapp.utils.OnObservableListChangedCallback;


public class GenericGridRecyclerAdapter<T extends BaseObservable> extends RecyclerView.Adapter<GenericGridRecyclerAdapter.ViewHolder>{
    private final ObservableArrayList<T> mItems;
    private final int mLayout;
    private OnObservableListChangedCallback mChangeObserver;

    public GenericGridRecyclerAdapter(ObservableArrayList<T> items, @LayoutRes int layout) {
        mItems = items;
        mLayout = layout;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (mItems != null) {
            mChangeObserver = new OnObservableListChangedCallback(this);
            mItems.addOnListChangedCallback(mChangeObserver);
        }
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mItems != null) {
            mItems.removeOnListChangedCallback(mChangeObserver);
        }
        mChangeObserver = null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T item = mItems.get(position);
        holder.binding.setVariable(BR.item, item);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        int itemId;
        ViewDataBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemId = BR.item;
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
