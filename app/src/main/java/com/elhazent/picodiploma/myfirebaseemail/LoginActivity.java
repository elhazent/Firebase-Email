package com.elhazent.picodiploma.myfirebaseemail;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText txtEhmail, txtPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEhmail = findViewById(R.id.ehmail);
        txtPass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnlogIn);
        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnlogIn){
            String ehmail, pass;
            ehmail = txtEhmail.getText().toString().trim();
            pass = txtPass.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(ehmail,pass)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                tampilkanPesan("Anda berhasil login");
                            }else {
                                tampilkanPesan("Anda gagal login");
                            }
                        }
                    });
        }
    }
    private void tampilkanPesan(String string){
        Toast.makeText(LoginActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
