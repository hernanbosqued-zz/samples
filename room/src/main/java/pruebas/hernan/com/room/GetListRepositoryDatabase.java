package pruebas.hernan.com.room;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


class GetListRepositoryDatabase implements GetListRepository {

    private final RepoDao repoDao;

    @Inject
    public GetListRepositoryDatabase(RepoDao repoDao) {
        this.repoDao = repoDao;
    }

    @Override
    public Observable<List<Repo>> getList() {
        return repoDao.getAllRepos().toObservable();
    }

    @Override
    public void insert(String name) {
        repoDao.insert(new Repo(name));
    }
}
