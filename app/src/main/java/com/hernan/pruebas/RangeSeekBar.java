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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import static android.view.MotionEvent.INVALID_POINTER_ID;


public class RangeSeekBar extends android.support.v7.widget.AppCompatImageView {

    private final int LINE_HEIGHT = 4;
    private final int TOUCH_AREA_GAP = 3;

    private double minValue = 0;
    private double maxValue = 1;

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Bitmap thumbImage = drawableToBitmap(R.drawable.circle);
    private float padding;
    private Thumb pressedThumb = null;

    private int activePointerId = INVALID_POINTER_ID;
    private OnRangeSeekBarChangeListener listener;
    private RectF rectF;

    public RangeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RangeSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        rectF = new RectF(padding, thumbImage.getHeight() / 2 - LINE_HEIGHT / 2, getWidth() - padding, thumbImage.getHeight() / 2 + LINE_HEIGHT / 2);

        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public void setOnRangeSeekBarChangeListener(OnRangeSeekBarChangeListener listener) {
        this.listener = listener;
        listener.onRangeSeekBarValuesChanged(this, getMinValue(), getMaxValue());
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = thumbImage.getHeight();
        setMeasuredDimension(width, height);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Style.FILL);
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);

        padding = thumbImage.getWidth() / 2;

        // draw seek bar background line
        rectF.left = padding;
        rectF.right = getWidth() - padding;
        canvas.drawRect(rectF, paint);

        // draw seek bar active range line
        rectF.left = normalizedToScreen(minValue);
        rectF.right = normalizedToScreen(maxValue);

        paint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawRect(rectF, paint);

        drawThumb(normalizedToScreen(minValue), canvas);
        drawThumb(normalizedToScreen(maxValue), canvas);
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
                        listener.onRangeSeekBarValuesChanged(this, getMinValue(), getMaxValue());
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                trackTouchEvent(event);
                setPressed(false);
                pressedThumb = null;
                invalidate();
                if (listener != null) {
                    listener.onRangeSeekBarValuesChanged(this, getMinValue(), getMaxValue());
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
            setMinValue(screenToNormalized(x));
        } else if (Thumb.MAX.equals(pressedThumb)) {
            setMaxValue(screenToNormalized(x));
        }
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    private void drawThumb(float screenCoord, Canvas canvas) {
        Drawable drawable = getResources().getDrawable(R.drawable.circle);
        drawable.setBounds( screenCoord - thumbImage.getWidth() / 2, 0 );
        drawable.draw(canvas);
        //canvas.drawBitmap(thumbImage, screenCoord - thumbImage.getWidth() / 2, 0, paint);
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

    private boolean isInThumbRange(float touchX, double normalizedThumbValue) {
        return Math.abs(touchX - normalizedToScreen(normalizedThumbValue)) <= thumbImage.getWidth() * TOUCH_AREA_GAP;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    private void setMinValue(double value) {
        minValue = Math.max(0d, Math.min(1d, Math.min(value, maxValue)));
        invalidate();
    }

    private void setMaxValue(double value) {
        maxValue = Math.max(0d, Math.min(1d, Math.max(value, minValue)));
        invalidate();
    }

    private float normalizedToScreen(double normalizedCoord) {
        return (float) (padding + normalizedCoord * (getWidth() - 2 * padding));
    }

    private double screenToNormalized(float screenCoord) {
        int width = getWidth();
        if (width <= 2 * padding) {
            return 0d;
        } else {
            double result = (screenCoord - padding) / (width - 2 * padding);
            return Math.min(1d, Math.max(0d, result));
        }
    }

    public Bitmap drawableToBitmap(int drawableId) {
        Drawable drawable = getResources().getDrawable(drawableId);

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    interface OnRangeSeekBarChangeListener {
        void onRangeSeekBarValuesChanged(RangeSeekBar bar, double minValue, double maxValue);
    }

    private enum Thumb {
        MIN, MAX
    }
}