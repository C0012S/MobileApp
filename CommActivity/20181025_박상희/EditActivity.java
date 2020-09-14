package com.example.commactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mEdit = (EditText) findViewById(R.id.et_edit);

        Intent intent = getIntent();
        String text = intent.getStringExtra("TextIn");
        if (text != null) {
            mEdit.setText(text);
        }
    }

    public void onBClick(View v) {
        switch (v.getId()) {
            case R.id.b_ok:
                Intent intent = new Intent();
                intent.putExtra("TextOut", mEdit.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.b_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
