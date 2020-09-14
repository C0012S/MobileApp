package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListView = null;
    private BaseAdapterEx mAdapter = null;
    private ArrayList<Student> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        mStudent(); //데이터 생성 메서드 호출

        makeAdapter(); //어댑터 생성 메서드 호츌

        msetAdapter(); //리스트뷰에 어댑터 설정 메서드 호출
    }


    private void mStudent(){ //어댑터에서 사용할 데이터 설정
        mData = new ArrayList<Student>();

        for(int i=0; i<100; i++) {
            Student student = new Student();
            student.mName = "슈퍼성근" + i;
            student.mNumber = "95000" + i;
            student.mDepartment = "컴퓨터 공학" + i;

            mData.add(student);
        }
    }

    private void makeAdapter(){ //어댑터를 생성하고 데이터 설정
        mAdapter = new BaseAdapterEx(this, mData);
    }

    private void msetAdapter(){ //리스트뷰에 어댑터 설정
        mListView = (ListView)findViewById(R.id.lt_listview);
        mListView.setAdapter(mAdapter);
    }

    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.b_add:
                EditText nameEt = (EditText)findViewById(R.id.et_name);
                EditText numberEt = (EditText)findViewById(R.id.et_number);
                EditText departmentEt = (EditText)findViewById(R.id.et_dept);

                Student addData = new Student();

                addData.mName = nameEt.getText().toString();
                addData.mNumber = numberEt.getText().toString();
                addData.mDepartment = departmentEt.getText().toString();

                mAdapter.add(0,addData);

                break;

            case R.id.b_del:
                EditText indexEt = (EditText)findViewById(R.id.et_del_item_index);
                int index = Integer.parseInt(indexEt.getText().toString());
                if(indexEt.getText().length()!=0) {
                    mAdapter.delete(index);
                }
                break;

            case R.id.b_all_del:
                mAdapter.clear();
                break;
        }
    }

}


