package org.yapp.covey.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.yapp.covey.R;
import org.yapp.covey.fragment.ApplyFragment;
import org.yapp.covey.fragment.HomeFragment;
import org.yapp.covey.fragment.RecruitFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragmentHome = new HomeFragment();
    Fragment fragmentApply = new ApplyFragment();
    Fragment fragmentRecruit = new RecruitFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        setBottomNavigationView(bottomNavigationView);

        changeFragment(fragmentHome);
        setStatusBarColor(true);
    }

    private void setBottomNavigationView(BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_home:
                        changeFragment(fragmentHome);
                        setStatusBarColor(true);
                        break;

                    case R.id.menu_apply:
                        changeFragment(fragmentApply);
                        setStatusBarColor(false);
                        break;
                    case R.id.menu_recruit:
                        changeFragment(fragmentRecruit);
                        setStatusBarColor(false);
                        break;
                    case R.id.menu_profile:

                }
                return true;
            }
        });
    }
    private void setStatusBarColor(boolean enabled) {
        View view = getWindow().getDecorView();
        if (enabled) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.tomato));

        }else{
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    public void changeFragment(Fragment fragment){
        fragmentManager.beginTransaction().replace(R.id.layout_main,fragment).commit();    }
}
