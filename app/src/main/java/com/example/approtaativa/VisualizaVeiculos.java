package com.example.approtaativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.approtaativa.databinding.ActivityVisualizaVeiculosBinding;
import com.example.approtaativa.model.Veiculo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VisualizaVeiculos extends AppCompatActivity {

    private ArrayList<String> veiArray;
    private ListView listarVeiculos;

    ActivityVisualizaVeiculosBinding binding;
    private final DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private final DatabaseReference meusVeiculos = referencia.child("veiculos");
    Query placasVeiculos = meusVeiculos.orderByChild("placa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityVisualizaVeiculosBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        veiArray = new ArrayList<>();
        placasVeiculos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dados: snapshot.getChildren()){
                    Veiculo veiculo = snapshot.getValue(Veiculo.class);
                    Veiculo veiculoData = dados.getValue(Veiculo.class);
                    assert veiculoData != null;
                    veiArray.add(veiculoData.getPlaca());
                }
                listarVeiculos = binding.lisVeiculos;
                ArrayAdapter<String> adapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_2,android.R.id.text1,veiArray);
                listarVeiculos.setAdapter(adapt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Erro ao listar veículos! "+error, Toast.LENGTH_SHORT).show();
            }
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
            veiculos();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void veiculos(){
        finish();
        Intent intent = new Intent(getApplicationContext(),VeiculosActivity.class);
        startActivity(intent);
    }

}