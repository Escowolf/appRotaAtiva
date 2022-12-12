package com.example.approtaativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.approtaativa.databinding.ActivityVeiculosBinding;
import com.example.approtaativa.model.Veiculo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VeiculosActivity extends AppCompatActivity {
    ActivityVeiculosBinding binding;
    private EditText cor;
    private EditText renavan;
    private EditText placa;
    private EditText ano;
    private final DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

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
        Button cadastrar = binding.btnAdicionar;
        Button visualizar = binding.btnVisualizar;

        cadastrar.setOnClickListener(view1 -> {
            DatabaseReference veiculo = referencia.child("veiculos");

            Veiculo veiculoData = new Veiculo();
            veiculoData.setPlaca(placa.getText().toString());
            veiculoData.setRenavan(renavan.getText().toString());
            veiculoData.setCor(cor.getText().toString());
            veiculoData.setAno(Long.valueOf(ano.getText().toString()));
            veiculo.push().setValue(veiculoData);

            Toast.makeText(getApplicationContext(), "Veiculo "+placa.getText() +" cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            limpaCampos();
        });

        visualizar.setOnClickListener(view12 -> {
            Intent intent = new Intent(VeiculosActivity.this, VisualizaVeiculos.class);
            startActivity(intent);
        });
    }

    public void limpaCampos(){
        binding.txtPlaca.setText("");
        binding.txtRenavan.setText("");
        binding.txtCor.setText("");
        binding.txtAno.setText("");
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
        finish();
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
    }
}