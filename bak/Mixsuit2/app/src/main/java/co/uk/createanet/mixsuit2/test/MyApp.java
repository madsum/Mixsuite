package co.uk.createanet.mixsuit2.test;

import android.app.Application;
import android.content.Context;

/**
 * Created by masum on 22/10/15.
 */
public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}