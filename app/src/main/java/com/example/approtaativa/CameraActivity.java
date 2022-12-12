package com.example.approtaativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.approtaativa.databinding.ActivityCameraBinding;
import com.example.approtaativa.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class CameraActivity extends AppCompatActivity {

    private ArrayList<String> nomesImagens;
    private String[] arrayNomesImagens;

    private ListView lvImagens;
    private ImageView ivStorage;

    private Button btSalvar;
    private Button btTirarFoto;

    private EditText etNome;

    private DatabaseReference refImagens;
    private StorageReference storageReference;

    private ArrayAdapter<String> nomesAdapter;
    ActivityCameraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCameraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ivStorage = binding.imgGuardada;
        btSalvar = binding.btnSalvar;
        lvImagens = binding.lisImagens;
        btTirarFoto = binding.btnFotografar;
        etNome = binding.txtNome;

        storageReference = FirebaseStorage.getInstance().getReference();
        refImagens = FirebaseDatabase.getInstance().getReference("imagens");

        btSalvar.setEnabled(true);
        btTirarFoto.setEnabled(true);

        btTirarFoto.setOnClickListener(view13 -> tirarFoto(view13));

        refImagens.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nomesImagens = new ArrayList<String>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    nomesImagens.add(data.child("nome").getValue().toString());
                }
                arrayNomesImagens = new String[nomesImagens.size()];
                nomesImagens.toArray(arrayNomesImagens);

                nomesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, nomesImagens);
                lvImagens.setAdapter(nomesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("LISTAFOTOS: ",error.getMessage());
            }
        });

        lvImagens.setOnItemClickListener((adapterView, view12, i, l) -> {
            String select = lvImagens.getItemAtPosition(i).toString();
            carregaImagem(select);
            etNome.setText(select);
        });

        btSalvar.setOnClickListener(view1 -> {
            BitmapDrawable bmp = (BitmapDrawable) ivStorage.getDrawable();
            Bitmap bitmap = bmp.getBitmap();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] imagemArray = baos.toByteArray();

            final String idImagem = UUID.randomUUID().toString();
            final String nomeImagem = etNome.getText().toString();

            StorageReference refPasta = storageReference.child("imagens");
            StorageReference refImagem = refPasta.child(nomeImagem + ".jpeg");
            UploadTask task = refImagem.putBytes(imagemArray);

            task.addOnFailureListener(CameraActivity.this, (e) -> {
                Toast.makeText(CameraActivity.this, "Falha no upload" + e.getMessage(), Toast.LENGTH_LONG).show();
            }).addOnSuccessListener(CameraActivity.this, taskSnapshot -> {
                Task<Uri> url = taskSnapshot.getMetadata().getReference().getDownloadUrl(); //THREAD DE UPLOAD DE IMAGEM
                while (!url.isComplete())
                    ;
                Toast.makeText(CameraActivity.this, "Sucesso" + url.getResult().toString(), Toast.LENGTH_LONG).show();

                btSalvar.setEnabled(false);
                refImagens.child(idImagem).child("nome").setValue(nomeImagem + ".jpeg");
                nomesAdapter.add(nomeImagem + ".jpeg");
                etNome.setText("Escreva o nome do arquivo");
            });
        });

    }

    private void carregaImagem(String nomeImagem) {
        try {
            StorageReference dirReference = storageReference.child("imagens");
            StorageReference imagemRef = dirReference.child(nomeImagem);

            Task<Uri> url = imagemRef.getDownloadUrl();
            while(!url.isComplete());

            ivStorage = binding.imgGuardada;
            Glide.with(getApplicationContext()).load(url.getResult().toString()).into(ivStorage);
        }catch (Exception e){
            Log.i("APP: ", e.getMessage());
        }
        btSalvar.setEnabled(false);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void tirarFoto(View view) {
        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(fotoIntent.resolveActivity(getPackageManager())!=null){
        startActivityForResult(fotoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivStorage = binding.imgGuardada;
            ivStorage.setImageBitmap(imageBitmap);
            btSalvar.setEnabled(true);
        }
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

    public void home() {
        finish();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}