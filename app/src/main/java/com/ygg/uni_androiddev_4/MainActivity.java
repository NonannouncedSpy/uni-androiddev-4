package com.ygg.uni_androiddev_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public void logIn() {
        // get our name input
        final EditText nameField = findViewById(R.id.main_edit);
        String name = nameField.getText().toString();

        // check if it matches a pattern of "FIRST LAST"
        if (!name.matches("[a-zA-Zа-яА-Я]+ [a-zA-Zа-яА-Я]+")) {
            Toast badNameToast = Toast.makeText(getApplicationContext(), getString(R.string.invalid_name), Toast.LENGTH_SHORT);
            badNameToast.show();
            return;
        }
        String[] parsedName = name.split(" ");

        // start next activity and pass the name stuff
        Intent registerIntent = new Intent(getApplicationContext(), DisciplineRegisterActivity.class);
        registerIntent.putExtra("first name", parsedName[0]);
        registerIntent.putExtra("last name", parsedName[1]);
        startActivity(registerIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button loginButton = findViewById(R.id.main_button);
        loginButton.setOnClickListener(v -> logIn());
    }
}