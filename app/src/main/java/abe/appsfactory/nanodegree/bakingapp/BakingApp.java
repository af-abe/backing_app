package abe.appsfactory.nanodegree.bakingapp;

import android.app.Application;

import io.realm.Realm;

public class BakingApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
