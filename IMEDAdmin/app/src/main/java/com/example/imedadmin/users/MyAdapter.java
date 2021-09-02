package com.example.imedadmin.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imedadmin.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<UserData> list;

    public MyAdapter(Context context, ArrayList<UserData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UserData userData= list.get(position);
        holder.uname.setText(userData.getName());
        holder.uemail.setText(userData.getEmail());
        holder.upassword.setText(userData.getPassword());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView uname,uemail,upassword;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            uname= itemView.findViewById(R.id.tvName);
            uemail= itemView.findViewById(R.id.tvEmail);
            upassword= itemView.findViewById(R.id.tvPassword);
        }
    }
}
