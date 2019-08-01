package com.example.crdr.layouttestingapplication;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import es.dmoral.toasty.Toasty;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class HousieActivity extends AppCompatActivity {

    GridView gvNumbers;
    ArrayList<Integer> numberslist=new ArrayList<>();

    HousieAdapter housieAdapter;

    int integerArrayList[];
    Random randomObj = new Random();
    int randomNum;
    int min,max,output;
    MenuItem menuCalledToken,menuCallNext,menuReset;
    boolean isManual=false;
    private static final String SHOWCASE_ID="simple showcase";
    TextToSpeech t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housie);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });

        refresh();

        gvNumbers=findViewById(R.id.gvNumbers);

        integerArrayList = new int[90];

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_switch_mode_black_24dp);
        // upArrow.setColorFilter(getResources().getColor(R.color.whitecolor), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for(int i=1,count=0;i<=90;i++,count++)
        {
            numberslist.add(i);
            integerArrayList[count]=i;

        }

        //integerArrayList=numberslist.toArray(integerArrayList);
        housieAdapter=new HousieAdapter(this,numberslist,isManual);
        gvNumbers.setAdapter(housieAdapter);

        gvNumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvToken=(TextView) view.findViewById(R.id.tvToken);
                tvToken.setBackground(getResources().getDrawable(R.drawable.circle_red));
                tvToken.setTextColor(Color.WHITE);
                menuCalledToken.setTitle(tvToken.getText().toString());
            }
        });

        Toasty.info(HousieActivity.this,"Hello instant build1",Toast.LENGTH_LONG,true).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.housie_action, menu);
        menuCalledToken =menu.findItem(R.id.token_text);
        menuCallNext=menu.findItem(R.id.action_leave);
       // showTutor(100,menuCalledToken.getActionView());
        return true;

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:

                final Dialog dialogChooseMode = new Dialog(HousieActivity.this);
                dialogChooseMode.setContentView(R.layout.alert_choose_mode);
                dialogChooseMode.setTitle("Select Leave Type");

                // set the custom dialog components - text, image and button
                final RadioButton rbAutomatic = (RadioButton) dialogChooseMode.findViewById(R.id.rbAutomatic);
                final RadioButton rbManual = (RadioButton) dialogChooseMode.findViewById(R.id.rbManual);

                if(isManual)
                {
                    rbManual.setChecked(true);
                    refresh();
                }
                else
                {
                    rbAutomatic.setChecked(true);
                    refresh();
                }

                rbAutomatic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(HousieActivity.this);
                        builder.setMessage("if you click yes your data will be lossed")
                                .setTitle("Do You Wish To Start New Game?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dialogChooseMode.dismiss();
                                        isManual=false;
                                        refresh();

                                        housieAdapter=new HousieAdapter(HousieActivity.this,numberslist,isManual);
                                        gvNumbers.setAdapter(housieAdapter);
                                        Toasty.info(HousieActivity.this,"Automatic Mode Selected",Toast.LENGTH_LONG,true).show();
                                        menuCallNext.setVisible(true);
                                        menuCalledToken.setVisible(true);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();


                        //gvNumbers.get.setClickable(false);
                    }
                });

                rbManual.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(HousieActivity.this);
                        builder.setMessage("if you click yes your data will be lossed")
                                .setTitle("Do You Wish To Start New Game?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dialogChooseMode.dismiss();
                                        isManual=true;
                                        refresh();

                                        housieAdapter=new HousieAdapter(HousieActivity.this,numberslist,isManual);
                                        gvNumbers.setAdapter(housieAdapter);
                                        Toasty.info(HousieActivity.this,"Manual Mode Selected",Toast.LENGTH_LONG,true).show();
                                        menuCallNext.setVisible(false);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                });

                dialogChooseMode.show();

                return true;

            case R.id.action_leave:

                if(integerArrayList.length!=0)
                {
                    View view;
                    randomNum=integerArrayList[randomObj.nextInt(integerArrayList.length)];
                    Log.e("called number", String.valueOf(randomNum));
                    Log.w("before remove","integerArrayList "+integerArrayList.length);

                    integerArrayList= ArrayUtils.removeElement(integerArrayList, randomNum);
                    Log.w("after remove","integerArrayList "+integerArrayList.length);
                    Log.w("integerArrayList" ,Arrays.toString(integerArrayList));
                    if(randomNum==0)
                    {
                        //integerArrayList= ArrayUtils.removeElement(integerArrayList, randomNum);
                        view=gvNumbers.getChildAt(randomNum+1);
                    }
                    else
                    {
                        view=gvNumbers.getChildAt(randomNum-1);

                    }
                    TextView tvToken=(TextView) view.findViewById(R.id.tvToken);
                    tvToken.setBackground(getResources().getDrawable(R.drawable.circle_red));
                    tvToken.setTextColor(Color.WHITE);
                    menuCalledToken.setTitle(String.valueOf(randomNum));
                    String toSpeak = "Number "+randomNum;

                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                    if(integerArrayList.length<=0)
                    {
                        Toasty.success(this,"Congratulations!!!!!!!!!!!!\n Start new game",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                   startActivity(new Intent(this,HousieActivity.class));
                    //onCreate(this);
                }


                return true;

            case R.id.reset_game:

                finish();
                startActivity(new Intent(this,HousieActivity.class));



                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public int pickRandomNumber()
    {
        return randomObj.nextInt (91 - 1) + 1;
    }

    public void refresh()
    {
        if(isManual)
        {
            setTitle(Html.fromHtml("<small>Manual Mode</small>"));

        }
        else
        {
            setTitle(Html.fromHtml("<small>Automatic Mode</small>"));


        }

    }

    private void showTutor(int millis,View view)
    {
        new MaterialShowcaseView.Builder(this).
                setTarget(view)
                .setTitleText("Single")
                .setDismissText("Got It")
                .setContentText("Click here to call number")
                .setDelay(millis)
                .singleUse(SHOWCASE_ID)
                .show();
    }
    //JUNA CODE
/*                randomNum = randomNumber.nextInt (91 - 1) + 1;
                if(integerArrayList.size()!=0)
                {
                    //Log.e("in","main if");
                    l: for(int i=0;i<integerArrayList.size();i++)
                    {
                        // Log.e("in","randum "+randomNum);
                        if(randomNum == integerArrayList.get(i))
                        {
                            //Log.e("in","for if");

                            randomNum=pickRandomNumber();
                            //  Log.e("in","randum new "+randomNum);
                            continue l;

                        }
                        else
                        {
                            //Log.e("in","for else");
                            //break l;

                        }
                    }
                    View view=gvNumbers.getChildAt(randomNum-1);
                    TextView tvToken=(TextView) view.findViewById(R.id.tvToken);
                    tvToken.setBackground(getResources().getDrawable(R.drawable.circle_red));
                    tvToken.setTextColor(Color.WHITE);
                    menuCalledToken.setTitle(String.valueOf(randomNum));

                    integerArrayList.add(randomNum);

                }
                else
                {
                    //Log.e("in","main else");
                    View view=gvNumbers.getChildAt(randomNum-1);
                    TextView tvToken=(TextView) view.findViewById(R.id.tvToken);
                    tvToken.setBackground(getResources().getDrawable(R.drawable.circle_red));
                    tvToken.setTextColor(Color.WHITE);
                    menuCalledToken.setTitle(String.valueOf(randomNum));
                    integerArrayList.add(randomNum);

                }

                Collections.sort(integerArrayList);
                Log.e("Number List ",integerArrayList.toString());*/
}