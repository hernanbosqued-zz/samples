package pruebas.hernan.com.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface RepoDao {

    @Query("SELECT * FROM repo")
    Maybe<List<Repo>> getAllRepos();

    @Insert
    void insert(Repo... repos);

    @Update
    void update(Repo... repos);

    @Delete
    void delete(Repo... repos);
}
