package com.example.approtaativa;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.approtaativa.databinding.ActivityMainBinding;
import com.example.approtaativa.databinding.ActivityRegistrationBinding;
import com.example.approtaativa.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText txtNome;
    private EditText txtEmail;

    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        txtEmail = binding.txtEmail;
        txtNome = binding.txtNome;

        binding.btnRegistro.setOnClickListener(view1 -> {
            Usuario userModel = new Usuario();

            userModel.setId(mAuth.getUid());
            userModel.setEmail(txtEmail.getText().toString());
            userModel.setNome(txtNome.getText().toString());
            userModel.salvar();
            Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
        });
    }



}