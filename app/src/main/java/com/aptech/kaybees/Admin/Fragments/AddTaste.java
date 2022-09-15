package com.aptech.kaybees.Admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aptech.kaybees.Admin.Model.TasteModel;
import com.aptech.kaybees.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTaste extends Fragment {


    View view;

    TextInputEditText tname,tdesc,tprice,tdisPrice,tquatity;

    MaterialButton uploadtast;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_taste, container, false);

        init();



        uploadtast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();

                if(tname.getText().toString().isEmpty())
                {
                    tname.setError("Fill First");
                    return;
                }
                if(tdesc.getText().toString().isEmpty())
                {
                    tdesc.setError("Fill First");
                    return;
                }
                if(tprice.getText().toString().isEmpty())
                {
                    tprice.setError("Fill First");
                    return;
                }
                if(tdisPrice.getText().toString().isEmpty())
                {
                    tdisPrice.setError("Fill First");
                    return;
                }
                if(tquatity.getText().toString().isEmpty())
                {
                    tquatity.setError("Fill First");
                    return;
                }

               // Write a message to the database

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();


                TasteModel tasteModel=new TasteModel(tname.getText().toString(),tdesc.getText().toString(),
                        tprice.getText().toString(),tdisPrice.getText().toString(),tquatity.getText().toString());

                myRef.child("taste").push().setValue(tasteModel);


            }
        });


        return view;
    }

    private void init()
    {
        tname=view.findViewById(R.id.tastename);
        tdesc=view.findViewById(R.id.taseteDescrip);
        tprice=view.findViewById(R.id.tastePrice);
        tdisPrice=view.findViewById(R.id.tastediscPrice);
        tquatity=view.findViewById(R.id.tasteQuantity);
        uploadtast=view.findViewById(R.id.uploadtast);
    }
}