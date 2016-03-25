package com.mayank.jumble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Jumble_Add_Member extends AppCompatActivity {

    Firebase firebase;
    int number;
    private String GroupName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumble__add__member);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            GroupName=extras.getString("Group_Name_Key");
            setTitle(GroupName);
        }

        Firebase.setAndroidContext(getApplicationContext());
        firebase = new Firebase("https://jumble.firebaseio.com/Groups");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                number = Integer.parseInt(dataSnapshot.child("Number").getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
    public void create(View view)
    {
        new Firebase("https://jumble.firebaseio.com/Groups").child(String.valueOf(number)).setValue(GroupName);
        int num = number + 1;
        firebase.child("Number").setValue(String.valueOf(num));
        Intent g= new Intent(Jumble_Add_Member.this,MainActivity.class);
        startActivity(g);
    }
}
