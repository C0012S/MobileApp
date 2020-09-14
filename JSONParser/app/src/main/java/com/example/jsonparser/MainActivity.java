package com.example.jsonparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText input01;
    TextView txtMsg;

    public static String defaultUrl = "https://goo.gl/eIXu9l";

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_main);

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
        OkHttpClient client = new OkHttpClient();
        String urlStr;
        List<Weather> weatherList = new ArrayList<Weather>();

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
                Response response = client.newCall(request).execute();

                Gson gson = new Gson();

                // import java.lang.reflect.Type
                Type listType = new TypeToken<ArrayList<Weather>>() {}.getType();
                weatherList = gson.fromJson(response.body().string(), listType);

                Log.d("MainActivity", "onCreate: " + weatherList.toString());

                handler.post(new Runnable() {
                    public void run() {
                        //WeatherAdapter adapter = new WeatherAdapter(weatherList);
                        //mWeatherListView.setAdapter(adapter);
                        txtMsg.setText(weatherList.toString()); //ArrayList를 문자열로 변환해서 TextView 상에 출력
                    }
                });
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
