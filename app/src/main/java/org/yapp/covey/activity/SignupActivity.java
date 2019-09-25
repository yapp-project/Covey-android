package org.yapp.covey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import org.yapp.covey.R;
import org.yapp.covey.fragment.*;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Signup_LoginFragment loginFragment = new Signup_LoginFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 여기에서 프래그먼트 트랜잭션, 백스택, 애니메이션 등을 설정합니다.
        // -----------------------------------------------------------------
        fragmentTransaction.add(R.id.fragment_container_signup, loginFragment);
        // -----------------------------------------------------------------
        fragmentTransaction.commit();
    }
}
