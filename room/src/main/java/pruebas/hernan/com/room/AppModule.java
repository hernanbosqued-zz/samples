package pruebas.hernan.com.room;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    RoomApplication application;
    public AppModule( RoomApplication application) {
        this.application = application;
    }

    @Provides
    @AppScope
    GetListRepository providesGetListRepository(GetListRepositoryDatabase data) {
        return data;
    }

    @Provides
    @AppScope
    RepoDao providesRepoDao( ) {
        return RepoDatabase.getInstance(application).getRepoDao();
    }

}
