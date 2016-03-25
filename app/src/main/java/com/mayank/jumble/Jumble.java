package com.mayank.jumble;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Jumble extends Fragment {

    Intent StartActivity;
    String[] val;
    String[] number;
    int numb;
    Firebase firebase;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jumble, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g = new Intent(getActivity(), Jumble_Group_Create.class);
                startActivity(g);
            }
        });

        listView = (ListView) view.findViewById(R.id.Group_List);

        Firebase.setAndroidContext(getContext());
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        firebase= new Firebase("https://jumble.firebaseio.com/Groups");
        firebase.keepSynced(true);
        firebase.orderByKey();
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numb = Integer.parseInt(dataSnapshot.child("Number").getValue().toString());
                val = new String[numb];
                try1(numb);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                firebase= new Firebase("https://jumble.firebaseio.com/Groups");
                firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        val[position]= dataSnapshot.child(String.valueOf(position)).getValue().toString();
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
                StartActivity= new Intent(getActivity(),Jumble_Chat_Page.class);
                StartActivity.putExtra("Group_Name_Key", val[position]);
                startActivity(StartActivity);
            }
        });
        return view;
    }

    public void try1(int numb)
    {
        number = new String[numb];
        Jumble_GroupList adapter = new Jumble_GroupList(getActivity(), number);
        listView.setAdapter(adapter);
    }

}
