package com.mayank.jumble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Jumble_Group_Create extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumble__group__create);
        editText=(EditText)findViewById(R.id.Group_Name_Add);
    }

    public void nextClick(View view)
    {
        Intent g= new Intent(Jumble_Group_Create.this,Jumble_Add_Member.class);
        g.putExtra("Group_Name_Key",editText.getText().toString());
        startActivity(g);
    }
}