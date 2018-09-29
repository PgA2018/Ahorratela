package com.example.juan.ahorratela.Activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.juan.ahorratela.Fragments.ComprasFragment;
import com.example.juan.ahorratela.Fragments.LugaresFragment;
import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.R;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    LugaresFragment lugaresFragment = new LugaresFragment();
    ComprasFragment comprasFragment = new ComprasFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment, comprasFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment, lugaresFragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment, lugaresFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment, comprasFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
}
