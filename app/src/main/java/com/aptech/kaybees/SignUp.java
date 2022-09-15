package com.aptech.kaybees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    TextInputEditText name,phone,email,password;
     FirebaseAuth mAuth;
    MaterialButton signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name.getText().toString().isEmpty())
                {
                    name.setError("Fill First");
                    return;
                }
                if(phone.getText().toString().isEmpty())
                {
                    phone.setError("Fill First");
                    return;
                }
                if(phone.getText().length()<11)
                {
                    phone.setError("provide 11 digit no");
                    return;
                }
                if(email.getText().toString().isEmpty())
                {
                    email.setError("Fill First");
                    return;
                }
                if(!email.getText().toString().contains("@"))
                {
                    email.setError("Enter a Correct Email");
                    return;
                }

                if(password.getText().toString().isEmpty())
                {
                    password.setError("Fill First");
                    return;
                }
                if(password.getText().length()<6)
                {
                    password.setError("Password greater 6 digit");
                    return;
                }




                mAuth.createUserWithEmailAndPassword
                        (email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {


                            Toast.makeText(SignUp.this, "Registeration Successfully", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(SignUp.this,Signin.class);
                            startActivity(i);
                            finish();


                        }
                     /*   else
                        {
                            Toast.makeText(SignUp.this, "Something Wrong Try Again", Toast.LENGTH_SHORT).show();
                        }
*/
                    }
                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });

            }
        });

    }

    public void init()
    {
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signupBtn=findViewById(R.id.singUpBtn);
    }
}