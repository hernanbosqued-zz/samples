package com.hernan.pruebas;

import dagger.Subcomponent;

@SessionScope
@Subcomponent( modules = {SessionModule.class})
interface SessionComponent {
    void inject(MainFragment mainFragment);
}
