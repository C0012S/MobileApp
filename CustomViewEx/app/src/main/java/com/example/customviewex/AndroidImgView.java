package com.example.customviewex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class AndroidImgView extends View {

    Bitmap mBmp;

    public AndroidImgView(Context context) {
        super(context);

        mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.gingerdroid);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBmp, 0, 0, null); //(0, 0) 위치에 그린다 //paint 객체는 필요 없어서 null 값을 지정
    }
}
