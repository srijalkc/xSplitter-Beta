
package com.example.x_splitter;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.design.widget.BottomNavigationView;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
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
        BottomNavigationHelper.enableNavigation(Group.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }
}