package com.example.project_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class DrawGame extends View {

    private int mChance = 3;
    private int mScore = 0;

    Random random = new Random();
    int mX = random.nextInt(381);
    int mY = random.nextInt(550 - 60 + 1) + 60;

    private Rect mRect;

    private Thread mThread;
    private Handler mHandler = new Handler();

    private ArrayList<Rect> mList;
    int id = 0;

    public DrawGame(Context context) {
        super(context);

        mRect = new Rect(0, 0, 100, 100);
        mList = new ArrayList<Rect>();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint pnt = new Paint();
        Paint pnt2 = new Paint();

        pnt.setTextSize(30);
        pnt.setColor(Color.BLUE);
        canvas.drawText("기회 : " + mChance, 0, 30, pnt);
        canvas.drawText("점수 : " + mScore, 0, 60, pnt);

        pnt2.setColor(Color.RED);

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (mThread == Thread.currentThread()) {
                    final Runnable callback = new Runnable() {
                        @Override
                        public void run() {
                            if (mChance > 0) {
                                mX = random.nextInt(381);
                                mY = random.nextInt(550 - 60 + 1) + 60;
                            }
                        }
                    };
                    Message msg = Message.obtain(mHandler, callback);

                    mHandler.sendMessage(msg);

                    try {
                        Thread.sleep(2000);
                        invalidate();
                    }
                    catch (InterruptedException e) {    }
                }
            }
        });
        mThread.start();

        if (mChance > 0) {
            mRect.set(mX, mY, mX + 100, mY + 100);
            mList.add(mRect);
            id = id + 1;
            canvas.drawRect(mRect, pnt2);
        }

        else {
            canvas.drawRect(mRect, pnt2);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int eventX = (int) event.getX();
        int eventY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mList.get(id - 1).contains(eventX, eventY)) {
                    mScore = mScore + 1;
                    invalidate();
                    return true;
                }

                else {
                    if (mChance > 0) {
                        mChance = mChance - 1;
                    }

                    else {
                        mChance = 0;
                    }
                    invalidate();
                    return false;
                }
        }
        return false;
    }
}
