package com.example.paulo.projeto_p3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpActivity extends AppCompatActivity {

    public String TAG = "ERROR_________";
    private EditText loginInput;
    private EditText passwordInput;
    private EditText confirmPassInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Parse.initialize(this);

        loginInput = findViewById(R.id.username_input_register);
        passwordInput = findViewById(R.id.password_input_register);
        confirmPassInput = findViewById(R.id.confirm_pass_input_register);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Checar se as credenciais estao vazias e se as senhas dao match
                final ParseUser user = new ParseUser();
                user.setUsername(String.valueOf(loginInput.getText()));
                user.setPassword(String.valueOf(passwordInput.getText()));
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            ParseUser.logInInBackground(user.getUsername(), String.valueOf(passwordInput.getText()), new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    Toast.makeText(getApplicationContext(), user.getUsername() + " criado e logado com sucesso.", Toast.LENGTH_SHORT).show();
                                    Intent listPiecesIntent = new Intent(getApplicationContext(), ListPendingPiecesActivity.class);
                                    startActivity(listPiecesIntent);
                                }
                            });
                        } else {
                            e.printStackTrace();
                            ParseUser.logOut();
                        }
                    }
                });
            }
        });
    }
}
