package pruebas.hernan.com.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private final GetListUseCase getListUseCase;
    private final InsertUseCase insertUseCase;

    @Inject
    MainPresenter(GetListUseCase getListUseCase, InsertUseCase insertUseCase) {
        this.getListUseCase = getListUseCase;
        this.insertUseCase = insertUseCase;
        insertItem("hernan " + new Random().nextInt(100));
        insertItem("hernan " + new Random().nextInt(100));
        insertItem("hernan " + new Random().nextInt(100));
        insertItem("hernan " + new Random().nextInt(100));
    }

    @Override
    public void requestItems() {
        getListUseCase.execute(new DisposableObserver<List<Repo>>() {
            @Override
            public void onNext(List<Repo> repos) {
                List<String> data = new ArrayList<>();
                for(Repo repo:repos) {
                    data.add(repo.name);
                }
                view.fillAdapter(data);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

    public void insertItem(String param) {
        insertUseCase.execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean response) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        },param);
    }

    @Override
    public void onDestroy() {
        getListUseCase.dispose();
        super.onDestroy();
    }
}
