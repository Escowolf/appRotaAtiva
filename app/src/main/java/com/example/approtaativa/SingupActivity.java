package com.example.approtaativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.approtaativa.databinding.ActivitySingupBinding;
import com.example.approtaativa.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SingupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ActivitySingupBinding binding;
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtCpf;
    private EditText txtSenha;
    private EditText txtCheckSenha;
    CheckBox btnCheckSenha;
    private ProgressBar progressBar_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivitySingupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        txtNome = binding.txtNomeCompleto;
        txtEmail = binding.txtEmail;
        txtCpf = binding.txtCPF;
        txtSenha = binding.txtSenha;
        txtCheckSenha = binding.txtCheckSenha;
        btnCheckSenha = binding.btnCheckSenha;
        progressBar_signup = binding.progressBarSignup;

        btnCheckSenha.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                txtSenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                txtCheckSenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                txtSenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                txtCheckSenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        binding.btnSingup.setOnClickListener(view1 -> Signup());
    }

    private void Signup(){
        Usuario usuarioModel = new Usuario();
        usuarioModel.setNome(txtNome.getText().toString());
        //todo REGEX do CPF
        usuarioModel.setCpf(txtCpf.getText().toString());
        usuarioModel.setEmail(txtEmail.getText().toString());
        //todo regras da senha colocar tamanho min 8 digitos
        String senha = txtSenha.getText().toString();
        String confirmarSenha = txtCheckSenha.getText().toString();

        if(!TextUtils.isEmpty(usuarioModel.getNome()) ||
                !TextUtils.isEmpty(usuarioModel.getCpf())||
                !TextUtils.isEmpty(usuarioModel.getEmail()) ||
                !TextUtils.isEmpty(senha) || !TextUtils.isEmpty(confirmarSenha)){

            if(senha.equals(confirmarSenha)){
                progressBar_signup.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(usuarioModel.getEmail(), senha).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        usuarioModel.setId(mAuth.getUid());
                        usuarioModel.salvarUsuario();
                        Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                        abrirMain();
                    } else {
                        String error = Objects.requireNonNull(task.getException()).getMessage();
                        Toast.makeText(SingupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                    progressBar_signup.setVisibility(View.INVISIBLE);
                });
            }else{
                Toast.makeText(SingupActivity.this,"A senha deve ser a mesma em todos os campos!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void abrirMain(){
        finish();
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
        switch (item.getItemId()) {
            case R.id.action_main:
                main();
                return true;
            case R.id.action_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void main(){
        finish();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void logout(){
        finish();
        mAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}