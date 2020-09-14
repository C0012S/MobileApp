package com.example.hw_customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class TouchEvent extends View {

    private Rect mRect;
    private Bitmap m_icon = BitmapFactory.decodeResource(getResources(), R.drawable.android_s);
    private ArrayList<Vertex> mVList;
    int dragid = -1;

    public TouchEvent(Context context) {
        super(context);

        mRect = new Rect(0, 0, 100, 100);
        mVList = new ArrayList<Vertex>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mVList == null) {
            return;
        }

        else {
            for (int i = 0; i < mVList.size(); i++) {
                mRect.set(mVList.get(i).x - 50, mVList.get(i).y - 50, mVList.get(i).x + 50, mVList.get(i).y + 50);
                canvas.drawBitmap(m_icon, null, mRect, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int cx = (int) event.getX();
        int cy = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mRect = new Rect(cx - 50, cy - 50, cx + 50, cy + 50);

                for (int i = 0; i < mVList.size(); i++) {
                    if (mRect.contains(mVList.get(i).x, mVList.get(i).y)) {
                        dragid = i;
                        break;
                    }

                    else {
                        dragid = -1;
                    }
                }

                if (dragid == -1) {
                    mVList.add(new Vertex(cx, cy));
                }

                invalidate();

                return true;

            case MotionEvent.ACTION_MOVE:
                if (dragid > -1) {
                    mRect.set(mVList.get(dragid).x - 50, mVList.get(dragid).y - 50, mVList.get(dragid).x + 50, mVList.get(dragid).y + 50);
                    if (mRect.contains(cx, cy)) {
                        mVList.get(dragid).x = cx;
                        mVList.get(dragid).y = cy;
                    }
                }

                invalidate();

                return true;
        }
        return false;
    }

    public class Vertex {
        int x, y;

        public Vertex (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
