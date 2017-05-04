package abe.appsfactory.nanodegree.bakingapp.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Arrays;

import abe.appsfactory.nanodegree.bakingapp.R;
import abe.appsfactory.nanodegree.bakingapp.ui.activites.MainActivity;

public class WidgetProvider extends AppWidgetProvider {
    public static final String ACTION_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";
    public static final String EXTRA_RECIPE_ID = "extra.recipeID";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d("WidgetProvider", Arrays.toString(appWidgetIds));
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            setRemoteAdapter(context, views);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

            views.setOnClickPendingIntent(R.id.widget_empty, pi);

            views.setEmptyView(R.id.widget_list, R.id.widget_empty);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d("WidgetProvider", "onReceive: " + intent);
        if (ACTION_UPDATE.equals(intent.getAction())) {
            if (intent.hasExtra(EXTRA_RECIPE_ID)) {
                int recipeId = intent.getExtras().getInt(EXTRA_RECIPE_ID);
                context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE)
                        .edit()
                        .putInt(EXTRA_RECIPE_ID, recipeId)
                        .apply();
            }
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, getClass()));
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
        }
    }

    private void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        Log.d("WidgetProvider", "setRemoteAdapter");
        views.setRemoteAdapter(R.id.widget_list, new Intent(context, WidgetRemoteViewService.class));
    }
}
