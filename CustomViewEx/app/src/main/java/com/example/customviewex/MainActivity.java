package com.example.customviewex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); //레이아웃 파일로부터 전개되어진 내용으로 자신의 내용을 채운다

        //View v = new MyView(this); //생성자의 인수로 액티비티에 대한 참조를 넘겨 준다 //일반화를 위해 상위클래스인 View 참조 변수로 받는다
        //setContentView(v); //View 객체를 내용물로 삼는다

        //setContentView(new AndroidImgView(this)); //AndroidImgView 객체를 하나 생성해서 액티비티의 내용으로 채우라는 형태로 구성

        setContentView(new TouchView(this)); //TouchView는 객체 생성 시 액티비티에 대한 참조 값을 전달해 줘야 한다
    }
}
