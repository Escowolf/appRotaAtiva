package com.example.approtaativa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.approtaativa.databinding.ActivityPerfilBinding;
import com.example.approtaativa.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;

public class PerfilActivity extends AppCompatActivity {


    ActivityPerfilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        EditText txtEmail = binding.txtEmail;
        TextView txtNome =  binding.txtUsernameEdit;

        binding.btnRegistro.setOnClickListener(view1 -> {
//            Usuario userModel = new Usuario();
//
//            userModel.setId(mAuth.getUid());
//            userModel.getEmail(txtEmail.getText().toString());
//            userModel.setNome(txtNome.getText().toString());
//            userModel.salvar();
//            Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
        });
    }
}