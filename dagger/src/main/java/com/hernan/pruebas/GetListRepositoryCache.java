package com.hernan.pruebas;

import javax.inject.Inject;

import io.reactivex.Observable;


class GetListRepositoryCache implements GetListRepository {

    @Inject
    public GetListRepositoryCache() {
    }

    @Override
    public Observable<String[]> getList1() {
        return Observable.just(new String[]{"a", "b", "c", "d"});
    }

    @Override
    public Observable<String[]> getList2() {
        return Observable.just(new String[]{"1", "2", "3", "4"});
    }
}
