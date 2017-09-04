package com.hernan.pruebas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = (LinearLayout) findViewById(R.id.main);
        RangeFilterView rangeFilterView = new RangeFilterView(this);
        rangeFilterView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        layout.addView(rangeFilterView);

//        final RangeSeekBar seekBar1 = (RangeSeekBar) findViewById(R.id.seekbar1);
//        final TextView textView1 = (TextView) findViewById(R.id.value1);
//        seekBar1.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, double minValue, double maxValue) {
//                textView1.setText((int)(seekBar1.getMinValue() * 107950 + 50) + "-" + (int)(seekBar1.getMaxValue() * 107950 + 50));
//            }
//        });
//
//        final RangeSeekBar seekBar2 = (RangeSeekBar) findViewById(R.id.seekbar2);
//        final TextView textView2 = (TextView) findViewById(R.id.value2);
//        seekBar2.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, double minValue, double maxValue) {
//                textView2.setText((int)(seekBar2.getMinValue() * 150000 + 5000) + "-" + (int)(seekBar2.getMaxValue() * 150000 + 5000));
//            }
//        });
//
//        final RangeSeekBar seekBar3 = (RangeSeekBar) findViewById(R.id.seekbar3);
//        final TextView textView3 = (TextView) findViewById(R.id.value3);
//        seekBar3.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, double minValue, double maxValue) {
//                textView3.setText((int)(seekBar3.getMinValue() * 8500 + 500) + "-" + (int)(seekBar3.getMaxValue() * 8500 + 500));
//            }
//        });
//
//        final RangeSeekBar seekBar4 = (RangeSeekBar) findViewById(R.id.seekbar4);
//        final TextView textView4 = (TextView) findViewById(R.id.value4);
//        seekBar4.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, double minValue, double maxValue) {
//                textView4.setText((int)(seekBar4.getMinValue() * 1980000 + 20000) + "-" + (int)(seekBar4.getMaxValue() * 1980000 + 20000));
//            }
//        });
//
//        final RangeSeekBar seekBar5 = (RangeSeekBar) findViewById(R.id.seekbar5);
//        final TextView textView5 = (TextView) findViewById(R.id.value5);
//        seekBar5.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, double minValue, double maxValue) {
//                textView5.setText((int)(seekBar5.getMinValue() * 297000 + 3000) + "-" + (int)(seekBar5.getMaxValue() * 297000  + 3000));
//            }
//        });
//
//        final RangeSeekBar seekBar6 = (RangeSeekBar) findViewById(R.id.seekbar6);
//        final TextView textView6 = (TextView) findViewById(R.id.value6);
//        seekBar6.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, double minValue, double maxValue) {
//                textView6.setText((int)(seekBar6.getMinValue() * 950 + 50) + "-" + (int)(seekBar6.getMaxValue() * 950 + 50));
//            }
//        });
    }
}
