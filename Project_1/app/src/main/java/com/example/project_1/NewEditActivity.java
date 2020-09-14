package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewEditActivity extends AppCompatActivity {

    EditText ed_site;
    EditText ed_url;
    EditText ed_user;
    EditText ed_pwd;
    EditText ed_pwd_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edit);

        ed_site = (EditText) findViewById(R.id.et_sitename);
        ed_url = (EditText) findViewById(R.id.et_url);
        ed_user = (EditText) findViewById(R.id.et_user);
        ed_pwd = (EditText) findViewById(R.id.et_pwd);
        ed_pwd_check = (EditText) findViewById(R.id.et_pwd_check);

        Intent intent = getIntent();

        ed_site.setText(intent.getStringExtra("sitename"));
        ed_url.setText(intent.getStringExtra("url"));
        ed_user.setText(intent.getStringExtra("user"));
    }

    public void onBClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel: // 취소
                setResult(RESULT_CANCELED);
                finish();
                break;

            case R.id.btn_add: // 등록
                Intent intent = new Intent();

                // 정보를 다 입력해야만 등록 가능
                if ((ed_site.getText().toString().length() > 0) && (ed_url.getText().toString().length() > 0) && (ed_user.getText().toString().length() > 0) && (ed_pwd.getText().toString().length() > 0) && (ed_pwd_check.getText().toString().length() > 0)) {
                    if (ed_pwd.getText().toString().equals(ed_pwd_check.getText().toString())) { // 비밀번호와 비밀번호 확인이 같아야만 등록 가능
                        intent.putExtra("new_sitename", ed_site.getText().toString());
                        intent.putExtra("new_url", ed_url.getText().toString());
                        intent.putExtra("new_user", ed_user.getText().toString());
                        intent.putExtra("new_pwd", ed_pwd.getText().toString());
                        intent.putExtra("new_pwd_check", ed_pwd_check.getText().toString());

                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    else {
                        Toast.makeText(this.getApplicationContext(), "비밀번호가 틀렸습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    }
                }

                else {
                    Toast.makeText(this.getApplicationContext(), "정보를 다 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
