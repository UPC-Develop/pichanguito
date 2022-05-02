package com.upc.pichanguito;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.upc.pichanguito.entidad.Product;
import com.upc.pichanguito.modelo.ProductDao;

public class GuardarActivity extends AppCompatActivity {

    EditText txtNombreProduct, txtCodigoProduct, txtDescripcionProduct, txtAnchoProduct, txtLargoProduct,
             txtPrecioProduct, txtCambioProduct, txtCategoriaProduct, txtEstadoProduct, txtSedeProduct;
    Button btnRegistrar;

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);
        asignarReferencias();

    }

    @SuppressLint("WrongViewCast")
    private void asignarReferencias(){
        txtNombreProduct = findViewById(R.id.txtNombreProduct);
        txtCodigoProduct = findViewById(R.id.txtCodigoProduct);
        txtDescripcionProduct = findViewById(R.id.txtDescripcionProduct);
        txtAnchoProduct = findViewById(R.id.txtAnchoProduct);
        txtLargoProduct = findViewById(R.id.txtLargoProduct);
        txtPrecioProduct = findViewById(R.id.txtPrecioProduct);
        txtCambioProduct = findViewById(R.id.txtCambioProduct);
        txtCategoriaProduct = findViewById(R.id.txtCategoriaProduct);
        txtEstadoProduct = findViewById(R.id.txtEstadoProduct);
        txtSedeProduct = findViewById(R.id.txtSedeProduct);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturarDatos();
                ProductDao productDao = new ProductDao(GuardarActivity.this);
                productDao.abrirBD();
                String mensaje = productDao.registrarProducto(product);

                AlertDialog.Builder ventana = new AlertDialog.Builder(GuardarActivity.this);
                ventana.setTitle("Mensaje Informativo");
                ventana.setMessage(mensaje);
                ventana.setPositiveButton("Aceptar",null);

                ventana.create().show();


            }
        }); 
    }
    private void capturarDatos(){
        String nombre = txtNombreProduct.getText().toString();
        String codigo = txtCodigoProduct.getText().toString();
        String descripcion = txtDescripcionProduct.getText().toString();
        int ancho = Integer.parseInt(txtAnchoProduct.getText().toString());
        int largo = Integer.parseInt(txtLargoProduct.getText().toString());
        int precio = Integer.parseInt(txtPrecioProduct.getText().toString());
        String cambio = txtCambioProduct.getText().toString();
        String categoria = txtCategoriaProduct.getText().toString();
        String estado = txtEstadoProduct.getText().toString();
        String sede = txtSedeProduct.getText().toString();
        product = new Product(nombre,codigo,descripcion,cambio,categoria,estado,sede , ancho,largo,precio);



    }
}