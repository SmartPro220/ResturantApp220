package com.aptech.kaybees.Consumer.Adapter;

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
import com.aptech.kaybees.Consumer.Model.AddToCartData;
import com.aptech.kaybees.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    ArrayList<AddToCartData> addToCartDataArrayList=new ArrayList<>();

    public MyCartAdapter(ArrayList<AddToCartData> addToCartDataArrayList) {
        this.addToCartDataArrayList = addToCartDataArrayList;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {


        String title=addToCartDataArrayList.get(position).getTitle();
        String price=addToCartDataArrayList.get(position).getDisPrice();
        String totalPrice=addToCartDataArrayList.get(position).getTotalPRicee();
        String quantity=addToCartDataArrayList.get(position).getQuantity();


        holder.title.setText(title);
        holder.actualPrice.setText(price);
        holder.quantity.setText("Quantity: "+quantity);
        holder.totalPrice.setText("Rs: "+totalPrice);

    }

    @Override
    public int getItemCount() {
        return addToCartDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,actualPrice,quantity,totalPrice;

        MaterialButton checout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.cctitle);
            actualPrice=itemView.findViewById(R.id.ccActutalPrice);
            quantity=itemView.findViewById(R.id.ccquantity);
            totalPrice=itemView.findViewById(R.id.cctotalPrice);
            checout=itemView.findViewById(R.id.cccheckout);

        }
    }
}
