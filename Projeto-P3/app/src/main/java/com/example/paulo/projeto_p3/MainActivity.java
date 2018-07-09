package com.example.paulo.projeto_p3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(this);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), ListPendingPiecesActivity.class));
        }
    }
}
