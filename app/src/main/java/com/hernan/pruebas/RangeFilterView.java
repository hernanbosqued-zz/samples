package com.hernan.pruebas;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RangeFilterView extends LinearLayout {
    private FilterEntity filterEntity;

    public RangeFilterView(Context context, final FilterEntity filterEntity) {
        super(context);
        this.filterEntity = filterEntity;
        setOrientation(VERTICAL);

        LayoutInflater  mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.range_filter_view, this, true);

        final RangeSeekBar seekBar = (RangeSeekBar) findViewById(R.id.seekbar);
        final TextView textView = (TextView) findViewById(R.id.value);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged( float minValue, float maxValue) {
                textView.setText((int) (minValue * filterEntity.max + filterEntity.min) + "-" + (int) (maxValue * filterEntity.max + filterEntity.min));
            }
        });
    }
}
