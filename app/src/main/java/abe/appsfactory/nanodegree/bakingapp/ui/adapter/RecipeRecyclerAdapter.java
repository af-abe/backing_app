package abe.appsfactory.nanodegree.bakingapp.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abe.appsfactory.nanodegree.bakingapp.presenter.items.RecipeItem;
import abe.appsfactory.nanodegree.bakingapp.utils.OnObservableListChangedCallback;


public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.ViewHolder> {
    private final ObservableArrayList<RecipeItem> mItems;
    private OnObservableListChangedCallback mChangeObserver;

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getLayoutRes();
    }

    public RecipeRecyclerAdapter(ObservableArrayList<RecipeItem> items) {
        mItems = items;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecipeItem item = mItems.get(position);
        holder.binding.setVariable(item.getBindingRes(), item);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
