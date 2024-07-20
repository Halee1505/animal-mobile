package com.funix.animal.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class CustomTextView extends AppCompatTextView {

    private Paint borderPaint;
    private Paint textPaint;
    private Paint shadowPaint;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Initialize text paint
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLUE);

        // Initialize border paint
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStrokeWidth(5);

        // Initialize shadow paint
        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String text = getText().toString();
        float x = (getWidth() - textPaint.measureText(text)) / 2;
        float y = getBaseline();

        // Draw text border
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(5);
        textPaint.setColor(Color.BLACK);
        canvas.drawText(text, x, y, textPaint);

        // Draw text fill
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLUE);
        canvas.drawText(text, x, y, textPaint);

        // Draw shadow (reflection)
        canvas.save();
        canvas.translate(0, getHeight());
        canvas.scale(1, -1);
        LinearGradient shader = new LinearGradient(0, getHeight(), 0, getHeight() / 4,
                Color.BLUE, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        shadowPaint.setShader(shader);
        canvas.drawText(text, x, y, shadowPaint);
        canvas.restore();

        super.onDraw(canvas);
    }
}
