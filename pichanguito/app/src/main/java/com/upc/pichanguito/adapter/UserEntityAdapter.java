package com.upc.pichanguito.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.pichanguito.R;
import com.upc.pichanguito.SeeUserActivity;
import com.upc.pichanguito.entity.UserEntity;

import java.util.ArrayList;

public class UserEntityAdapter extends RecyclerView.Adapter <UserEntityAdapter.UserViewHolder>{

    ArrayList<UserEntity> userEntities;

    public  UserEntityAdapter(ArrayList<UserEntity> userEntities){
        this.userEntities = userEntities;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user,null, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.userNameTextView.setText(userEntities.get(position).getUser_name());
        holder.firstNameTextView.setText(userEntities.get(position).getFirst_name());
        holder.lastNameTextView.setText(userEntities.get(position).getLast_name());
        holder.documentNumberTextView.setText(userEntities.get(position).getDocument_number());

    }

    @Override
    public int getItemCount() {
        return userEntities.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView  userNameTextView, firstNameTextView, lastNameTextView, documentNumberTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameView);
            firstNameTextView = itemView.findViewById(R.id.firstNameView);
            lastNameTextView = itemView.findViewById(R.id.lastNameView);
            documentNumberTextView = itemView.findViewById(R.id.documentNumberView);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, SeeUserActivity.class);
                    intent.putExtra("user_id", userEntities.get(getAdapterPosition()).getUser_id());
                    context.startActivity(intent);

                }
            });


        }
    }
}
