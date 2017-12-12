package pruebas.hernan.com.room;

import java.util.List;

interface MainContract {
    interface View {
        void fillAdapter(List<String> list);
    }

    interface Presenter {
        void requestItems();
    }
}
