package com.example.crdr.layouttestingapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class HousieAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Integer> Items;
    //int musicModel;

    TextView tvToken;
    private boolean isManual;
    int width,height;
    //
    public HousieAdapter(Activity activity, ArrayList<Integer> items,Boolean isManual) {
        this.activity = activity;
        Items = items;
        this.isManual=isManual;

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
         width= size.x/10;
        height = size.y/10;
        Log.e("Width", String.valueOf(width)/*+"         width/10 " + width/10*/);
        Log.e("height", String.valueOf(height)/*+"        height/10 " + height/9*/);

    }

    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public Object getItem(int position) {
        return Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(inflater==null)
        {
            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view ==null)
        {
            view=inflater.inflate(R.layout.row_tokens,null);
        }
        tvToken=(TextView) view.findViewById(R.id.tvToken);
        tvToken.setLayoutParams(
                new TableRow.LayoutParams(
                        width,
                        width));
        /*tvToken.setWidth(108);
        tvToken.setHeight(108);*/

        tvToken.setText(String.valueOf(Items.get(position)));

        return view;
    }



    @Override
    public boolean areAllItemsEnabled() {

        if(isManual)
            return true;
        else
            return false;
    }

    @Override
    public boolean isEnabled(int position) {
        //return false;
        if(isManual)
            return true;
        else
            return false;
    }
}
