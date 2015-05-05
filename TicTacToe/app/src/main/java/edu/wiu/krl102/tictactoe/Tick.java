package edu.wiu.krl102.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;



public class Tick extends View {

    SparseArray<PointF> points = new SparseArray<PointF>();
    int count;

    public Tick(Context context) {
        this(context, null);
        count=1;
    }

    public Tick(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Tick(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);

    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(200);
        for(int i=0;i<points.size();i++) {
            PointF point = points.valueAt(i);
            String t1 = "Tic";
            String t2 = "Tac";
            String t3 = "Toe";

            if (count == 1)
                canvas.drawText(t1, point.x, point.y, paint);
            if (count == 2)
                canvas.drawText(t2, point.x, point.y, paint);
            if (count == 3)
                canvas.drawText(t3, point.x, point.y, paint);
        }

    }

    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();
        int index = event.getActionIndex(); //gets index of pointer that triggered event
        int id = event.getPointerId(index); //gets id of the pointer at index

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
            count++;
            if (count > 3)
                count = 1;
            PointF point = new PointF(event.getX(index), event.getY(index));
            points.put(id, point);
            invalidate();
        }
        if (action == MotionEvent.ACTION_MOVE) {
            //Update all of the points with their current location
            for (int i=0;i<event.getPointerCount();i++) {
                int pointerId = event.getPointerId(i);
                PointF point = points.get(pointerId);
                point.set(event.getX(i), event.getY(i));
                invalidate();
            }

        }
        if(action == MotionEvent.ACTION_POINTER_UP){
            points.remove(id);
            invalidate();
        }


        return true;
    }

}