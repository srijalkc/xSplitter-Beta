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

public class FragmentLogin extends Fragment {
    View view;
    public FragmentLogin(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.get_started_login_fragment,container,false);
        Button btnLogInEmail = view.findViewById(R.id.btn_get_started_login_email);

        btnLogInEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FragmentLogin.this.getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
