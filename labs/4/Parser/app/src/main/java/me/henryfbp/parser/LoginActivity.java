package me.henryfbp.parser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    public static final String USERNAME = "scrungo";
    public static final String PASSWORD = "chungus";
    public static final int CHANCES = 3;

    Button buttonLogin;
    FloatingActionButton floatingActionButtonLazy;

    TextView textViewProblems;
    EditText editTextUsername;
    EditText editTextPassword;

    public void populateComponents() {
        buttonLogin = findViewById(R.id.buttonLogin);
        floatingActionButtonLazy = findViewById(R.id.floatingActionButtonLazy);

        textViewProblems = findViewById(R.id.textViewProblems);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        final Integer[] tries = {CHANCES};

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        populateComponents();

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (credsValid()) {
                    Toast.makeText(getApplicationContext(), "Welcome to hell!", Toast.LENGTH_LONG).show();

                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(myIntent);
                } else {
                    textViewProblems.setText(String.format("Wrong login creds.\n" +
                            "Try pressing the floating action button.\n" +
                            "%d chances left.", tries[0]));

                    tries[0] = tries[0] - 1;
                }

                if (tries[0] <= 0) {
                    finish();
                }
            }
        });

        floatingActionButtonLazy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateLogin();
            }
        });
    }

    private void populateLogin() {
        editTextUsername.setText(USERNAME);
        editTextPassword.setText(PASSWORD);
    }

    private String getUsername() {
        return editTextUsername.getText().toString();
    }

    private String getPassword() {
        return editTextPassword.getText().toString();
    }

    public boolean credsValid() {
        return (getUsername().equals(USERNAME) && getPassword().equals(PASSWORD));
    }

}
