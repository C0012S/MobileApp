package com.example.customviewex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.SyncStateContract;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View { //View 클래스는 그 뷰를 포함할 액티비티에 대한 참조를 전달받는다

    private String mColor = "FF0000FF";

    public MyView(Context context) { //Custom View 역시 자기를 포함할 액티비티에 대한 참조를 생성자의 인수로 받아서 부모의 생성자에게 전달하는 형태로 구성
        super(context); //자식 클래스의 생성자에서는 명시적으로 상위 클래스의 생성자를 super()로 처리해 줘야 한다
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.YELLOW); // 의미 없음 //onDraw 메소드 호출되었을 때, 배경 흰색으로 설정되었기 때문에 //배경을 한 번만 칠할 것이라면 생성자에서 처리하는 것이, 매번 배경을 다시 그릴 필요가 있다면 onDraw 안에서 처리하는 것이 나을 수 있다

        for (int i = 0; i < attrs.getAttributeCount(); i++) { //속성의 개수 만큼 반복
            if (attrs.getAttributeName(i).equals("color")) { //color라는 속성 정보가 있는지
                String c = attrs.getAttributeValue(i); //i 번째 항목의 값을 가져와라

                if (c != null) {
                    mColor = c.replace("#", "FF");
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { //나의 크기와 너비 부분들을 지정되어진 속성 값에 따라 적절히 설정할 수 있도록 구성
        // height 진짜 크기 구하기
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = 0;
        switch(heightMode) {
            case MeasureSpec.UNSPECIFIED: // mode가 셋팅되지 않은 크기가 넘어올 때
                heightSize = heightMeasureSpec;
                break;

            case MeasureSpec.AT_MOST: // wrap_content (뷰 내부의 크기에 따라 크기가 달라짐)
                heightSize = 200;
                break;

            case MeasureSpec.EXACTLY: // fill_parent, match_parent (외부에서 이미 크기가 지정되었음)
                heightSize = MeasureSpec.getSize(heightMeasureSpec);
                break;
        }

        // width 진짜 크기 구하기
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = 0;
        switch(widthMode) {
            case MeasureSpec.UNSPECIFIED: // mode가 셋팅되지 않은 크기가 넘어올 때
                widthSize = widthMeasureSpec;
                break;

            case MeasureSpec.AT_MOST: // wrap_content (뷰 내부의 크기에 따라 크기가 달라짐)
                widthSize = 320;
                break;

            case MeasureSpec.EXACTLY: // fill_parent, match_parent (외부에서 이미 크기가 지정되었음)
                widthSize = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }

//        Log.w(Constants.TAG, "onMeasure(" + widthMeasureSpec + "," + heightMeasureSpec + ")");

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) { //어떻게 그려질지에 대한 코드 작성
        super.onDraw(canvas);

        Paint pnt = new Paint(); //인자 없는 생성자를 이용해서 하나의 Paint 객체를 생성
        //pnt.setColor(Color.BLUE);
        pnt.setColor((int) Long.parseLong(mColor, 16)); //생성자를 통해서 속성 값을 읽어올 수 있도록, 적용해 오는 형태로 수정

        canvas.drawColor(Color.WHITE); //매개변수로 전달받은 canvas 객체를 통해 draw- 메소드를 통해 처리
        canvas.drawCircle(100, 100, 80, pnt);
    }
}
