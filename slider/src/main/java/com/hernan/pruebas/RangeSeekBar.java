/*
Copyright 2014 Stephan Tittel and Yahoo Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.hernan.pruebas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import static android.view.MotionEvent.INVALID_POINTER_ID;


public class RangeSeekBar extends android.support.v7.widget.AppCompatImageView {

    private static final int LINE_HEIGHT = 4;
    private static final int TOUCH_AREA_GAP = 3;

    private float minValue = 0;
    private float maxValue = 1;

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Drawable thumbImage = getResources().getDrawable(R.drawable.circle);

    private float padding;
    private Thumb pressedThumb = null;

    private int activePointerId = INVALID_POINTER_ID;
    private OnRangeSeekBarChangeListener listener;
    private RectF lineBounds;

    private enum Thumb {
        MIN,
        MAX
    }

    public RangeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RangeSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        lineBounds = new RectF(padding, thumbImage.getIntrinsicHeight() / 2 - LINE_HEIGHT / 2, getWidth() - padding, thumbImage.getIntrinsicHeight() / 2 + LINE_HEIGHT / 2);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public void setOnRangeSeekBarChangeListener(OnRangeSeekBarChangeListener listener) {
        this.listener = listener;
        listener.onRangeSeekBarValuesChanged(getMinValue(), getMaxValue());
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = thumbImage.getIntrinsicHeight();
        setMeasuredDimension(width, height);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);

        paint.setColor(Color.GRAY);
        padding = thumbImage.getIntrinsicWidth() / 2;
        lineBounds.left = padding;
        lineBounds.right = getWidth() - padding;
        canvas.drawRect(lineBounds, paint);

        paint.setColor(getResources().getColor(R.color.colorAccent));
        lineBounds.left = valueToScreen(minValue);
        lineBounds.right = valueToScreen(maxValue);
        canvas.drawRect(lineBounds, paint);

        drawThumb(valueToScreen(minValue), canvas);
        drawThumb(valueToScreen(maxValue), canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        int pointerIndex;

        final int action = event.getAction();
        switch (action & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                activePointerId = event.getPointerId(event.getPointerCount() - 1);
                pointerIndex = event.findPointerIndex(activePointerId);
                pressedThumb = evalPressedThumb(event.getX(pointerIndex));

                // Only handle thumb presses.
                if (pressedThumb == null) {
                    return super.onTouchEvent(event);
                }

                setPressed(true);
                invalidate();
                trackTouchEvent(event);
                attemptClaimDrag();

                break;

            case MotionEvent.ACTION_MOVE:
                if (pressedThumb != null) {
                    trackTouchEvent(event);
                    if (listener != null) {
                        listener.onRangeSeekBarValuesChanged(getMinValue(), getMaxValue());
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                trackTouchEvent(event);
                setPressed(false);
                pressedThumb = null;
                invalidate();
                if (listener != null) {
                    listener.onRangeSeekBarValuesChanged(getMinValue(), getMaxValue());
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                setPressed(false);
                invalidate();
                break;
        }
        return true;
    }

    private void trackTouchEvent(MotionEvent event) {
        final int pointerIndex = event.findPointerIndex(activePointerId);
        final float x = event.getX(pointerIndex);

        if (Thumb.MIN.equals(pressedThumb)) {
            setMinValue(screenToValue(x));
        } else if (Thumb.MAX.equals(pressedThumb)) {
            setMaxValue(screenToValue(x));
        }
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    private void drawThumb(float screenCoord, Canvas canvas) {
        Drawable drawable = getResources().getDrawable(R.drawable.circle);
        drawable.setBounds((int) screenCoord - drawable.getIntrinsicWidth() / 2,
                0,
                (int) screenCoord - drawable.getIntrinsicWidth() / 2 + drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
    }

    private Thumb evalPressedThumb(float touchX) {
        Thumb result = null;
        boolean minThumbPressed = isInThumbRange(touchX, minValue);
        boolean maxThumbPressed = isInThumbRange(touchX, maxValue);
        if (minThumbPressed && maxThumbPressed) {
            result = (touchX / getWidth() > 0.5f) ? Thumb.MIN : Thumb.MAX;
        } else if (minThumbPressed) {
            result = Thumb.MIN;
        } else if (maxThumbPressed) {
            result = Thumb.MAX;
        }
        return result;
    }

    private boolean isInThumbRange(float screenCoord, float value) {
        return Math.abs(screenCoord - valueToScreen(value)) <= thumbImage.getIntrinsicWidth() * TOUCH_AREA_GAP;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    private void setMinValue(float value) {
        minValue = Math.max(0, Math.min(value, maxValue));
        invalidate();
    }

    private void setMaxValue(float value) {
        maxValue = Math.min(1, Math.max(value, minValue));
        invalidate();
    }

    private float valueToScreen(float normalizedCoord) {
        return padding + normalizedCoord * (getWidth() - 2 * padding);
    }

    private float screenToValue(float screenCoord) {
        int width = getWidth();
        if (width <= 2 * padding) {
            return 0;
        } else {
            float result = (screenCoord - padding) / (width - 2 * padding);
            return Math.min(1, Math.max(0, result));
        }
    }

    interface OnRangeSeekBarChangeListener {
        void onRangeSeekBarValuesChanged(float minValue, float maxValue);

    }
}