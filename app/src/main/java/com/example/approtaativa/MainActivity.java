package com.example.approtaativa;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.approtaativa.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ActivityMainBinding binding;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
//        TextView text_botaoGoogle = (TextView) binding.btnGoogleSingIn.getChildAt(0);
//        text_botaoGoogle.setText("Fazer login com o Google");
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                loginUsuarioSenha(binding.textEmail.getText().toString(),
                        binding.textPassword.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUsuarioSenha(String emailUsuario, String senha){
        mAuth.signInWithEmailAndPassword(emailUsuario, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                            abrirHome();
                            finish();
//                                    updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Falha ao logar.", Toast.LENGTH_SHORT).show();

//                            Toast.makeText(CustomAuthActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                        }
                    }
                });
    }

    private void abrirHome(){
        binding.textEmail.setText("");
        binding.textPassword.setText("");
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        try{
            Toast.makeText(getApplicationContext(), "Usuário "+currentUser.getEmail()+" logado.", Toast.LENGTH_LONG).show();
            abrirHome();
        }catch (Exception e){
//
        }
//        updateUI(currentUser);
    }
}