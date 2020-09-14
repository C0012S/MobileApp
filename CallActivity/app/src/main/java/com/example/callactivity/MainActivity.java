package com.example.callactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mOnClick(View v) {
        //Intent intent = new Intent(this, SubActivity.class);
        //startActivity(intent);

        Intent intent = new Intent();
        intent.setAction("com.example.ACTION_VIEW");
        startActivity(intent);
    }
}
