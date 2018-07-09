package com.example.paulo.projeto_p3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public String TAG = "ERROR_________";
    private EditText loginInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button registerActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Parse.initialize(getApplicationContext());

        loginInput = findViewById(R.id.username_input_login);
        passwordInput = findViewById(R.id.password_input_login);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(String.valueOf(loginInput.getText()), String.valueOf(passwordInput.getText()), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(getApplicationContext(), "Bem vindo, " + user.getUsername() + ".", Toast.LENGTH_SHORT).show();
                            Intent listPiecesIntent = new Intent(getApplicationContext(), ListPendingPiecesActivity.class);
                            startActivity(listPiecesIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            ParseUser.logOut();
                        }
                    }
                });
            }
        });

        registerActivityButton = findViewById(R.id.register_activity_button);

        registerActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpActivity = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signUpActivity);
            }
        });
    }
}
