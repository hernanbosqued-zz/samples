package com.hernan.pruebas;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
class SessionModule {

    @Provides
    @SessionScope
    Object providesParam() {
        return new Object();
    }

    @Provides
    @SessionScope
    GetListRepository providesGetListRepository(GetListRepositoryData data) {
        return data;
    }

    @Provides
    @SessionScope
    String[] providesItems(Context context) {
        return context.getResources().getStringArray(R.array.my_string_array);
    }
}
