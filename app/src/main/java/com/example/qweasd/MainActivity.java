package com.example.qweasd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {



    CheckBox rem_userpass;
    EditText edUsername, edPass;
    Button button;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String SHARED_PREF_NAME = "prefS";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "password";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        edUsername = findViewById(R.id.editTextTextPersonName);
        edPass = findViewById(R.id.editTextTextPassword);


        String name = sharedPreferences.getString(KEY_NAME, null);

        if (name != null){
            Intent intent = new Intent(MainActivity.this , menu_game.class);
            startActivity(intent);
        }







        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,edUsername.getText().toString());
                editor.putString(KEY_PASS,edPass.getText().toString());
                editor.apply();


                Intent intent = new Intent(MainActivity.this, menu_game.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_LONG).show();
            }
        });
    }


}