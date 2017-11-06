package com.hernan.pruebas;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import javax.inject.Inject;
import io.reactivex.Observable;


public class GetListRespositoryStorage implements GetListRepository {

    private static final String PANAMERA_PREFERENCES = "dagger_preferences";
    public static final String LIST_1 = "list1";
    public static final String LIST_2 = "list2";
    private SharedPreferences sharedPreferences;
    private final Gson gson;

    @Inject
    public GetListRespositoryStorage(Context context, Gson gson) {
        sharedPreferences = context.getSharedPreferences(PANAMERA_PREFERENCES, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    private String getStringPreference(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    private void setStringPreference(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    private String[] getList(String key) {
        String[] list = null;

        String json = getStringPreference(key, null);
        if (json != null) {
            Type listType = new TypeToken<String[]>() { }.getType();
            list = gson.fromJson(json, listType);
        }
        return list;
    }

    public void setList(String key, String[] value) {
        setStringPreference(key,gson.toJson(value));
    }

    @Override
    public Observable<String[]> getList1() {
        if (sharedPreferences.contains(LIST_1)) {
            return Observable.just( getList(LIST_1));
        }
        return null;
    }

    @Override
    public Observable<String[]> getList2() {
        if (sharedPreferences.contains(LIST_2)) {
            return Observable.just( getList(LIST_2));
        }
        return null;
    }
}