package com.example.variousevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
  /*
    private TextView mTv; //멤버 변수 //m은 내가 만든 사용자 정의 멤버
  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //액티비티가 처음 생성되면서, 화면을 구성하면서, 이 구문이 처리가 될 것이다
        setContentView(R.layout.echo);

        EditText et = findViewById(R.id.et_input); //이벤트 소스
        final TextView tv = findViewById(R.id.tv_disp); //final 키워드로 상수화 시켜서 함수가 끝나도 지속적으로 남아 있을 수 있게 함

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { //Text가 입력이 일어나기 전

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { //입력된 Text가 있을 때 일을 하면 된다
                tv.setText("echo: " + s); //TextView가 지역 변수라서 오류가 나고 있다 -> final 키워드로 상수화
            }

            @Override
            public void afterTextChanged(Editable s) { //Text가 입력이 일어난 후

            }
        });
  /*
        setContentView(R.layout.counter2); //onClick 속성 사용 //setContentView(R.layout.counter); //리스너 사용

        //final TextView tv = (TextView) findViewById(R.id.tvNum); //다운캐스팅
        mTv = (TextView) findViewById(R.id.tvNum);
        Button bDec = (Button) findViewById(R.id.bDec);
        Button bInc = (Button) findViewById(R.id.bInc);
  */

/*  리스너 연결해서 사용
        Button.OnClickListener listener = new Button.OnClickListener() {
            //버튼들이 눌렸을 때 뭐 할 건지에 대한 이벤트 리스너 연결하는 작업 수행 //숫자 값을 증가, 감소한다
            @Override //onClick 메소드는 onCreate 함수가 호출되는 동안 수행되는 함수가 아니다 //리스너 객체를 만들면서, 리스너 객체가 버튼의 클릭 이벤트를 받으면 이런 동작을 하라는 미리 예약해 놓는 작업 여기서 함수를 호출하고 있는 구문이 아니다
            public void onClick(View v) { //이벤트 소스를 인자로 전달받는다 (어디서 전달받은 건지 식별을 위해서)
                //String num = tv.getText().toString(); //TextView 값을 가져 온다 //onCreate의 지역 변수 TextView는 남아 있지 않아서 오류가 난다 -> 지역 변수에 접근할 것이라면, 지역 변수의 생존 시기 때문에 앞에 final을 붙여 상수화 해 준다
                int num = Integer.parseInt(tv.getText().toString()); //문자열로부터 숫자를 뽑아내는 작업 //함수 안에서 지역적으로 받았다

                switch (v.getId()) { //리소스에 대한 아이디 조회
                    //TextView 값을 바꿔 주는 일을 한다
                    case R.id.bDec:
                        num--; //--num도 가능 //얘가 바뀌었다고 TextView 값이 변하는 건 아니다
                        break;

                    case R.id.bInc:
                        num++; //++num도 가능 //어차피 단독으로 사용하는 것은 증감 연산자의 위치가 중요하지 않다
                        break;
                }

                tv.setText(String.valueOf(num)); //정식의 방법 -> String 클래스의 valueOf 함수를 통해 다양한 종류의 자료형들의 데이터를 문자열로 바꿔 주는 일을 수행 //tv.setText("" + num); //꼼수
            }
        };
        //각각의 버튼마다 별도로 리스너를 만들 수 있지만 그럴 필요 없이 공통된 부분들을 하나로 처리하기 위해서 하나의 리스너 객체를 만들어서 지정
        bDec.setOnClickListener(listener); //listener 참조 변수는 지역적으로 선언했기 때문에 listener 변수를 선언한 다음에 사용해 줘야 한다 멤버 변수 영역에 두었다면 어디에 나오든 상관 없다
        bInc.setOnClickListener(listener); //하나의 리스너가 두 개의 버튼에 적용
*/

  /*
        Button.OnLongClickListener listener = new Button.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int num = Integer.parseInt(mTv.getText().toString());

                switch (v.getId()) {
                    case R.id.bDec:
                        num -= 10;
                        break;

                    case R.id.bInc:
                        num += 10;
                        break;
                }

                mTv.setText(String.valueOf(num));

                return true; //버튼 꾹 누르다가 떼면 그대로다
                //return false; //(+)버튼 꾹 누르다가 떼면 1이 또 증가된다 //(+)버튼을 꾹 누르는 순간 LongClick 이벤트 발생되어 10 만큼 증가되고, return false 하면 이벤트 처리가 다 안 끝난 것으로 간주되어서 클릭 이벤트가 한 번 더 처리되어진다
            }
        };

        bDec.setOnLongClickListener(listener);
        bInc.setOnLongClickListener(listener);
  */
    } //실제 이 동작은 언제 일어나나면, onCreate 함수가 끝나고 나서 사용자가 화면에 있는 버튼을 눌렀을 때 이벤트를 전달받아 이 객체의 메소드가 호출되는 것이다
  /*
    public void mBtnClicked(View v) {
        int num = Integer.parseInt(mTv.getText().toString()); //TextView는 onCreate의 지역 변수로 만들었기 때문에 btnClicked 함수 안에서는 접근 불가능
        //-> TextView에 대한 참조를 받아오는 일을 여기서 해 주거나, TextView에 대한 참조 변수를 멤버 변수로 둔다
        switch (v.getId()) {

            case R.id.bDec:
                num--;
                break;

            case R.id.bInc:
                num++;
                break;
        }

        mTv.setText(String.valueOf(num));
    }
  */
}
