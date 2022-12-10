package com.example.approtaativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_home) {
            home();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void home(){
        finish();
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
    }


}