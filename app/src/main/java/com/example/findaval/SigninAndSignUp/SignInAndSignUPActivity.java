package com.example.findaval.SigninAndSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import com.example.findaval.Adapter.SigninAndSignUPViewPagerAdapter;
import com.example.findaval.R;
import com.example.findaval.databinding.ActivitySignInAndSignUpactivityBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SignInAndSignUPActivity extends AppCompatActivity {

    private ActivitySignInAndSignUpactivityBinding signInAndSignUpactivityBinding;
    SignInFragment signInFragment;
    SignUPFragment signUPFragment;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    SigninAndSignUPViewPagerAdapter signinAndSignUPViewPagerAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    signInAndSignUpactivityBinding = ActivitySignInAndSignUpactivityBinding.inflate(getLayoutInflater());

        View view = signInAndSignUpactivityBinding.getRoot();

        setContentView(view);

        signInFragment = new SignInFragment();
        signUPFragment = new SignUPFragment();

        viewPager2 = signInAndSignUpactivityBinding.viewPager2;
        tabLayout = signInAndSignUpactivityBinding.tabLayout;
        toolbar = signInAndSignUpactivityBinding.toolbar;

     signinAndSignUPViewPagerAdapter = new SigninAndSignUPViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

     viewPager2.setAdapter(signinAndSignUPViewPagerAdapter);

     new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
         @Override
         public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

             if(position ==0){
                 tab.setText("Sign Up");
             }else {
                 tab.setText("Sign In");
             }
         }
     }).attach();

     tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
         @Override
         public void onTabSelected(TabLayout.Tab tab) {
             viewPager2.setCurrentItem(tab.getPosition());
         }

         @Override
         public void onTabUnselected(TabLayout.Tab tab) {

         }

         @Override
         public void onTabReselected(TabLayout.Tab tab) {

         }
     });

    }


}