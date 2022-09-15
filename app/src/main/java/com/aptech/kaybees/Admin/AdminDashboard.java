package com.aptech.kaybees.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.aptech.kaybees.Admin.Fragments.AddTaste;
import com.aptech.kaybees.Admin.Fragments.ComplteOrder;
import com.aptech.kaybees.Admin.Fragments.PendingOrder;
import com.aptech.kaybees.Admin.Fragments.ViewTaste;
import com.aptech.kaybees.R;
import com.aptech.kaybees.Signin;
import com.google.android.material.navigation.NavigationView;

public class AdminDashboard extends AppCompatActivity {

    NavigationView mynav;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        init();
        openFragment(new AddTaste());



        mynav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(item.getItemId()==R.id.addTaste)
                {
                 openFragment(new AddTaste());
                }
                else if(item.getItemId()==R.id.viewTaste)
                {
                    openFragment(new ViewTaste());
                }

                else if(item.getItemId()==R.id.penOrder)
                {
                    openFragment(new PendingOrder());
                }

                else if(item.getItemId()==R.id.compOrder)
                {
                    openFragment(new ComplteOrder());
                }
                else if(item.getItemId()==R.id.signoutbtn)
                {

                    saveData();
                    Intent i=new Intent(AdminDashboard.this, Signin.class);
                    startActivity(i);
                    finish();
                }
                drawerLayout.close();


                return false;
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

        myEdit.putBoolean("islogin",false);
// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();

    }

    public  void openFragment(Fragment f){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.adminContainer,f);

        ft.commit();

    }

    public  void  init()
    {
        mynav=findViewById(R.id.mynav);
        drawerLayout=findViewById(R.id.drawer);
    }
}