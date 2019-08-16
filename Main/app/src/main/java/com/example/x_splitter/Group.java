
package com.example.x_splitter;

        import android.content.Intent;
import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
        import android.widget.EditText;

        import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Group extends AppCompatActivity {
    FloatingActionButton fab_add;
    EditText searchGroup;


    private static final int Activity_num = 1; // for recognizing menu item number

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Group.this,FAB_Menu_page.class));
            }
        });

        searchGroup = findViewById(R.id.edit_text_search);
        //searchGroup.setText("neha");

        ArrayList<ModelGroup> group = getGroupData();

        RecyclerView recyclerView = findViewById(R.id.group_recycler_view);
        AdapterGroup adapterGroup = new AdapterGroup(this,group);
        recyclerView.setAdapter(adapterGroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        searchGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapterGroup.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        setBottomNavigationView();
    }

    public static ArrayList<ModelGroup> getGroupData(){
        ArrayList<ModelGroup> modelGroups = new ArrayList<>();
        modelGroups.clear();
        FirebaseDatabase.getInstance().getReference("GroupName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Map<String, Object> groupdata = (Map<String, Object>) snapshot.getValue();
                    String groupname = (String) Objects.requireNonNull(groupdata).get("GroupName");
                    String ID = (String) Objects.requireNonNull(groupdata).get("ID");

//                    Intent id = new Intent(Group.this, FragmentEvent.class);
//                    id.putExtra("ID", ID);
//                    startActivity(id);

                    modelGroups.add(new ModelGroup(R.mipmap.ic_applogo, groupname, ID,  "1", "2"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        modelGroups.add(new ModelGroup(R.mipmap.ic_applogo,"Kathford","4","2"));
//        modelGroups.add(new ModelGroup(R.drawable.undraw_group_selfie_ijc6,"Aimless","2","1"));
//        modelGroups.add(new ModelGroup(R.drawable.undraw_data_report_bi6l,"Lolwa","3","0"));
//        modelGroups.add(new ModelGroup(R.drawable.undraw_time_management_30iu,"Gang","3","3"));
//        modelGroups.add(new ModelGroup(R.drawable.undraw_make_it_rain_iwk4,"Adhikari","3","3"));
//        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher_foreground,"Maharjan","4","3"));
//        modelGroups.add(new ModelGroup(R.drawable.undraw_having_fun_iais,"K.C.","3","3"));
//        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher,"Kirat","3","2"));
//        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher_foreground,"Mainali","1","3"));
//        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher,"Acchami","4","2"));
//        modelGroups.add(new ModelGroup(R.mipmap.ic_applogo,"rayamajhi","1","0"));

        return modelGroups;
    }

    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(Group.this, Home.class); // Activity_num = 0
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_group:
                        break;

                    case R.id.ic_event:
                        Intent intent3 = new Intent(Group.this, Event.class); // Activity_num = 3
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_profile:
                        Intent intent4 = new Intent(Group.this, Profile.class); // Activity_num = 4
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        break;

                }
                return false;
            }
        });
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }
}