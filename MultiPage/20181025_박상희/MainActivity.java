package com.example.multipage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    private View page1, page2, page3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        ViewGroup frame = findViewById(R.id.id_frame);

        LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.page1, null);
        GridLayout grid = (GridLayout) View.inflate(this, R.layout.page2, null);
        TableLayout table = (TableLayout) View.inflate(this, R.layout.page3, null);

        frame.addView(linear);
        frame.addView(grid);
        frame.addView(table);

        page1 = findViewById(R.id.l_page1);
        page2 = findViewById(R.id.l_page2);
        page3 = findViewById(R.id.l_page3);

        page2.setVisibility(View.INVISIBLE);
        page3.setVisibility(View.INVISIBLE);
    }

    public void onClicked(View v) {
        switch (v.getId()) {
            case R.id.b_page1:
                page1.setVisibility(View.VISIBLE);
                page2.setVisibility(View.INVISIBLE);
                page3.setVisibility(View.INVISIBLE);
                break;

            case R.id.b_page2:
                page1.setVisibility(View.INVISIBLE);
                page2.setVisibility(View.VISIBLE);
                page3.setVisibility(View.INVISIBLE);
                break;

            case R.id.b_page3:
                page1.setVisibility(View.INVISIBLE);
                page2.setVisibility(View.INVISIBLE);
                page3.setVisibility(View.VISIBLE);
                break;
        }
    }
}
