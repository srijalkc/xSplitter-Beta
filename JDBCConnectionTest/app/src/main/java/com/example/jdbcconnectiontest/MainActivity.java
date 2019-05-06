package com.example.jdbcconnectiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button buttonGetData;
    private ListView listView;
    SimpleAdapter AD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGetData = (Button)findViewById(R.id.button_get_data);
        listView = (ListView)findViewById(R.id.listview);
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Map<String, String>> myDataList = null;
                GetData myData = new GetData();
                myDataList = myData.getData();

                String[] fromwhere = {"Username", "Email" , "Password"};
                int[] viewwhere = {R.id.Username, R.id.Email, R.id.Password};
                AD = new SimpleAdapter(MainActivity.this, myDataList, R.layout.list_template, fromwhere, viewwhere);
                listView.setAdapter(AD);

            }
        });
    }
}
