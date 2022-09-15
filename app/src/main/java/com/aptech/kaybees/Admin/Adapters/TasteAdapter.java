package com.aptech.kaybees.Admin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aptech.kaybees.Admin.EditTaste;
import com.aptech.kaybees.Admin.Model.TasteModel;
import com.aptech.kaybees.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TasteAdapter extends RecyclerView.Adapter<TasteAdapter.ViewHolder> {

    Context context;

    ArrayList<TasteModel> tasteModelArrayList;
    ArrayList<String> keyList;
    public TasteAdapter(Context context, ArrayList<TasteModel> tasteModelArrayList,ArrayList<String> keyList) {
        this.context = context;
        this.tasteModelArrayList = tasteModelArrayList;
        this.keyList=keyList;
    }

    @NonNull
    @Override
    public TasteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.taste_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasteAdapter.ViewHolder holder, int position) {

        String name=tasteModelArrayList.get(position).getName();
        String price=tasteModelArrayList.get(position).getPrice();
        String disprice=tasteModelArrayList.get(position).getDisPrice();
        String quan=tasteModelArrayList.get(position).getQuantity();
        String des=tasteModelArrayList.get(position).getDescription();

        holder.name.setText(name);
        holder.price.setText("Rs "+price);
        holder.disPrice.setText("Rs "+disprice);
        holder.quatity.setText("Quantity "+quan);
        holder.desc.setText(des);
// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        String key=keyList.get(position);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("taste").child(key).removeValue();
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, EditTaste.class);
                i.putExtra("name",name);
                i.putExtra("price",price);
                i.putExtra("disprice",disprice);
                i.putExtra("quantity",quan);
                i.putExtra("desc",des);
                i.putExtra("key",key);

                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return tasteModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,desc,quatity,disPrice;
        ImageView deleteBtn,editBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tname);
            price=itemView.findViewById(R.id.priiii);
            desc=itemView.findViewById(R.id.tdesc);
            quatity=itemView.findViewById(R.id.tquant);
            disPrice=itemView.findViewById(R.id.tdprice);
            deleteBtn=itemView.findViewById(R.id.deletebtn);
            editBtn=itemView.findViewById(R.id.editebtn);
        }
    }
}
