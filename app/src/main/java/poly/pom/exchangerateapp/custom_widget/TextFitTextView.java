package poly.pom.exchangerateapp.custom_widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class TextFitTextView extends TextView {

    static final String TAG = "TextFitTextView";
    boolean fit = false;

    public TextFitTextView(Context context) {
        super(context);
    }

    public TextFitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextFitTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setFitTextToBox(Boolean fit) {
        this.fit = fit;
    }

    public void setTextWithReSetSize(CharSequence text,float textSize){
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        this.setText(text);
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (fit)
        _shrinkToFitsmall();

    }

    protected void _shrinkToFitsmall() {

        int height = this.getHeight();
        int lines = this.getLineCount();
        Rect r = new Rect();
        int y1 = this.getLineBounds(0, r);
        int y2 = this.getLineBounds(lines - 1, r);
//return px
        float size = this.getTextSize();
        if (y2 > height && size >= 4.0f) {
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, size - 2.0f);
            _shrinkToFitsmall();
        }

    }


}