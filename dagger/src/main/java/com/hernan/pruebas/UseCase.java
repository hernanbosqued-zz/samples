package com.hernan.pruebas;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

abstract class UseCase<T,V>{

    private final CompositeDisposable disposables;

    public abstract Observable<T> buildObservable(V param);

    UseCase() {
        this.disposables = new CompositeDisposable();
    }

    void execute(DisposableObserver<T> observer, V params) {
        Observable<T> observable = buildObservable(params).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        disposables.add(observable.subscribeWith(observer));
    }

    void dispose() {
        disposables.dispose();
    }
}
