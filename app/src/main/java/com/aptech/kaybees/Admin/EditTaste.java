package com.aptech.kaybees.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aptech.kaybees.Admin.Model.TasteModel;
import com.aptech.kaybees.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditTaste extends AppCompatActivity {
    TextInputEditText tname,tdesc,tprice,tdisPrice,tquatity;

    MaterialButton uploadtast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_taste);
        init();

        String name=getIntent().getExtras().getString("name");
        String price=getIntent().getExtras().getString("price");
        String disprice=getIntent().getExtras().getString("disprice");
        String quant=getIntent().getExtras().getString("quantity");
        String key=getIntent().getExtras().getString("key");
        String disc=getIntent().getExtras().getString("desc");

        tname.setText(name);
        tdesc.setText(disc);
        tprice.setText(price);
        tdisPrice.setText(disprice);
        tquatity.setText(quant);

        uploadtast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();

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

                myRef.child("taste").child(key).setValue(tasteModel);

                finish();


            }
        });

    }

    private void init()
    {
        tname= findViewById(R.id.tastename);
        tdesc= findViewById(R.id.taseteDescrip);
        tprice= findViewById(R.id.tastePrice);
        tdisPrice= findViewById(R.id.tastediscPrice);
        tquatity= findViewById(R.id.tasteQuantity);
        uploadtast= findViewById(R.id.uploadtast);
    }
}