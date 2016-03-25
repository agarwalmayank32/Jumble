package com.mayank.jumble;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class Jumble_MemberList extends ArrayAdapter<String> {

    private final Activity context;


    public Jumble_MemberList(Activity context){
        super(context, R.layout.jumble_memberlist);
        this.context = context;

    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView= inflater.inflate(R.layout.jumble_memberlist, null, true);

        return rowView;
    }
}