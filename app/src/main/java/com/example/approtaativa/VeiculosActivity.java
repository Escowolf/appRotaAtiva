package com.example.approtaativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.approtaativa.databinding.ActivityPerfilBinding;
import com.example.approtaativa.databinding.ActivitySingupBinding;
import com.example.approtaativa.databinding.ActivityVeiculosBinding;
import com.example.approtaativa.model.Veiculo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VeiculosActivity extends AppCompatActivity {
    ActivityVeiculosBinding binding;
    private EditText cor;
    private EditText renavan;
    private EditText placa;
    private EditText ano;
    private Button cadastrar;
    private Button visualizar;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVeiculosBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        placa = binding.txtPlaca;
        renavan = binding.txtRenavan;
        cor = binding.txtCor;
        ano = binding.txtAno;
        cadastrar = binding.btnAdicionar;
        visualizar = binding.btnVisualizar;

        cadastrar.setOnClickListener(view1 -> {
            DatabaseReference veiculo = referencia.child("veiculos");

            Veiculo veiculoData = new Veiculo();
            veiculoData.setPlaca(placa.getText().toString());
            veiculoData.setRenavan(renavan.getText().toString());
            veiculoData.setCor(cor.getText().toString());
            veiculoData.setAno(Long.valueOf(ano.getText().toString()));
            veiculo.push().setValue(veiculoData);

            Toast.makeText(getApplicationContext(), "Veiculo "+placa.getText() +" cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        });

        visualizar.setOnClickListener(view12 -> {
            Intent intent = new Intent(VeiculosActivity.this, VisualizaVeiculos.class);
            startActivity(intent);
        });
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
            home();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void home(){
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
    }
}