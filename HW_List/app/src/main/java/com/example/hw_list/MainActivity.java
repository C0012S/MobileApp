package com.example.hw_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> mData = null;
    ListView mListView = null;
    BaseAdapterEx mAdapter = null;

    EditText nameEt;
    EditText numberEt;
    EditText departmentEt;
    EditText itemEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        nameEt = (EditText) findViewById(R.id.et_name);
        numberEt = (EditText) findViewById(R.id.et_number);
        departmentEt = (EditText) findViewById(R.id.et_dept);
        itemEt = (EditText) findViewById(R.id.et_del_item_index);

        // 1. 어댑터에서 사용할 데이터 설정
        mData = new ArrayList<Student>();

        for (int i = 0; i < 100; i++) {
            Student student = new Student();

            student.mName = "슈퍼성근" + i;
            student.mNumber = "95000" + i;
            student.mDepartment = "컴퓨터 공학" + i;

            mData.add(student);
        }

        // 2. 어댑터를 생성하고 데이터 설정
        mAdapter = new BaseAdapterEx(this, mData);

        // 3. 리스트뷰에 어댑터 설정
        mListView = (ListView) findViewById(R.id.lt_listview);
        mListView.setAdapter(mAdapter);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.b_add:
                Student addData = new Student();

                addData.mName = nameEt.getText().toString();
                addData.mNumber = numberEt.getText().toString();
                addData.mDepartment = departmentEt.getText().toString();

                if (addData.mName.length() != 0 && addData.mNumber.length() != 0 && addData.mDepartment.length() != 0) {
                    nameEt.setText("");
                    numberEt.setText("");
                    departmentEt.setText("");

                    mAdapter.add(0, addData);
                }
                break;

            case R.id.b_del:
                int num = Integer.parseInt(itemEt.getText().toString());

                if (mData != null) {
                    itemEt.setText("");
                    mAdapter.delete(num);
                }
                break;

            case R.id.b_all_del:
                if (mData != null) {
                    mAdapter.clear();
                }
                break;
        }
    }
}
