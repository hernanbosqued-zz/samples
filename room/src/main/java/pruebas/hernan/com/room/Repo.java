package pruebas.hernan.com.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Repo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public Repo(String name) {
        this.name = name;
    }
}