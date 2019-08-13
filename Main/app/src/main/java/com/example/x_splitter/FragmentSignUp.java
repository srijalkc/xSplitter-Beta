package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentSignUp extends Fragment {
    View view;

    public FragmentSignUp(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.get_started_signup_fragment,container,false);
        Button btnSignUpEmail = view.findViewById(R.id.btn_get_started_signup_email);
        btnSignUpEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentSignUp.this.getActivity(),SignupActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
