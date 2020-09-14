package com.example.resourcetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.drawable_test);
        setContentView(R.layout.string_test);

        View v = findViewById(R.id.main);
        registerForContextMenu(v); //메뉴만 정의해 놓는 게 아니라 ContextMenu를 등록해 주는 일이 필요 //어떤 뷰의 ContextMenu를 부착하도록 할 것인지를 따로 지정

        /*
        BitmapDrawable sampleImg = (BitmapDrawable) getResources().getDrawable(R.drawable.image_sample);

        ImageView imgView = (ImageView) findViewById(R.id.img_sample);
        //imgView.setImageDrawable(sampleImg);
        imgView.setImageResource(R.drawable.image_sample);
        */

        /*
        TextView tv2 = (TextView) findViewById(R.id.textView2); //textView2라는 id를 가지고 있는 뷰에 대한 참조를 반환 //자바 버전에 따라 다운캐스팅을 반드시 명시적으로 해 줘야 하는 부분들도 있다

        //String name = getResources().getString(R.string.my_name); //리소스 객체로부터 하나의 문자열을 얻어온다
        //tv2.setText(name);

        tv2.setText(R.string.my_name);
        */
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int color = Color.WHITE;

        switch(item.getItemId()) {
            case R.id.menu_red:
                color = Color.RED; break;

            case R.id.menu_green:
                color = Color.GREEN; break;

            case R.id.menu_blue:
                color = Color.BLUE; break;
        }

        View v = findViewById(R.id.main);
        v.setBackgroundColor(color);

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
        MenuItem item1 = menu.add(0, 0, 0, "슬라이드쇼");
        MenuItem item2 = menu.add(0, 1, 0, "앨범에 추가");
        return true;
        */

//        super.onCreateOptionsMenu(menu); //부모 클래스의 onCreateOptionsMenu를 먼저 호출해서, 부모에서 정의된 부분들을 현재 단계에서 적용 //MainActivity 상위 클래스에선 정의한 내용이 없기 때문에 현재 구성에서는 써 줘도 그만, 안 써 줘도 그만

        MenuInflater inflater = this.getMenuInflater(); //메뉴 전개하는 전개자 객체 얻음
        inflater.inflate(R.menu.option_menu, menu); //전개하는 객체를 전개하는 inflate 메소드 //xml 문서 내용을 수동으로 전개 //전개된 내용을 현재 매개변수로 전달받은 menu에 설정 //이전에는 여기에다가 직접 메뉴 아이템 객체들을 따로 추가해 줬다면 이제는 xml 문서에서 정의한 내용으로 간단하게 정의할 수 있다

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int color = Color.WHITE;

        switch (item.getItemId()) {
            /*
            case 0: break;

            case 1: break;
            */

            case R.id.menu_red:
                color = Color.RED; break;

            case R.id.menu_green:
                color = Color.GREEN; break;

            case R.id.menu_blue:
                color = Color.BLUE; break;
        }

        View v = findViewById(R.id.main);
        v.setBackgroundColor(color);

        return super.onOptionsItemSelected(item); //부모 레벨에서 메뉴에 대한 이벤트 처리가 추가적으로 필요할 수 있기 때문에 재정의 되기 전에 onOptionsItemSelected를 호출해서 이 안에서 어떤 처리를 하도록 한 것 //지금처럼 구성하지 않았으면 그냥 return true; 해도 상관 없을 것이다
    }
}
