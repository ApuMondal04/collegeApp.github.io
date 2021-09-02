package com.example.imed.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.imed.MainActivity;
import com.example.imed.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName,regEmail,regPassword;
    private Button registerBtn;
    private String name,email,password;

    private FirebaseAuth auth;

    private DatabaseReference reference;
    private DatabaseReference dbRef;
    private TextView openLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        reference = FirebaseDatabase.getInstance().getReference();

        openLog = findViewById(R.id.openLog);


        regName = findViewById(R.id.regName);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        registerBtn = findViewById(R.id.registerBtn);
        
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        openLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    private void openLogin() {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (auth.getCurrentUser() != null){
            openMain();
        }

    }

    private void openMain() {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }



    private void validateData() {
        name = regName.getText().toString();
        email = regEmail.getText().toString();
        password = regPassword.getText().toString();

        if (name.isEmpty()){
            regName.setError("Required");
            regName.requestFocus();
        }else if (email.isEmpty()){
            regEmail.setError("Required");
            regEmail.requestFocus();
        }else if (password.isEmpty()){
            regPassword.setError("Required");
            regPassword.requestFocus();
        }else {
            createUser();
        }

    }

    private void createUser() {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            uploadData();

                        }else {
                            Toast.makeText(RegisterActivity.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }


                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadData() {

        dbRef = reference.child("students");
        String key = dbRef.push().getKey();

        HashMap<String, String> user= new HashMap<>();
        user.put("key", key);
        user.put("name", name);
        user.put("email", email);
        user.put("password", password);

        dbRef.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            openMain();

                        }else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
