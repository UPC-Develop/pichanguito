package com.upc.pichanguito.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upc.pichanguito.entidad.Product;
import com.upc.pichanguito.util.Constantes;
import com.upc.pichanguito.util.Esquema;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    Esquema esquema;
    private Context context;
    SQLiteDatabase db;

    public ProductDao(Context context){
        this.context = context;
        esquema = new Esquema(context);
    }

    public void abrirBD(){

        db = esquema.getWritableDatabase();
    }

    public String registrarProducto(Product product){
        String respuesta = "";
        try {
            ContentValues valores = new ContentValues();

            valores.put("nombre", product.getNombre());
            valores.put("codigo", product.getCodigo());
            valores.put("descripcion", product.getDescripcion());
            valores.put("ancho", product.getAncho());
            valores.put("largo", product.getLargo());
            valores.put("precio", product.getPrecio());
            valores.put("cambio", product.getCambio());
            valores.put("categoria", product.getCategoria());
            valores.put("sede", product.getSede());

            long r = db.insert(Constantes.NOMBRE_TABLA,null,valores);
            if(r == -1){
                respuesta = "Error al insertar";
            }else {
                respuesta = "Se registró correctamente";
            }

        }catch (Exception e){
            Log.d("==>" , e.getMessage());
        }
        return respuesta;
    }

    public List<Product> getAllProduct(){
        List<Product> listaProduct = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM "+Constantes.NOMBRE_TABLA,null);
            while (c.moveToNext()){
                listaProduct.add(new Product(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),
                        c.getInt(4),c.getInt(5),c.getInt(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10)));
            }
        }catch (Exception e){
            Log.d("==>",e.getMessage());
        }
        return listaProduct;
    }
}
