package com.aptech.kaybees.Consumer.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aptech.kaybees.R;
import com.aptech.kaybees.Signin;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class EditProfile extends Fragment {



    MaterialButton materialButton;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_edit_profile, container, false);


        materialButton=view.findViewById(R.id.signout);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(getContext(), Signin.class));

                getActivity().finish();
            }
        });

        return view;
    }
}