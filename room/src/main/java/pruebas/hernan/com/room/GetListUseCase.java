package pruebas.hernan.com.room;


import java.util.List;
import javax.inject.Inject;

import io.reactivex.Observable;

class GetListUseCase extends UseCase<List<Repo>,Void> {

    private final GetListRepository repo;

    @Inject
    GetListUseCase(GetListRepository repo) {
        this.repo = repo;
    }

    @Override
    public Observable<List<Repo>> buildObservable(Void param) {
        return repo.getList();
    }
}