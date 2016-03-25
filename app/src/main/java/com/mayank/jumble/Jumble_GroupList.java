package com.mayank.jumble;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Jumble_GroupList extends ArrayAdapter<String> {

    private final Activity context;
    Firebase firebase;
    TextView textView;

    public Jumble_GroupList(Activity context,String a[]){
        super(context, R.layout.jumble_grouplist,a);
        this.context = context;
        Firebase.setAndroidContext(context);
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView= inflater.inflate(R.layout.jumble_grouplist, null, true);
        textView=(TextView)rowView.findViewById(R.id.Group_Name_List);
        firebase= new Firebase("https://jumble.firebaseio.com/Groups");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textView.setText(dataSnapshot.child(String.valueOf(position)).getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        return rowView;
    }
}