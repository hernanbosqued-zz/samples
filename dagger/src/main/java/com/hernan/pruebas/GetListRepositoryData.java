package com.hernan.pruebas;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


class GetListRepositoryData implements GetListRepository {

    private final GetListRepositoryCache cache;
    private final GetListRespositoryStorage storage;

    @Inject
    GetListRepositoryData(GetListRepositoryCache cache, GetListRespositoryStorage storage){
        this.cache = cache;
        this.storage = storage;
    }

    @Override
    public Observable<String[]> getList1() {
        if( storage.getList1() == null ){
                return cache.getList1().doOnNext(new Consumer<String[]>() {
                    @Override
                    public void accept(String[] list) throws Exception {
                        storage.setList(GetListRespositoryStorage.LIST_1,list);
                    }
                });
        }
        return storage.getList1();
    }

    @Override
    public Observable<String[]> getList2() {
        if( storage.getList2() == null ){
            return cache.getList2().doOnNext(new Consumer<String[]>() {
                @Override
                public void accept(String[] list) throws Exception {
                    storage.setList(GetListRespositoryStorage.LIST_2,list);
                }
            });
        }
        return storage.getList2();
    }
}
