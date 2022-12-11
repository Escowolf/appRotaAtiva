package com.example.approtaativa.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Veiculo {
    private String id;
    private String placa;
    private String renavan;
    private Long ano;
    private String cor;

    public Veiculo(){
        //
    }

    public Veiculo(String id, String placa, String renavan, Long ano, String cor) {
        this.id = id;
        this.placa = placa;
        this.renavan = renavan;
        this.ano = ano;
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRenavan() {
        return renavan;
    }

    public void setRenavan(String renavan) {
        this.renavan = renavan;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long  ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void salvarVeiculo() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("veiculos").child(getId()).setValue(this);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", renavan='" + renavan + '\'' +
                ", ano='" + ano + '\'' +
                ", cor='" + cor + '\'' +
                '}';
    }

}
