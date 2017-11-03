package com.hernan.pruebas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import static com.hernan.pruebas.R.id.min;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RangeFilterView filter;
    private EditText minEdit;
    private EditText maxEdit;
    private EditText stepEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.main);
        minEdit = (EditText) findViewById(min);
        maxEdit = (EditText) findViewById(R.id.max);
        stepEdit = (EditText) findViewById(R.id.step);
        Button button = (Button) findViewById(R.id.button);

        filter = new RangeFilterView(this);
        filter.setFilterEntity(new FilterEntity(0, 100000, 1000, "Precio"));
        layout.addView(filter);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Integer min = Integer.valueOf(minEdit.getText().toString());
        Integer max = Integer.valueOf(maxEdit.getText().toString());
        Integer step = Integer.valueOf(stepEdit.getText().toString());

        if (min < 0 || max < min || step < 1 || step > max-min) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else {
            filter.setFilterEntity(new FilterEntity(min,
                    max,
                    step,
                    "Precio"));
        }
    }
}
