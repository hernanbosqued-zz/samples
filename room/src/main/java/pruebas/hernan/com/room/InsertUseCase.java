package pruebas.hernan.com.room;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

class InsertUseCase extends UseCase<Boolean, String> {

    private final GetListRepository repo;

    @Inject
    InsertUseCase(GetListRepository repo) {
        this.repo = repo;
    }

    @Override
    public Observable<Boolean> buildObservable(final String param) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                repo.insert(param);
                e.onNext(true);
            }
        });
    }
}