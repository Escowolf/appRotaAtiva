package com.example.approtaativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.approtaativa.databinding.ActivityHomeBinding;
import com.example.approtaativa.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSair.setOnClickListener(view1 -> {
            mAuth.getInstance().signOut();
            finish();
        });

        binding.btnPerfil.setOnClickListener(view12 -> {
            finish();
            Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
            startActivity(intent);
        });
    }
}