package com.hernan.pruebas;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    private final DaggerApplication application;

    AppModule(DaggerApplication application){
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesContext(){
        return this.application;
    }

    @Provides
    @Singleton
    Gson providesGson(){
        return new Gson();
    }
}
