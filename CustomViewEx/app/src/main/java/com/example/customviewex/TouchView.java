package com.example.customviewex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class TouchView extends View {

    private Point mPoint = new Point(); //좌표를 저장할 수 있는 객체 생성
    private String mStringAction = "NONE"; //터치 이벤트의 종류도 판별 //이벤트가 발생했을 때 어떤 이벤트가 발생했는지 설정

    public TouchView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint(); //지역적으로 Paint 객체 생성
        p.setTextSize(50); //글씨 크기 20 픽셀로 지정
        p.setColor(Color.BLUE);

        canvas.drawText("Event POS. X : " + mPoint.x + ", Y : " + mPoint.y, 0, 50, p); //이벤트가 발생된 좌표 값 출력 //현재 화면의 (0, 20)에 Paint 객체를 통해서 출력할 수 있도록 구성 //0부터 50까지 출력
        canvas.drawText("Event Action : " + mStringAction, 0, 120, p);
    }
    //현재 뷰에 대해서 터치 이벤트를 수행하도록 하기 위해서 콜백 메소드 추가
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX(); //float 타입을 int 타입으로 캐스팅
        int y = (int) event.getY(); //이벤트가 발생된 좌표 값 판별
        mPoint.set(x, y); //x, y 값을 mPoint 객체에 설정

        switch (event.getAction()) { //이벤트가 발생된 이벤트 종류 판별
            case MotionEvent.ACTION_DOWN: //정수 값은 MotionEvent의 종류 //ACTION_DONW: 터치를 했느냐라는 상태
                mStringAction = "ACTION_DOWN";
                break;

            case MotionEvent.ACTION_MOVE: //움직임이 발생했는지
                mStringAction = "ACTION_MOVE";
                break;

            case MotionEvent.ACTION_UP: //터치가 해제되었는지
                mStringAction = "ACTION_UP";
                break;
        }

        invalidate(); //현재 터치가 일어난 좌표 값과, 터치가 일어났을 때의 종류 값들을 다시 그려 주는 onDraw 메소드가 자동으로 호출된다

        return true; //터치 이벤트가 발생되었을 때의 이벤트 처리가 현재 단계에서 다 끝났다면 부모의 터치 이벤트를 호출할 필요 없이 return true; 해서, 이후 과정에서 추가적인 이벤트에 대한 처리가 일어나지 않도록 설정 가능
        //return super.onTouchEvent(event); //이후 과정에서 추가적으로 현재 터치에 대한 이벤트 처리를 하도록 해 보고 싶다면 false 값 반환 해 주도록 해도 된다
    }
}
