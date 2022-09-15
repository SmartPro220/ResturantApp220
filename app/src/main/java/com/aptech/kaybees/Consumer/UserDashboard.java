package com.aptech.kaybees.Consumer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.aptech.kaybees.Consumer.Fragment.Cart;
import com.aptech.kaybees.Consumer.Fragment.EditProfile;
import com.aptech.kaybees.Consumer.Fragment.Home;
import com.aptech.kaybees.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserDashboard extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        getSupportActionBar().hide();

        init();

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.user_container,new Home());
        ft.commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.home)
                {
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.user_container,new Home());
                    ft.commit();
                    return true;
                }
                else if(item.getItemId()==R.id.cart)
                {
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.user_container,new Cart());
                    ft.commit();
                    return true;
                }
                else if(item.getItemId()==R.id.profile)
                {
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.user_container,new EditProfile());
                    ft.commit();
                    return true;
                }


                return false;
            }
        });


    }
    public void init(){
        bottomNavigationView=findViewById(R.id.bottom_nav_bar);
    }
}