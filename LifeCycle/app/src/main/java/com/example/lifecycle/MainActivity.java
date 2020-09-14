package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.v("MainActivity", "verbose");
        //Log.i("MainActivity", "info");
        Log.v("MainActivity", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("MainActivity", "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("MainActivity", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("MainActivity", "onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("MainActivity", "onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("MainActivity", "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("MainActivity", "onRestart()");
    }
}
