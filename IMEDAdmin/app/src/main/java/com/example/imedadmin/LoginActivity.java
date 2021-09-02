package com.example.imedadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText adminEmail,adminPass;
    private Button loginBtn;

    private String email,password;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString("isLogin","false").equals("yes")){


            openDash();
        }

        adminEmail = findViewById(R.id.logEmail);
        adminPass = findViewById(R.id.logPassword);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        email = adminEmail.getText().toString();
        password = adminPass.getText().toString();

        if (email.isEmpty()){
            adminEmail.setError("Required");
            adminEmail.requestFocus();
        }else if (password.isEmpty()){
            adminPass.setError("Required");
            adminPass.requestFocus();
        }else if (email.equals("admin@gmail.com") && password.equals("admin123")){

            editor.putString("isLogin","yes");
            editor.commit();
            openDash();
        }else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();
        }

    }

    private void openDash() {

      //  Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

}