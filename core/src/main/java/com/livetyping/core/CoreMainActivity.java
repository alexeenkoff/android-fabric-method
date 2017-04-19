package com.livetyping.core;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.livetyping.core.notification.CoreNotification;

public abstract class CoreMainActivity extends AppCompatActivity {

    private TextView mainTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mainTextView = ((TextView) findViewById(getTextViewId()));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey(CoreNotification.KEY_FROM_PUSH)) {
                mainTextView.setText(extras.getString(CoreNotification.KEY_FROM_PUSH));
            }
        }
    }

    protected abstract
    @IdRes
    int getTextViewId();


    protected abstract
    @LayoutRes
    int getLayoutResId();
}
