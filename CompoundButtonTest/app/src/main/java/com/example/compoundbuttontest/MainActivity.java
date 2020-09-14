package com.example.compoundbuttontest;

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
        setContentView(R.layout.compoundbutton_test);

        mConfig = (LinearLayout) findViewById(R.id.config);

        mMsg = (TextView) findViewById(R.id.tv_msg);
        mMsg.setTextSize(20);

        RadioGroup.OnCheckedChangeListener mRadioCheck = new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getId() == R.id.rbg_colorGroup) {
                    switch (checkedId) {
                        case R.id.rb_red:
                            mMsg.setTextColor(Color.RED);
                            break;

                        case R.id.rb_green:
                            mMsg.setTextColor(Color.GREEN);
                            break;

                        case R.id.rb_blue:
                            mMsg.setTextColor(Color.BLUE);
                            break;
                    }
                }
            }
        };

        CompoundButton.OnCheckedChangeListener mCheckChange = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.cb_bigFont) {
                    if (isChecked) {
                        mMsg.setTextSize(40);
                    }

                    else {
                        mMsg.setTextSize(20);
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
