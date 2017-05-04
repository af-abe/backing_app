package abe.appsfactory.nanodegree.bakingapp.ui.widget;

import android.content.Context;
import android.os.Binder;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IIngredient;
import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.persistence.RealmHelper;

class WidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context mContext;
    private IRecipe mItem;

    public WidgetViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();
        int id = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE)
                .getInt(WidgetProvider.EXTRA_RECIPE_ID, 0);
        mItem = RealmHelper.getRecipe(id);
        Log.d("WidgetProvider", "onDataSetChanged: " + mItem);

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {
        mItem = null;
    }

    @Override
    public int getCount() {
        Log.d("WidgetProvider", "getCount: " + (mItem == null ? 0 : mItem.getIngredients().size()));

        return mItem == null ? 0 : mItem.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == AdapterView.INVALID_POSITION || mItem == null) {
            return null;
        }
        IIngredient item = mItem.getIngredients().get(position);
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        views.setTextViewText(R.id.quant, "" + item.getQuantity());
        views.setTextViewText(R.id.mesure, item.getMeasure());
        views.setTextViewText(R.id.ing, item.getIngredient());

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
