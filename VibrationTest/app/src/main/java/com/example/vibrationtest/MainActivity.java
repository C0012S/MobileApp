package com.example.vibrationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;
    private Button mBtn;

    //private boolean mCont;
    private Thread mThread;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vibration_test);
    }

    public void mOnClick(View v) {
        String txt = mBtn.getText().toString();

        if (txt.equals("진동시작")) {
            mBtn.setText("멈춤");
/* 이 부분을 스레드를 통해서 처리하도록 구성
            int offset = 10;
            mCont = true;

            while (mCont) {
                Log.v("MainActivity", "" + mTv.getX());

                mTv.setX(mTv.getX() + offset);

                offset = -offset;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
*/
            // 2. 스레드 생성 및 시작
            mThread = new Thread(new Runnable() {
                int offset = 10;

                @Override
                public void run() {
                    while (Thread.currentThread() == mThread) { //나에 대한 참조를 얻어와서, mThread라는 메인 스레드에서 일을 시킨 스레드에 대한 참조 값과 같은지를 비교해 줌으로써 주어진 작업들을 수행할지 말지 결정할 수 있도록 구성
/*                        Log.v("MainActivity", "" + mTv.getX());

                        mTv.setX(mTv.getX() + offset);
*/

                        // 3. 실행 코드가 담긴 Runnable 객체 생성
                        Runnable callback = new Runnable() {
                            public void run() {
                                //Log.v("MainActivity", "" + mTv.getX());

                                mTv.setX(mTv.getX() + offset);
                            }
                        };

                        // 4. 메시지 객체 생성, 생성시 Runnable 객체를 인수로 전달
                        Message msg = Message.obtain(mHandler, callback);

                        // 5. 핸들러를 통해 메시지를 메시지 큐로 보냄
                        mHandler.sendMessage(msg);

                        offset = -offset;

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {}
                    }
                }
            });
            mThread.start(); //스레드 구동
        }
        else {
            mBtn.setText("진동시작");

            mThread = null; //스레드를 중지시키기 위해 지연 취소라는 방법을 이용 //스레드에 대한 참조 변수에 null 값을 넣어 줌으로써, 스레드가 스스로 run 메소드를 끝날 수 있도록 처리해 보자
        }
    }
}
