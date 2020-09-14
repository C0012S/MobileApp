package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button start_btn;
    private Button pause_btn;
    private Button reset_btn;

    private Thread mThread;

    private TextView ms_tv;
    private TextView ss_tv;
    private TextView mm_tv;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch_test);

        start_btn = (Button) findViewById(R.id.btn_start);
        pause_btn = (Button) findViewById(R.id.btn_pause);
        reset_btn = (Button) findViewById(R.id.btn_reset);

        ms_tv = (TextView) findViewById(R.id.tv_ms);
        ss_tv = (TextView) findViewById(R.id.tv_ss);
        mm_tv = (TextView) findViewById(R.id.tv_mm);

        pause_btn.setEnabled(false);
        reset_btn.setEnabled(false);
    }

    public void StartClick(View v) {
        start_btn.setEnabled(false);
        pause_btn.setEnabled(true);
        reset_btn.setEnabled(false);

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (mThread == Thread.currentThread()) {
                    Runnable callback = new Runnable() {
                        @Override
                        public void run() {
                            ms_tv.setText(String.format("%01d", Integer.parseInt(ms_tv.getText().toString()) + 1));

                            if (Integer.parseInt((String) ms_tv.getText()) > 9) {
                                ms_tv.setText("0");
                                ss_tv.setText(String.format("%02d", Integer.parseInt(ss_tv.getText().toString()) + 1));

                                if (Integer.parseInt((String) ss_tv.getText()) > 59) {
                                    ss_tv.setText("00");
                                    mm_tv.setText(String.format("%02d", Integer.parseInt(mm_tv.getText().toString()) + 1));
                                }
                            }
                        }
                    };

                    Message msg = Message.obtain(mHandler, callback);

                    mHandler.sendMessage(msg);

                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {    }
                }
            }
        });

        mThread.start();

    }

    public void PauseClick(View v) {
        start_btn.setEnabled(true);
        pause_btn.setEnabled(false);
        reset_btn.setEnabled(true);

        if (mThread != Thread.currentThread()) {
            mThread = null;
        }
    }

    public void ResetClick(View v) {
        start_btn.setEnabled(true);
        pause_btn.setEnabled(false);
        reset_btn.setEnabled(false);

        ms_tv.setText("0");
        ss_tv.setText("00");
        mm_tv.setText("00");
    }
}
