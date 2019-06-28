
package com.example.x_splitter;

        import android.content.Intent;
        import android.os.Bundle;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import java.util.ArrayList;

public class Group extends AppCompatActivity {
    FloatingActionButton fab_add;


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

        RecyclerView recyclerView = findViewById(R.id.group_recycler_view);
        AdapterGroup adapterGroup = new AdapterGroup(this,getGroupData());
        recyclerView.setAdapter(adapterGroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setBottomNavigationView();
    }

    public static ArrayList<ModelGroup> getGroupData(){
        ArrayList<ModelGroup> modelGroups = new ArrayList<>();

        modelGroups.add(new ModelGroup(R.mipmap.ic_applogo,"Kathford","4","2"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_applogo_round,"Aimless","2","1"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher,"Lolwa","3","0"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher_foreground,"Gang","3","3"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_applogo_round,"Adhikari","3","3"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher_foreground,"Maharjan","4","3"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_applogo,"K.C.","3","3"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher,"Kirat","3","2"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher_foreground,"Mainali","1","3"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_launcher,"Acchami","4","2"));
        modelGroups.add(new ModelGroup(R.mipmap.ic_applogo,"rayamajhi","1","0"));

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