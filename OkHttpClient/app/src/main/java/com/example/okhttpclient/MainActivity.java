package com.example.okhttpclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText input01;
    TextView txtMsg;

    public static String defaultUrl = "https://goo.gl/eIXu9l"; //가상의 날씨 정보를 제이슨 형태로 제공받을 수 있는 URL에 대한 단축 URL

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input01 = (EditText) findViewById(R.id.input01);
        input01.setText(defaultUrl);

        txtMsg = (TextView) findViewById(R.id.txtMsg);

        // 버튼 이벤트 처리
        Button requestBtn = (Button) findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String urlStr = input01.getText().toString();

                ConnectThread thread = new ConnectThread(urlStr);
                thread.start();
            }
        });
    }

    // 소켓 연결할 스레드 정의
    class ConnectThread extends Thread {
        OkHttpClient client = new OkHttpClient(); //OkHttpClient, Request, Response는 okhttp 라이브러리에서 제공하는 클래스
        String urlStr;

        public ConnectThread(String inStr) {
            urlStr = inStr;
        }

        public void run() {
            try {
                // 요청
                Request request = new Request.Builder()
                        .url(urlStr)
                        .build();

                // 응답
                Response response = client.newCall(request).execute(); //newCall 메소드를 통해서 새로운 요청을 보낸다
                final String output = response.body().string();
                handler.post(new Runnable() { //전통적인 자바의 Http 통신 방법과 비교해 보면, 이 방법은 추가의 프로그래밍을 해 주지 않아도 된다. //어떻게 요청을 보내고, 보낸 요청에 대해서 어떻게 응답을 받을지에 대한 것들을 직접 프로그래밍하지 않고 몇 줄의 코드로 간단하게 처리할 수 있다.
                    public void run() {
                        txtMsg.setText(output);
                    }
                });
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
