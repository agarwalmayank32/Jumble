package com.mayank.jumble;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HitStatList extends ArrayAdapter<String> {

    private final Activity context;
    TextView hit_message;
    TextView support_count;
    String[] htext;
    String[] Nick;
    int[] support_count_hitstat;

    public HitStatList(Activity context,String[] htext,int[] support,String[] Nick){
        super(context, R.layout.hitstat_list,htext);
        this.htext=htext;
        support_count_hitstat=support;
        this.Nick=Nick;
        this.context = context;

    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView= inflater.inflate(R.layout.hitstat_list, null, true);

        hit_message = (TextView)rowView.findViewById(R.id.Hit_Message);
        support_count = (TextView)rowView.findViewById(R.id.Support_Count);
        hit_message.setText(htext[position]);
        support_count = (TextView)rowView.findViewById(R.id.Support_Count);
        support_count.setText(String.valueOf(support_count_hitstat[position]));

        return rowView;
    }
}