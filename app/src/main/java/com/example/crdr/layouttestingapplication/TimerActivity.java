package com.example.crdr.layouttestingapplication;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimerActivity extends AppCompatActivity {

    TextView tvTimer;
    Button btnStart,btnStop;

    private long startTime=0;
    private Handler myHandler= new Handler();

    long timeInMillis=0;
    long timeSwap=0;
    long finalTime=0L;

    /*private Chronometer myChronometer;
    private boolean isStart;
    long dayInMillis=60*60*24*1000;*/

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        tvTimer=findViewById(R.id.tvTimer);
        btnStop=findViewById(R.id.btnStop);
        btnStart=findViewById(R.id.btnStart);
        /*myChronometer=findViewById(R.id.chChronometer);

        myChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                myChronometer=chronometer;
            }
        });*/


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //myHandler= new Handler();
                startTime=SystemClock.uptimeMillis();
                myHandler.postDelayed(updateTimerMethod,0);


            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwap+=timeInMillis;
                myHandler.removeCallbacks(updateTimerMethod);
            }
        });
    }

   //To use startStopChronometer(); call this method on click of button
/*    public void startStopChronometer()
    {
        if(isStart)
        {
            myChronometer.stop();
            isStart=false;
            btnStart.setText("Start");
        }
        else
        {
            myChronometer.setBase(SystemClock.elapsedRealtime()-dayInMillis);
            myChronometer.start();
            isStart=true;
            btnStart.setText("Stop");
        }
    }*/

    /*public static String getDateFromMillis(long d)
    {
        SimpleDateFormat df=new SimpleDateFormat("MM:ss");//HH:
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return  df.format(d);
    }

    public void start(View view)
    {
        startTime=SystemClock.uptimeMillis();
        myHandler.postDelayed(updateTimerThread,0);
    }

    public void stop(View view)
    {
        myHandler.removeCallbacks(updateTimerThread);
    }

    private Runnable updateTimerThread =new Runnable() {
        @Override
        public void run() {
            timeInMillis=SystemClock.uptimeMillis()-startTime;
            tvTimer.setText(getDateFromMillis(timeInMillis));
            myHandler.postDelayed(this,1000);
        }
    };*/

    private Runnable updateTimerMethod= new Runnable() {
        @SuppressLint("LongLogTag")
        @Override
        public void run() {

            timeInMillis=SystemClock.uptimeMillis()-startTime;
            //finalTime=timeSwap+timeInMillis;

            //int seconds=(int) finalTime/1000;
            //int milliseconds= (int) (finalTime%1000);
            int minutes= (int) (timeInMillis/60);
            int seconds=(int) (timeInMillis)%60;

            //Log.e("minutes", String.valueOf(minutes));
            //Log.e("seconds", String.valueOf(seconds));
           // Log.e("finalTime simple date format", sdf.format(finalTime));

           // Log.e("finalTime", String.format("%02d:%02d:%02d",minutes, seconds, milliseconds % 60));
            Log.e("finalTime", String.format("%02d:%02d",minutes, seconds));
            //SimpleDateFormat sdf=new SimpleDateFormat("MM:ss z"); / 60

             //tvTimer.setText(String.format("%02d:%02d:%02d",minutes, seconds, milliseconds % 60));
            tvTimer.setText(String.format("%02d:%02d",minutes, seconds));
           // tvTimer.setText(String.format("%02d",seconds)+":"+String.format("%02d",milliseconds));

            myHandler.postDelayed(this,0);
        }
    };
}
