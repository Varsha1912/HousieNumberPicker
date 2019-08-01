package com.example.crdr.layouttestingapplication;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class MainActivity extends AppCompatActivity {

    Button loginBtn1;

    private static final String SHOWCASE_ID="simple showcase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn1=findViewById(R.id.button);



        showTutor(500);
        //startActivity(new Intent(MainActivity.this, TimerActivity.class));
        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(MainActivity.this, LoginActivity.class));

                //startActivity(new Intent(MainActivity.this, ImageConversionActivity.class));

               /* startActivity(new Intent(MainActivity.this, TimerActivity.class));*/
               startActivity(new Intent(MainActivity.this, HousieActivity.class));


            }
        });

    }


    private void showTutor(int millis)
    {
        new MaterialShowcaseView.Builder(this).
                setTarget(loginBtn1)
                .setTitleText("Single")
                .setDismissText("Got It")
                .setContentText("Click on this button to proceed")
                .setDelay(millis)
                .singleUse(SHOWCASE_ID)
                .show();
    }
}
