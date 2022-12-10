package com.example.approtaativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.approtaativa.MainActivity;
import com.example.approtaativa.databinding.ActivitySingupBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SingupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ActivitySingupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivitySingupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        binding.btnSingup.setOnClickListener(view13 -> {
            try{
                criarUsuarioEsenha(binding.txtEmail.getText().toString(),
                        binding.txtCheckSenha.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void criarUsuarioEsenha(String emailUsuario, String senha){
        mAuth.createUserWithEmailAndPassword(emailUsuario, senha);
//TODO criar try-catch para capturar erro caso o usuário não seja salvo
        Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show();
        finish();
        abrirMain();
    }

    private void abrirMain(){
//        binding.textEmail.setText("");
//        binding.textPasswordSingup.setText("");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_main) {
            main();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void main(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

}