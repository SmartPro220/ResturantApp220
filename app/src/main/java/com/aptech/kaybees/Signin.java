package com.aptech.kaybees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aptech.kaybees.Admin.AdminDashboard;
import com.aptech.kaybees.Consumer.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends AppCompatActivity {


    TextInputEditText email,password;
    MaterialButton signBtn;
    TextView sigunTxt;

    String adminEmail;
    String adminPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        getSupportActionBar().hide();

        init();



        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().isEmpty())
                {
                    email.setError("Please Enter Email First");
                    return;
                }

                if(password.getText().toString().isEmpty())
                {
                    password.setError("Please Enter Password First");
                    return;
                }

                if(password.length()<6)
                {
                    password.setError("Length must be greater then 6");
                    return;
                }

                if(!email.getText().toString().contains("@"))
                {
                    email.setError("Please Enter Correct email");
                }

                if(email.getText().toString().equals(adminEmail)  &&  password.getText().toString().equals(adminPassword))
                {
                    saveData();
                    Snackbar.make(view,"SignIn SuccessFull",Snackbar.LENGTH_LONG).show();
                    Intent i=new Intent(Signin.this, AdminDashboard.class);

                    startActivity(i);

                    finish();

                }
                else {



                    signin(email.getText().toString(), password.getText().toString());


                }

            }
        });

        sigunTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Signin.this, SignUp.class);
                startActivity(i);
            }
        });


    }

    public  void signin(String email, String pass)
    {
       FirebaseAuth  mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(Signin.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(Signin.this, UserDashboard.class);
                    startActivity(i);
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception eroor) {
                Toast.makeText(Signin.this, eroor.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void saveData()
    {
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext

        myEdit.putBoolean("islogin",true);
// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();

    }

    public void init()
    {
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signBtn=findViewById(R.id.singnBtn);
        sigunTxt=findViewById(R.id.signUpTxt);

        adminEmail="admin@gmail.com";
        adminPassword="1234567";
    }
}