package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mList;
    private EditText mInput;
    private ArrayAdapter<String> mAdapter;
    private ListView mListView;

    ArrayList<Student> mData = null;
    ListView mListView2 = null;
    BaseAdapterEx mAdapter2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //기본적으로 전개하고, 초기화하는 함수 호출
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.listview_test1);
        setContentView(R.layout.list_view_practice);

    //    mInit(); //초기화하는 작업들을 수행하는 사용자 정의 멤버 함수

        mInput = findViewById(R.id.et_newitem);

        // 어댑터에서 사용할 데이터 설정
        mData = new ArrayList<Student>();

        for (int i = 0; i < 10; i++) {
            Student student = new Student();

            student.mName = "슈퍼성근" + i;
            student.mNumber = "95000" + i;
            student.mDepartment = "컴퓨터 공학" + i;

            mData.add(student);
        }

        // 2. 어댑터를 생성하고 데이터 설정
        mAdapter2 = new BaseAdapterEx(this, mData);

        // 3. 리스트뷰에 어댑터 설정
        mListView2 = (ListView) findViewById(R.id.lt_listview);
        mListView2.setAdapter(mAdapter2);
    }

    private void mInit() { //각각의 함수들을 구조적으로 쪼개보자 <- 모듈화해서 구성...하는 것들은 소스 코드를 가독성 있게 유지할 수 있어서 바람직한 선택이 될 수 있다
        /*
        // 데이터 준비
        mList = new ArrayList<String>();

        mList.add("Apple Pie"); //ArrayList 상에 표시된 내용이 ListView 상에 적절히 출력된다
        mList.add("Banana Bread"); //각각의 항목들은 독립적으로 선택 가능하다
        mList.add("Cupcake");
        mList.add("Donut");
        mList.add("Eclair");
        mList.add("Froyo");
        mList.add("Gingerbread");
        mList.add("Honeycomb");
        mList.add("Icecream sandwich");
        mList.add("Jellly Bean");
        mList.add("KitKat");
        mList.add("Lollipop");
        mList.add("Marshmallow");
        mList.add("Nougat");
        mList.add("Oreo");
        mList.add("Pie");
        */
        mInitData(); //데이터를 초기화
/*
        //데이터를 ListView 상에 출력하는 데 중개 역할을 할 수 있는 Adapter 객체 필요
        // 어댑터 준비
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( //mList 각각의 항목을 이런 형태로 출력할 수 있는 Adapter 객체
                this,
                //android.R.layout.simple_list_item_1, //두 번째 인수는 어떻게 보일 것인지를 지정한다
                //android.R.layout.simple_list_item_single_choice, //항목 선택 모드 때 변경
                //android.R.layout.simple_list_item_checked, //라디오버튼 대신 체크 표시가 붙는다 //단일 선택을 하든, 다중 선택을 하든 가장 깔끔하게 표현된다
                //android.R.layout.simple_list_item_single_choice, //ChildView의 내용들을 바꿔 주기 위해서... //라디오 버튼이 나오지만 Choice mode가 MULTIPLE이기 때문에 라디오 버튼이더라도 다중 선택이 가능하다
                android.R.layout.simple_list_item_multiple_choice, //체크박스처럼 체크 표시가 붙는 체크박스로 나타난다
                mList
        );

        // 리스트뷰와 어댑터를 연결
        ListView lv = findViewById(R.id.listview); //이벤트 소스는 ListView가 된다 <- ListView의 특정 항목을 선택했을 때 이벤트 발생
        lv.setAdapter(adapter);

        // 이벤트 연결
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //position 값을 이용해서 몇 번째 항목이 선택되었는지를 판단한다
                String msg = "Select Item = " + mList.get(position);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        // 항목 선택 모드 //항목 선택 모드가 적용되기 위해선, ListView 상에 표시되는 것이 체크박스나 라디오버튼을 갖고 있는 경우에만 적용
        //lv.setChoiceMode(ListView.CHOICE_MODE_NONE); //setChoiceMode 설정 안 했을 때와 같이 선택이 일어나지 않는 것으로 동작
        //lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //선택된 항목에 대해서 라디오버튼이 체크가 활성화된다 //배타적인 선택으로, 최종 하나만 선택되어지는 모드로 동작된다
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); //다중 선택 가능 //ListView의 setChoiceMode는 어떤 선택을 할 것인지를 지정한다

        //lv.setDividerHeight(10);
*/
        mInitAdapterView(); //어댑터 뷰를 초기화
    }

    private void mInitData() { //데이터를 초기화
        // 데이터 준비
        mList = new ArrayList<String>();

        mList.add("Apple Pie");
        mList.add("Banana Bread");
        mList.add("Cupcake");
        mList.add("Donut");
        mList.add("Eclair");
        mList.add("Froyo");
        mList.add("Gingerbread");
        mList.add("Honeycomb");
        mList.add("Icecream sandwich");
        mList.add("Jellly Bean");
        mList.add("KitKat");
        mList.add("Lollipop");
        mList.add("Marshmallow");
        mList.add("Nougat");
        mList.add("Oreo");
        mList.add("Pie");
    }

    private void mInitAdapterView() {
        // 어댑터 준비
        /*ArrayAdapter<String> adapter*/ mAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_checked, //체크 표시로 표시될 수 있도록 구성
                mList
        );

        // 리스트뷰와 어댑터를 연결
        /*ListView lv*/ mListView = findViewById(R.id.listview);
        /*lv*/ mListView.setAdapter(/*adapter*/ mAdapter);

        /*
        // 이벤트 연결
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = "Select Item = " + mList.get(position);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        */

        // 항목 선택 모드
        //lv.setChoiceMode(ListView.CHOICE_MODE_NONE);
        /*lv*/ mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //단일 선택
        //lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); //다중 선택

        //lv.setDividerHeight(10);

    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.b_add:
                String text = mInput.getText().toString();
                if (text.length() != 0) {
                    mList.add(text);
                    mInput.setText("");
                    mAdapter.notifyDataSetChanged();
                }
                break;

            case R.id.b_delete:
                int pos = mListView.getCheckedItemPosition();
                if (pos != ListView.INVALID_POSITION) { //삭제 버튼이 눌렸을 때 체크된 항목이 없을 수도 있기 때문에 확인 필요
                    mList.remove(pos);
                    mListView.clearChoices(); //선택된 항목 지운다
                    mAdapter.notifyDataSetChanged(); //갱신
                }
                break;
        }
    }
}
