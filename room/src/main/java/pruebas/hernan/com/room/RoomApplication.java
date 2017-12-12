package pruebas.hernan.com.room;

import android.app.Application;

public class RoomApplication extends Application {
    private static AppComponent component;
    private static RoomApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static RoomApplication getInstance(){
        return instance;
    }

    public AppComponent getComponent(){
        return component;
    }
}
