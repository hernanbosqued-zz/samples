package com.hernan.pruebas;

class BasePresenter<T> {

    T view;

    public void setView(T view){
        this.view = view;
    }

    public void onDestroy(){
        view = null;
    }
}
