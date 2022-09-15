package com.aptech.kaybees.Consumer.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewKt;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aptech.kaybees.Admin.Model.TasteModel;
import com.aptech.kaybees.Consumer.Adapter.FoodRecAdapter;
import com.aptech.kaybees.Consumer.Model.AddToCartData;
import com.aptech.kaybees.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends Fragment {



    RecyclerView recyclerView;
    View view;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference myRef;


    ArrayList<TasteModel> tasteModelArrayList=new ArrayList<>();

    ArrayList<String> keys=new ArrayList<>();

    ProgressDialog progress;

    TextView myCartCount;

    ArrayList<AddToCartData> addToCartDataArrayList=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        init();


        progress.show();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                progress.dismiss();

                for (DataSnapshot child: snapshot.getChildren())
                {Log.e("dddd",child.getKey());

                    keys.add(child.getKey());

                    TasteModel tasteModel=child.getValue(TasteModel.class);

                    tasteModelArrayList.add(tasteModel);
                }

                FoodRecAdapter foodRecAdapter=new FoodRecAdapter(getContext(),tasteModelArrayList,keys);
                recyclerView.setAdapter(foodRecAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progress.dismiss();
                Log.e("error",error.getMessage());
            }
        });


        fetCartData();
        return view;
    }


    public void fetCartData()
    {

        myRef=firebaseDatabase.getReference("cart");


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


                myCartCount.setText(addToCartDataArrayList.size()+"");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e("id not rec",error.getMessage());
            }
        });

    }
    private void init()
    {

        myCartCount=view.findViewById(R.id.myCartCount);



        firebaseDatabase=FirebaseDatabase.getInstance();

        myRef=firebaseDatabase.getReference().child("taste");



        progress=new ProgressDialog(getContext());

        progress.setTitle("Wait");
        progress.setMessage("Food Fetching....");
        progress.setCancelable(false);

        recyclerView=view.findViewById(R.id.foodRec);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);


    }
}