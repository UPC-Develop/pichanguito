package com.upc.pichanguito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upc.pichanguito.entidad.Product;
import com.upc.pichanguito.modelo.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class ListarProductActivity extends AppCompatActivity {

    RecyclerView rvProduct;
    FloatingActionButton btnNuevo;
    ProductDao productDao = new ProductDao(this);
    List<Product> listaProduct = new ArrayList<>();
    AdaptadorPersonalizado adaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_product);
        asignarReferencias();
        productDao.abrirBD();
        mostrarProduct();
    }
    
    private void mostrarProduct(){
        listaProduct = productDao.getAllProduct();
        adaptador = new AdaptadorPersonalizado(this,listaProduct);
        rvProduct.setAdapter(adaptador);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        
    }

    private void asignarReferencias(){
        rvProduct = findViewById(R.id.rvProduct);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarProductActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}