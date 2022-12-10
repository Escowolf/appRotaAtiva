package com.example.approtaativa.secrets;

public class backupMain {
//    package com.example.approtaativa;
//
//import static android.content.ContentValues.TAG;
//
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Toast;
//
//import com.example.approtaativa.databinding.ActivityMainBinding;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.GoogleAuthProvider;
//
//    public class MainActivity extends AppCompatActivity {
//        private FirebaseAuth mAuth;
//        ActivityMainBinding binding;
//        GoogleSignInClient googleSignInClient;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//            binding = ActivityMainBinding.inflate(getLayoutInflater());
//            View view = binding.getRoot();
//            setContentView(view);
//
//            mAuth = FirebaseAuth.getInstance();
//
//            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
//                    GoogleSignInOptions.DEFAULT_SIGN_IN)
//                    .requestIdToken("108611398752-0vqj7splfg8oe0v6f80dgpofb1m6nrk3.apps.googleusercontent.com")
//                    .requestEmail()
//                    .build();
//
//            googleSignInClient = GoogleSignIn.getClient(this,gso);
//
//            binding.btnLogin.setOnClickListener(view12 -> {
//                try{
//                    loginUsuarioSenha(binding.textEmail.getText().toString(),
//                            binding.textPassword.getText().toString());
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            binding.btnGoogleSingIn.setOnClickListener(view1 -> signIn());
//
//            binding.txtCadastro.setOnClickListener(view13 -> {
//                try{
//                    criarUsuarioEsenha(binding.textEmail.getText().toString(),
//                            binding.textPassword.getText().toString());
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        private void signIn(){
//            Intent intentGoogle = googleSignInClient.getSignInIntent();
////        startActivityForResult(intentGoogle,1);
//            abrirActivity.launch(intentGoogle);
//        }
//
//        ActivityResultLauncher<Intent> abrirActivity = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if(result.getResultCode() == Activity.RESULT_OK){
//                        Intent intentGoogle = result.getData();
//                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intentGoogle);
//                        try{
//                            GoogleSignInAccount conta = task.getResult(ApiException.class);
//                            loginGoogle(conta.getIdToken());
//                        } catch (ApiException exception){
//                            Toast.makeText(getApplicationContext(), "Nenhum usuário logado.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//
//        private void loginGoogle(String token){
//            AuthCredential credencial = GoogleAuthProvider.getCredential(token, null);
//            mAuth.signInWithCredential(credencial).addOnCompleteListener(this, task -> {
//                if(task.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
//                    finish();
//                    abrirHome();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Falha ao logar.", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        private void criarUsuarioEsenha(String emailUsuario, String senha){
//            mAuth.createUserWithEmailAndPassword(emailUsuario, senha);
//
//            Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show();
//
//        }
//
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent intent){
//            super.onActivityResult(requestCode, resultCode, intent);
//
//            if (requestCode == 1){
//                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
//                try{
//                    GoogleSignInAccount conta = task.getResult(ApiException.class);
//                    loginGoogle(conta.getIdToken());
//                } catch (ApiException exception){
//                    Toast.makeText(getApplicationContext(), "Nenhum usuário logado.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//
//        private void loginUsuarioSenha(String emailUsuario, String senha){
//            mAuth.signInWithEmailAndPassword(emailUsuario, senha)
//                    .addOnCompleteListener(this, task -> {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCustomToken:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
//                            abrirHome();
////                                    updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
//                            Toast.makeText(getApplicationContext(), "Falha ao logar.", Toast.LENGTH_SHORT).show();
//
////                            Toast.makeText(CustomAuthActivity.this, "Authentication failed.",
////                                    Toast.LENGTH_SHORT).show();
////                                    updateUI(null);
//                        }
//                    });
//        }
//
//        private void abrirHome(){
//            binding.textEmail.setText("");
//            binding.textPassword.setText("");
//            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//            startActivity(intent);
//        }
//
//        @Override
//        public void onStart() {
//            super.onStart();
//            // Check if user is signed in (non-null) and update UI accordingly.
//            FirebaseUser currentUser = mAuth.getCurrentUser();
//            try{
//                if (currentUser != null) {
//                    Toast.makeText(getApplicationContext(), "Usuário "+currentUser.getEmail()+" logado.", Toast.LENGTH_LONG).show();
//                    abrirHome();
//                }
//            }catch (Exception e){
////
//            }
////        updateUI(currentUser);
//        }
//    }

//    <?xml version="1.0" encoding="utf-8"?>
//<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:background="@color/padraoBackground"
//    tools:context=".MainActivity">
//
//    <EditText
//    android:id="@+id/textEmail"
//    android:layout_width="match_parent"
//    android:layout_height="0dp"
//    android:layout_marginStart="32dp"
//    android:layout_marginTop="32dp"
//    android:layout_marginEnd="32dp"
//    android:layout_weight="1"
//    android:autofillHints=""
//    android:background="@drawable/txt_round"
//    android:ems="10"
//    android:hint="@string/prompt_email"
//    android:inputType="textWebEmailAddress"
//    android:minHeight="48dp"
//    android:paddingStart="10dp"
//    android:paddingEnd="10dp"
//    android:textColor="@color/black"
//    android:textColorHint="#757575"
//    app:layout_constraintEnd_toEndOf="parent"
//    app:layout_constraintHorizontal_bias="1.0"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toBottomOf="@+id/imgLogo" />
//
//    <EditText
//    android:id="@+id/textPassword"
//    android:layout_width="match_parent"
//    android:layout_height="0dp"
//    android:layout_marginStart="32dp"
//    android:layout_marginTop="8dp"
//    android:layout_marginEnd="32dp"
//    android:layout_weight="1"
//    android:autofillHints=""
//    android:background="@drawable/txt_round"
//    android:backgroundTint="@color/white"
//    android:ems="10"
//    android:hint="@string/prompt_password"
//    android:inputType="text|textPassword"
//    android:minHeight="48dp"
//    android:paddingStart="10dp"
//    android:paddingEnd="10dp"
//    android:textColor="@color/black"
//    android:textColorHint="#757575"
//    app:layout_constraintEnd_toEndOf="parent"
//    app:layout_constraintHorizontal_bias="0.497"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toBottomOf="@+id/textEmail" />
//
//    <Button
//    android:id="@+id/btnLogin"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_gravity="center"
//    android:layout_marginStart="32dp"
//    android:layout_marginTop="16dp"
//    android:layout_marginEnd="32dp"
//    android:layout_weight="1"
//    android:backgroundTint="@color/padraoPrimeira"
//    android:fontFamily="@font/poppins_semibold"
//    android:text="@string/action_sign_in_short"
//    android:textColor="#006064"
//    app:layout_constraintEnd_toEndOf="parent"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toBottomOf="@+id/textPassword" />
//
//    <TextView
//    android:id="@+id/textView"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_marginStart="16dp"
//    android:layout_marginTop="16dp"
//    android:layout_marginEnd="16dp"
//    android:layout_weight="1"
//    android:fontFamily="@font/poppins_semibold"
//    android:gravity="center_horizontal"
//    android:text="@string/stringOR"
//    android:textAlignment="center"
//    android:textColor="@color/padraoPrimeira"
//    android:textStyle="bold"
//    app:layout_constraintEnd_toEndOf="parent"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toBottomOf="@+id/btnLogin" />
//
//    <com.google.android.gms.common.SignInButton
//    android:id="@+id/btnGoogleSingIn"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_gravity="center"
//    android:layout_marginStart="32dp"
//    android:layout_marginTop="12dp"
//    android:layout_marginEnd="32dp"
//    app:layout_constraintEnd_toEndOf="parent"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toBottomOf="@+id/textView" />
//
//    <ImageView
//    android:id="@+id/imgLogo"
//    android:layout_width="0dp"
//    android:layout_height="wrap_content"
//    android:layout_marginStart="32dp"
//    android:layout_marginTop="128dp"
//    android:layout_marginEnd="32dp"
//    android:contentDescription="@string/logo"
//    android:foregroundGravity="center_vertical"
//    app:layout_constraintEnd_toEndOf="parent"
//    app:layout_constraintHorizontal_bias="0.0"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toTopOf="parent"
//    app:srcCompat="@drawable/g5638" />
//
//    <TextView
//    android:id="@+id/txtCadastro"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginStart="32dp"
//    android:layout_marginTop="32dp"
//    android:layout_marginEnd="32dp"
//    android:clickable="true"
//    android:fontFamily="@font/poppins_light"
//    android:minHeight="48dp"
//    android:text="@string/txtCadastro"
//    android:textColor="@color/padraoPrimeira"
//    android:textStyle="bold"
//    app:layout_constraintEnd_toEndOf="parent"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toBottomOf="@+id/btnGoogleSingIn" />
//
//</androidx.constraintlayout.widget.ConstraintLayout>
}
