package com.upc.pichanguito;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.pichanguito.entidad.Product;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.MyViewHolder> {

    private Context context;
    private List<Product> listaProduct = new ArrayList<>();

    public AdaptadorPersonalizado(Context context, List<Product> listaProduct){
        this.context = context;
        this.listaProduct = listaProduct;
    }

    @NonNull
    @Override
    public AdaptadorPersonalizado.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.filaNombre.setText(listaProduct.get(position).getNombre()+"");
        holder.filaCodigo.setText(listaProduct.get(position).getCodigo()+"");
        holder.filaDescripcion.setText(listaProduct.get(position).getDescripcion()+"");
        holder.filaAncho.setText(listaProduct.get(position).getAncho()+"");
        holder.filaLargo.setText(listaProduct.get(position).getLargo()+"");
        holder.filaPrecio.setText(listaProduct.get(position).getPrecio()+"");
        holder.filaCambio.setText(listaProduct.get(position).getCambio()+"");
        holder.filaCategoria.setText(listaProduct.get(position).getCategoria()+"");
        holder.filaSede.setText(listaProduct.get(position).getSede()+"");
        holder.filaEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("id",listaProduct.get(position).getId()+"");
                intent.putExtra("nombre",listaProduct.get(position).getNombre()+"");
                intent.putExtra("codigo",listaProduct.get(position).getCodigo()+"");
                intent.putExtra("descripcion",listaProduct.get(position).getDescripcion()+"");
                intent.putExtra("ancho",listaProduct.get(position).getAncho()+"");
                intent.putExtra("largo",listaProduct.get(position).getLargo()+"");
                intent.putExtra("precio",listaProduct.get(position).getPrecio()+"");
                intent.putExtra("cambio",listaProduct.get(position).getCambio()+"");
                intent.putExtra("categoria",listaProduct.get(position).getCategoria()+"");
                intent.putExtra("sede",listaProduct.get(position).getSede()+"");

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView filaNombre, filaCodigo, filaDescripcion, filaAncho, filaLargo,
                filaPrecio, filaCambio, filaCategoria, filaSede;
        ImageButton filaEditar, filaEliminar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            filaNombre = itemView.findViewById(R.id.filaNombre);
            filaCodigo = itemView.findViewById(R.id.filaCodigo);
            filaDescripcion = itemView.findViewById(R.id.filaDescripcion);
            filaAncho = itemView.findViewById(R.id.filaAncho);
            filaLargo = itemView.findViewById(R.id.filaLargo);
            filaPrecio = itemView.findViewById(R.id.filaPrecio);
            filaCambio = itemView.findViewById(R.id.filaCambio);
            filaCategoria = itemView.findViewById(R.id.filaCategoria);
            filaSede = itemView.findViewById(R.id.filaSede);
            filaEditar = itemView.findViewById(R.id.filaEditar);
            filaEliminar = itemView.findViewById(R.id.filaEliminar);
        }
    }
}
