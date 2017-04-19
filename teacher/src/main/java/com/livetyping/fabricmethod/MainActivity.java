package com.livetyping.fabricmethod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.livetyping.core.CoreMainActivity;

public class MainActivity extends CoreMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getTextViewId() {
        return R.id.teacher_main_activity_text_view;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
