package pruebas.hernan.com.room;

class BasePresenter<T> {

    T view;

    public void setView(T view){
        this.view = view;
    }

    public void onDestroy(){
        view = null;
    }
}
