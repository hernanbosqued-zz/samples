package com.hernan.pruebas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.main);
        layout.addView(new RangeFilterView(this, new FilterEntity(50,10000,100,"Precio")));
        layout.addView(new RangeFilterView(this, new FilterEntity(50,10000,100,"Precio")));
        layout.addView(new RangeFilterView(this, new FilterEntity(50,10000,100,"Precio")));
    }
}
