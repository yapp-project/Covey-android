package org.yapp.covey.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.yapp.covey.R;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        setBottomNavigationView(bottomNavigationView);

        fragmentManager = getSupportFragmentManager();

    }

    private void setBottomNavigationView(BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_home:
                    case R.id.menu_apply:
                    case R.id.menu_recruit:
                    case R.id.menu_profile:
                        return true;
                }
                return false;
            }
        });
    }
    public void changeFragment(Fragment fragment){
        fragmentManager.beginTransaction().replace(R.id.layout_main,fragment).commit();
    }
}
