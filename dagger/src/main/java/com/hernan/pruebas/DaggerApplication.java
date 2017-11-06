package com.hernan.pruebas;


import android.app.Application;

import com.facebook.stetho.Stetho;

public class DaggerApplication extends Application{

    private SessionComponent sessionComponent;
    private static DaggerApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        instance = this;

        AppComponent appcomponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appcomponent.inject(this);
        sessionComponent = appcomponent.sessionComponent(new SessionModule());
    }

    public static DaggerApplication getInstance(){
        return instance;
    }

    public SessionComponent getSessionComponent(){
        return this.sessionComponent;
    }
}
