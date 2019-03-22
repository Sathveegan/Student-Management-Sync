package com.example.studentmanagementsync;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.studentmanagementsync.fragment.OfflineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.studentmanagementsync.fragment.HomeFragment;
import com.example.studentmanagementsync.fragment.OnlineFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    ft.replace(R.id.id_fragment, new HomeFragment());
                    ft.commit();
                    return true;
                case R.id.navigation_offline:
                    ft.replace(R.id.id_fragment, new OfflineFragment());
                    ft.commit();
                    return true;
                case R.id.navigation_online:
                    ft.replace(R.id.id_fragment, new OnlineFragment());
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.id_fragment, new HomeFragment());
            ft.commit();
        }
    }



}
