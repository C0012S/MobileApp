package com.example.hw1_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw1_main);
    }

    public void BtnClicked(View v) {
        EditText et1 = findViewById(R.id.editText);
        EditText et2 = findViewById(R.id.editText2);

        String str1 = String.valueOf(et1.getText());
        String str2 = String.valueOf(et2.getText());

        et1.setText("");
        et2.setText("");

        Toast.makeText(this, str1 + " " + str2, Toast.LENGTH_LONG).show();
    }
}
