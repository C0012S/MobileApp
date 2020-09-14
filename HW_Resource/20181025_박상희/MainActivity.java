package com.example.hw_resource;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView mMsg;
    LinearLayout mConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.re_compoundbutton_test);

        mConfig = (LinearLayout) findViewById(R.id.config);

        mMsg = (TextView) findViewById(R.id.tv_msg);
        mMsg.setTextSize(20);

        RadioGroup.OnCheckedChangeListener mRadioCheck = new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int color_red = getResources().getColor(R.color.Red);
                int color_green = getResources().getColor(R.color.Green);
                int color_blue = getResources().getColor(R.color.Blue);

                if (group.getId() == R.id.rbg_colorGroup) {
                    switch (checkedId) {
                        case R.id.rb_red:
                            mMsg.setTextColor(color_red);
                            break;

                        case R.id.rb_green:
                            mMsg.setTextColor(color_green);
                            break;

                        case R.id.rb_blue:
                            mMsg.setTextColor(color_blue);
                            break;
                    }
                }
            }
        };

        CompoundButton.OnCheckedChangeListener mCheckChange = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int dimen1 = getResources().getDimensionPixelSize(R.dimen.my_name_dimen1);
                int dimen2 = getResources().getDimensionPixelSize(R.dimen.my_name_dimen2);

                if (buttonView.getId() == R.id.cb_bigFont) {
                    if (isChecked) {
                        mMsg.setTextSize(dimen2);
                    }

                    else {
                        mMsg.setTextSize(dimen1);
                    }
                }
            }
        };

        CompoundButton.OnCheckedChangeListener mToggleChange = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.tb_active) {
                    if (isChecked) {
                        mConfig.setVisibility(View.VISIBLE);
                    }
                    else {
                        mConfig.setVisibility(View.GONE);
                    }
                }
            }
        };

        RadioGroup colorGroup = (RadioGroup) findViewById(R.id.rbg_colorGroup);
        colorGroup.setOnCheckedChangeListener(mRadioCheck);

        CheckBox b_bigFont = (CheckBox) findViewById(R.id.cb_bigFont);
        b_bigFont.setOnCheckedChangeListener(mCheckChange);

        ToggleButton b_active = (ToggleButton) findViewById(R.id.tb_active);
        b_active.setOnCheckedChangeListener(mToggleChange);
    }
}

