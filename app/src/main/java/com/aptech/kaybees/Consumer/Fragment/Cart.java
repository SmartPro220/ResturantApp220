package com.aptech.kaybees.Consumer.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aptech.kaybees.Admin.Model.TasteModel;
import com.aptech.kaybees.Consumer.Adapter.MyCartAdapter;
import com.aptech.kaybees.Consumer.Model.AddToCartData;
import com.aptech.kaybees.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Cart extends Fragment {



    View view;

    RecyclerView myCartRec;
    ArrayList<AddToCartData> addToCartDataArrayList=new ArrayList<>();


    FirebaseDatabase firebaseDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_cart, container, false);

        init();

        fetCartData();
        return  view;
    }

    public  void init(){
        myCartRec=view.findViewById(R.id.myCartRec);;

//        firebaseDatabase.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);

        myCartRec.setLayoutManager(linearLayoutManager);


    }

    public void fetCartData()
    {

        DatabaseReference myRef=firebaseDatabase.getReference("cart");


        String userId= FirebaseAuth.getInstance().getUid();


        myRef.orderByChild("userId").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                addToCartDataArrayList.clear();

                for(DataSnapshot d:snapshot.getChildren())
                {
                    AddToCartData obj=d.getValue(AddToCartData.class);

                    addToCartDataArrayList.add(obj);
                }
                Collections.reverse(addToCartDataArrayList);

                MyCartAdapter myCartAdapter=new MyCartAdapter(addToCartDataArrayList);

                myCartRec.setAdapter(myCartAdapter);

                //myCartCount.setText(addToCartDataArrayList.size()+"");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e("id not rec",error.getMessage());
            }
        });

    }
}