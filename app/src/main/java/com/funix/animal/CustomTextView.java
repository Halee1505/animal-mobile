package com.funix.animal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getCurrentTextColor());

        // Vẽ văn bản gốc
        String text = getText().toString();
        canvas.drawText(text, 0, getBaseline(), paint);

        // Thiết lập gradient cho hiệu ứng phản chiếu
        LinearGradient shader = new LinearGradient(0, getHeight(), 0, getHeight() + getHeight() / 4,
                getCurrentTextColor(), getCurrentTextColor() & 0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        // Lưu trạng thái canvas
        canvas.save();

        // Dịch chuyển canvas và lật ngược văn bản để tạo hiệu ứng phản chiếu
        canvas.translate(0, getHeight());
        canvas.scale(1, -1);

        // Vẽ văn bản phản chiếu
        canvas.drawText(text, 0, getBaseline(), paint);

        // Khôi phục trạng thái canvas
        canvas.restore();
    }
}
