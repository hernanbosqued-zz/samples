package com.hernan.pruebas;


import javax.inject.Inject;

import io.reactivex.Observable;

class GetListUseCase extends UseCase<String[], Boolean> {

    private final GetListRepository repo;

    @Inject
    GetListUseCase(GetListRepository repo) {
        this.repo = repo;
    }

    @Override
    public Observable<String[]> buildObservable(Boolean param) {
        if (param) {
            return repo.getList1();
        } else {
            return repo.getList2();
        }
    }
}
