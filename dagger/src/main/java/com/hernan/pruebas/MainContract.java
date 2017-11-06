package com.hernan.pruebas;

interface MainContract {
    interface View {
        void fillAdapter(String[] list);
    }

    interface Presenter {
        void requestItems();
    }
}
