package com.example.commactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mText;

    final static int ACTIVITY_EDIT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.t_display);
    }

    public void onBClick(View v) {
        switch (v.getId()) {
            case R.id.b_edit:
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("TextIn", mText.getText().toString());
                startActivityForResult(intent, ACTIVITY_EDIT);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_EDIT:
                if (resultCode == RESULT_OK) {
                    mText.setText(data.getStringExtra("TextOut"));
                }
                break;
        }
    }
}
