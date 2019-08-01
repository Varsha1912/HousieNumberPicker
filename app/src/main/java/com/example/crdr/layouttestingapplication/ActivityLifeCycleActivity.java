package com.example.crdr.layouttestingapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityLifeCycleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Log.e("in", "onCreate");
        Button button =findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLifeCycleActivity.this,HousieActivity.class));
            }
        });
       // finish();

    }

    public ActivityLifeCycleActivity() {
        super();
        Log.e("in", "constructor deafult");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("in", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("in", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("in", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("in", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("in", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("in", "onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("in", "onBackPressed");
        finish();
    }
}
