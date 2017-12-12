package pruebas.hernan.com.room;


import java.util.List;

import io.reactivex.Observable;

public interface GetListRepository {
    Observable<List<Repo>> getList();
    void insert(String name);
}
