package com.hernan.pruebas;

import javax.inject.Singleton;
import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})
interface AppComponent {
    SessionComponent sessionComponent(SessionModule sessionComponent);
    void inject(DaggerApplication daggerApplication);
}
