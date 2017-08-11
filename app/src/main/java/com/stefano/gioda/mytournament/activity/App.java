package com.stefano.gioda.mytournament.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by mint on 8/11/17.
 */

public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
