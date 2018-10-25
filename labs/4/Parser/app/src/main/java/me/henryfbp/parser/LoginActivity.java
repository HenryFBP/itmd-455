package me.henryfbp.parser;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


class LoginActivity extends AppCompatActivity {

    public static final String USERNAME = "scrungo";
    public static final String PASSWORD = "chungus";
    public static final int CHANCES = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        final Integer[] tries = {CHANCES};

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        final Button buttonLogin = findViewById(R.id.buttonLogin);
        final TextView textViewProblems = findViewById(R.id.textViewProblems);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (credsValid()) {
                    Toast.makeText(getApplicationContext(), "Welcome to hell!", Toast.LENGTH_LONG).show();
                } else {
                    textViewProblems.setText(String.format("Wrong login creds.\n" +
                                    "Try (%s:%s).\n" +
                                    "%d chances left.",
                            USERNAME, PASSWORD, tries[0]));

                    tries[0] = tries[0] - 1;
                }

                if (tries[0] <= 0) {
                    finish();
                }
            }
        });
    }

    private String getUsername() {

        EditText t = findViewById(R.id.editTextUsername);

        return t.getText().toString();
    }

    private String getPassword() {

        EditText t = findViewById(R.id.editTextPassword);

        return t.getText().toString();
    }

    public boolean credsValid() {
        return getUsername().equals(USERNAME) && getPassword().equals(PASSWORD);
    }

}
