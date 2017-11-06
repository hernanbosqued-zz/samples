package com.hernan.pruebas;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    GetListUseCase getListUseCase;

    @Inject
    MainPresenter(GetListUseCase getListUseCase) {
        this.getListUseCase = getListUseCase;
    }

    @Override
    public void requestItems() {
        getListUseCase.execute(new DisposableObserver<String[]>() {
            @Override
            public void onNext(@NonNull String[] list) {
                view.fillAdapter( list );
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, false);
    }

    @Override
    public void onDestroy() {
        getListUseCase.dispose();
        super.onDestroy();
    }
}
