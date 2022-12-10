package com.example.approtaativa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.approtaativa.databinding.ActivityPerfilBinding;
import com.example.approtaativa.databinding.ActivitySingupBinding;
import com.example.approtaativa.databinding.ActivityVeiculosBinding;
import com.google.firebase.auth.FirebaseAuth;

public class VeiculosActivity extends AppCompatActivity {

    ActivityVeiculosBinding binding;
    private EditText txtCor;
    private EditText txtRenavan;
    private EditText txtPlaca;
    private EditText txtAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVeiculosBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }


}