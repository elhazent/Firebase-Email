package com.elhazent.picodiploma.myfirebaseemail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText txtEmail, txtPassword;
    private Button btnSign;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPassword = findViewById(R.id.password);
        txtEmail = findViewById(R.id.email);
        btnSign = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        btnSign.setOnClickListener(this);
        tvLogin = findViewById(R.id.login);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void tampilkanPesan(String string){
        Toast.makeText(getApplicationContext(),string,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signup){
            String email, password;
            email = txtEmail.getText().toString().trim();
            password = txtPassword.getText().toString().trim();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                tampilkanPesan("Anda berhasil didaftarkan");
                            }else {
                                tampilkanPesan("Anda gagal didaftarkan");
                            }
                        }
                    });
        }
    }
}
