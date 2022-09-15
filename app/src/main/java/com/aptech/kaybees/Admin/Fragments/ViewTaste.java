package com.aptech.kaybees.Admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aptech.kaybees.Admin.Adapters.TasteAdapter;
import com.aptech.kaybees.Admin.Model.TasteModel;
import com.aptech.kaybees.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class ViewTaste extends Fragment {


    RecyclerView mytaste;
    View view;

    ArrayList<TasteModel> tasteModelArrayList=new ArrayList<>();

    ArrayList<String> keyList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_view_taste, container, false);

        init();


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        mytaste.setLayoutManager(linearLayoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        myRef.child("taste").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                tasteModelArrayList.clear();

                keyList.clear();
                Log.e("l;fmsd;l",String.valueOf(snapshot.getKey()));


                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    keyList.add(dataSnapshot.getKey()) ;


                    TasteModel tasteModel=dataSnapshot.getValue(TasteModel.class);
                    tasteModelArrayList.add(tasteModel);
                }
                Collections.reverse(tasteModelArrayList);
                Collections.reverse(keyList);
                TasteAdapter tasteAdapter=new TasteAdapter(getContext(),tasteModelArrayList,keyList);

                mytaste.setAdapter(tasteAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return  view;
    }

    private void init()
    {
        mytaste=view.findViewById(R.id.mytaste);
    }
}