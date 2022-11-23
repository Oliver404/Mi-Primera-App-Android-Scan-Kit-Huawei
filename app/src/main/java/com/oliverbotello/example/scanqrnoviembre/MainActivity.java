package com.oliverbotello.example.scanqrnoviembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private Button loginBtn;
    private final static String USERNAME="admin";
    private final static String PASSWORD="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.edttxt_username);
        password=findViewById(R.id.edttxt_password);
        loginBtn=findViewById(R.id.loginbtn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //1 recuperar el texto: username y password
        String userText = username.getText().toString();
        String passText = password.getText().toString();
        //2 comprobar las credenciales del usuario
        if(userText.equals(USERNAME)&&passText.equals(PASSWORD)){
            Intent myIntent = new Intent(this, ScanActivity.class);

            Toast.makeText(this, "Bienvenido ( / ^.^)/", Toast.LENGTH_SHORT).show();
            startActivity(myIntent);
        }
        else{
            String message =getString(R.string.invalid_credentials);
            Snackbar.make(loginBtn,message,Snackbar.LENGTH_SHORT).show();
        }

    }
}