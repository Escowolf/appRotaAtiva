package com.example.approtaativa;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.approtaativa.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        binding.btnLoginMain.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        });

        binding.btnSignupMain.setOnClickListener(view12 -> MainActivity.this.abrirSingup());
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        try{
            if (currentUser != null) {
                Toast.makeText(getApplicationContext(), "Usuário "+currentUser.getEmail()+" logado.", Toast.LENGTH_LONG).show();
                abrirHome();
            }
        }catch (Exception e){
            Log.w(TAG, "Nenhum usuário encontrado ", e);
        }
    }

    private void abrirHome(){
        finish();
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
    }

    private void abrirSingup(){
        finish();
        Intent intent = new Intent(getApplicationContext(), SingupActivity.class);
        startActivity(intent);
    }



}