package com.example.android.bluetoothchat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class StartPageActivity extends Activity {
    private CheckBox checkBox;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        button = (Button) findViewById(R.id.enter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = checkBox.isChecked();
                //页面跳转逻辑
                Intent intent = new Intent();
                if (isChecked) {
                    //设置跳转到管理员页(待改)
                    //intent.setClass(StartPageActivity.this, Sup.class);
                } else {
                    //设置跳转到学生页(待改)
                    intent.setClass(StartPageActivity.this, MainActivity.class);
                }
                startActivity(intent);
                if (isChecked)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                else
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        //设置勾选框监视
        checkBox = findViewById(R.id.isSupervisor);
        checkBox.setOnCheckedChangeListener(new CheckListener());

        //设置SharedPreferences
        preferences = getSharedPreferences("data", MODE_PRIVATE);

        //恢复勾选状态(默认不勾选)
        checkBox.setChecked(preferences.getBoolean("isSupervisor", false));
    }

    private class CheckListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            //防误触提醒
            if (b)
                Toast.makeText(StartPageActivity.this,
                        "Please check carefully~", Toast.LENGTH_SHORT).show();

            //记录选择情况
            editor = preferences.edit();
            editor.putBoolean("isSupervisor", b);
            editor.apply();
        }
    }
}
