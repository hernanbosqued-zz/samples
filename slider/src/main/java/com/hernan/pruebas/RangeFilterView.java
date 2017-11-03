package com.hernan.pruebas;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RangeFilterView extends LinearLayout {

    FilterEntity filterEntity;
    RangeSeekBar seekBar;
    TextView title;
    TextView range;

    public RangeFilterView(Context context) {
        super(context);
        setOrientation(VERTICAL);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.range_filter_view, this, true);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, context.getResources().getDisplayMetrics());
        view.setPadding(padding, padding, padding, padding);
        seekBar = (RangeSeekBar) findViewById(R.id.seekbar);
        title = (TextView) findViewById(R.id.title);
        range = (TextView) findViewById(R.id.range);
    }

    public void setFilterEntity(FilterEntity filterEntity) {
        this.filterEntity = filterEntity;
        initView();

    }

    public void initView() {
        title.setText(this.filterEntity.name);
        final float steps = (float) (filterEntity.max - filterEntity.min) / filterEntity.stepValue;

        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(float minValue, float maxValue) {
                if (minValue == 0 && maxValue == 1) {
                    range.setText("Any price");
                } else if (minValue == 0 && maxValue < 1) {
                    int finalMaxValue = Math.round(maxValue * steps) * filterEntity.stepValue + filterEntity.min;
                    range.setText("Up to " + finalMaxValue);
                } else if (minValue > 0 && maxValue == 1) {
                    int finalMinValue = Math.round(minValue * steps) * filterEntity.stepValue + filterEntity.min;
                    range.setText("Above " + finalMinValue);
                } else if (minValue > 0 && maxValue < 1) {
                    int finalMinValue = Math.round(minValue * steps) * filterEntity.stepValue + filterEntity.min;
                    int finalMaxValue = Math.round(maxValue * steps) * filterEntity.stepValue + filterEntity.min;
                    range.setText(finalMinValue + " - " + finalMaxValue);
                }
            }
        });
    }
}
