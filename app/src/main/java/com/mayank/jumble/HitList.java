package com.mayank.jumble;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class HitList extends ArrayAdapter<String> {

    private final Activity context;
    TextView hit_message;
    Boolean[] flag;
    ImageButton support;
    TextView support_count;
    String[] htext;
    String[] nick;
    int[] support_count_hit;

    public HitList(Activity context,String[] htext,int[] support,String[] nick,int x) {
        super(context, R.layout.hit_list,htext);
        this.context = context;
        this.htext=htext;
        support_count_hit=support;
        this.nick=nick;
        flag=new Boolean[x];
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView= inflater.inflate(R.layout.hit_list, null, true);
        flag[position]= false;
        hit_message = (TextView)rowView.findViewById(R.id.Hit_Message);
        support = (ImageButton)rowView.findViewById(R.id.Support_Button);
        support_count = (TextView)rowView.findViewById(R.id.Support_Count);
        support_count.setText(String.valueOf(support_count_hit[position]));
        hit_message.setText(htext[position]);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton support = (ImageButton) v.findViewById(R.id.Support_Button);
                support_count = (TextView)rowView.findViewById(R.id.Support_Count);
                Integer value=Integer.parseInt(String.valueOf(support_count.getText()));
                if (!flag[position]) {
                    support.setBackgroundResource(R.drawable.thumbpressed);
                    value++;
                    support_count.setText(String.valueOf(value));
                } else {
                    support.setBackgroundResource(R.drawable.thumbnotpressed);
                    value--;
                    support_count.setText(String.valueOf(value));
                }
                flag[position]=!flag[position];

            }
        });



        return rowView;
    }
}