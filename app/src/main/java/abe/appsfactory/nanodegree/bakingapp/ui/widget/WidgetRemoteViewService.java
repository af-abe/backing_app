package abe.appsfactory.nanodegree.bakingapp.ui.widget;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

public class WidgetRemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d("WidgetProvider", "RemoteViewsFactory: " + intent);
        return new WidgetViewsFactory(this.getApplicationContext());
    }
}
