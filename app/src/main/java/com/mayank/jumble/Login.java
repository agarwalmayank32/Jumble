package com.mayank.jumble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Login extends AppCompatActivity {

    EditText email_text,password_text;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onClick1(View view)
    {
        EditText editText1= (EditText)findViewById(R.id.editText1);
        EditText editText2=(EditText)findViewById(R.id.editText2);
        String email=editText1.getText().toString();
        String password=editText2.getText().toString();

        Firebase.setAndroidContext(getApplicationContext());
        Firebase ref = new Firebase("https://jumble.firebaseio.com");
        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Intent g = new Intent(Login.this, MainActivity.class);
                startActivity(g);
                finish();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(Login.this, "Wrong Email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick2(View view)
    {
        Intent g = new Intent(this,SignUp.class);
        startActivity(g);
    }
}
