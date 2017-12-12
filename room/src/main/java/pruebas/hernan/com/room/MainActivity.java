package pruebas.hernan.com.room;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.idescout.sql.SqlScoutServer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String fragmentTag = "main_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        SqlScoutServer.create(this, getPackageName());

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);

        if (fragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment(), fragmentTag).commit();
        }
    }
}