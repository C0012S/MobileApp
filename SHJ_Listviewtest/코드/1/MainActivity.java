package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private BaseAdapterEx mAdapter;
    private ArrayList<Student> mData;
    private EditText nameEt = (EditText)findViewById(R.id.et_name);
    private EditText numberEt = (EditText)findViewById(R.id.et_number);
    private EditText departmentEt = (EditText)findViewById(R.id.et_dept);
    private EditText delitemindexEt = (EditText)findViewById(R.id.et_del_item_index);

    public class Student{
        String mName = ""; //이름
        String mNumber = ""; //학번
        String mDepartment =""; //학과
    }

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
                Student addData = new Student();

                addData.mName = nameEt.getText().toString();
                addData.mNumber = numberEt.getText().toString();
                addData.mDepartment = departmentEt.getText().toString();

                mAdapter.add(0,addData);

                break;

            case R.id.b_del:
                String text = delitemindexEt.getText().toString();
                if(text.length()!=0) {
                    for(int i = 0;i < mData.size(); i++)
                    {
                        //student의 모든 데이터에 입력받은 단어(text)가 포함되어 있으면 true를 반환한다.
                        if (mData.get(i).equals(text))
                        {
                            // 검색된 데이터를 리스트에 삭제한다.
                            mAdapter.delete(mData.indexOf(i));
                        }
                    }
                }
                break;
            case R.id.b_all_del:
                mAdapter.clear();

        }
    }

}


