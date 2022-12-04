package com.example.approtaativa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.approtaativa.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        TextView text_botaoGoogle = (TextView) binding.btnGoogleSingIn.getChildAt(0);
//        text_botaoGoogle.setText("Fazer login com o Google");
    }
}