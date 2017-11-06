package com.hernan.pruebas;


import io.reactivex.Observable;

public interface GetListRepository {
    Observable<String[]> getList1();

    Observable<String[]> getList2();
}
