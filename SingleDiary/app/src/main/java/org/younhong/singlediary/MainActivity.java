package org.younhong.singlediary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements OnTabItemSelectedListener{
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(
                R.id.container, fragment1).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch(menuItem.getItemId()) {
                            case R.id.tab1:
                                Toast.makeText(getApplicationContext(), "First Tab", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(
                                        R.id.container, fragment1).commit();
                                return true;
                            case R.id.tab2:
                                Toast.makeText(getApplicationContext(), "Second Tab", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(
                                        R.id.container, fragment2).commit();
                                return true;
                            case R.id.tab3:
                                Toast.makeText(getApplicationContext(), "Third Tab", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(
                                        R.id.container, fragment3).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

    public void onTabSelected(int position){
        if (position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigation.setSelectedItemId(R.id.tab3);
        }
    }
}