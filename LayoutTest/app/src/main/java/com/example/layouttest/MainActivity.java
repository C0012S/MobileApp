package com.example.layouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inflation_combin); //GridLayout을 가지고 있는 기본적인 ViewGroup을 자동으로 전개

        ViewGroup root = findViewById(R.id.root);
/*
        TextView text = (TextView) View.inflate(this, //Button은 정적인 xml 문서에서 부착, TextView는 코드를 통해서 부착
                R.layout.mytext, null); //View의 inflate 메소드를 통해서 mytext라는 xml 문서의 내용을 수동 전개하도록 구성
        root.addView(text); //수동 전개한 TextView를 root인, GridLayout을 갖고 있는 곳에 연결하는 처리
*/

        for (int i = 0; i < 20; i++) { //TextView를 하나 생성하고 부착하는 일을 반복적으로 여러 번 수행
            TextView text = (TextView) View.inflate(this, R.layout.mytext, null);
            root.addView(text); //개별적으로 설정되어진 하나의 내용을 코드를 통해 수동 전개해서 기존의 View와 연동 가능
        }
    }

/*
    private View page1, page2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayout_test);

        page1 = findViewById(R.id.b_1p);
        page2 = findViewById(R.id.b_2p);

        page2.setVisibility(View.INVISIBLE);
    }

    public void onClicked(View v) { //View는 이벤트를 발생시킨 View에 대한 참조를 받아 주는 매개변수
        switch (v.getId()) {
            case R.id.b_page1:
                page1.setVisibility(View.VISIBLE);
                page2.setVisibility(View.INVISIBLE);
                break;

            case R.id.b_page2:
                page1.setVisibility(View.INVISIBLE);
                page2.setVisibility(View.VISIBLE);
                break;
        }
    }
*/
}
