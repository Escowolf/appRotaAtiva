package com.example.approtaativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.approtaativa.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ActivityLoginBinding binding;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("108611398752-0vqj7splfg8oe0v6f80dgpofb1m6nrk3.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);

        binding.btnLogin.setOnClickListener(view12 -> {
            try{
                loginUsuarioSenha(binding.txtEmail.getText().toString(),
                        binding.txtSenha.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnGoogleSingIn.setOnClickListener(view1 -> signIn());

    }

    private void signIn(){
        Intent intentGoogle = googleSignInClient.getSignInIntent();
        abrirActivity.launch(intentGoogle);
    }

    ActivityResultLauncher<Intent> abrirActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent intentGoogle = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intentGoogle);
                    try{
                        GoogleSignInAccount conta = task.getResult(ApiException.class);
                        loginGoogle(conta.getIdToken());
                    } catch (ApiException exception){
                        Toast.makeText(getApplicationContext(), "Nenhum usuário logado.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private void loginGoogle(String token){
        AuthCredential credencial = GoogleAuthProvider.getCredential(token, null);
        mAuth.signInWithCredential(credencial).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
                abrirHome();
            } else {
                Toast.makeText(getApplicationContext(), "Falha ao logar.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
            try{
                GoogleSignInAccount conta = task.getResult(ApiException.class);
                loginGoogle(conta.getIdToken());
            } catch (ApiException exception){
                Toast.makeText(getApplicationContext(), "Nenhum usuário logado.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loginUsuarioSenha(String emailUsuario, String senha){
        mAuth.signInWithEmailAndPassword(emailUsuario, senha)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCustomToken:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                        abrirHome();

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Falha ao logar.", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void abrirHome(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
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