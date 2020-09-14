package com.example.widgettest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview_test); //edittext_test); //textview_test);
    }
/*
    public void onBtnClicked(View v) {
        EditText et = findViewById(R.id.editText1);
        TextView tv = findViewById(R.id.textView1);

        String str = String.valueOf(et.getText()); //et.getText().toString();
        tv.setText(str);
    }
*/
}
