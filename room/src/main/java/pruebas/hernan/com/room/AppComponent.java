package pruebas.hernan.com.room;

import dagger.Component;


@AppScope
@Component(modules = {AppModule.class})
interface AppComponent {
    void inject(MainFragment mainFragment);
}
