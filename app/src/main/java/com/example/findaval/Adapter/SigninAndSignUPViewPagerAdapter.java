package com.example.findaval.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.findaval.SigninAndSignUp.SignInFragment;
import com.example.findaval.SigninAndSignUp.SignUPFragment;

public class SigninAndSignUPViewPagerAdapter extends FragmentStateAdapter {

    public SigninAndSignUPViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new SignUPFragment();
            case 1:
                return new SignInFragment();

            default:break;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
