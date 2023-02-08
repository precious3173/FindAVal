package com.example.findaval;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {

//    public ArrayList<Fragment>fragmentArrayList = new ArrayList();
//    public ArrayList<String>fragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
//        this.fragmentArrayList = fragmentArrayList;
//        this.fragmentTitleList = fragmentTitleList;
    }


//    public String getTabTitle(int position){
//
//        return fragmentTitleList.get(position);
//    }
//

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ChatFragment();
            case 1:
                return new FindFriend();
            case 2:
                return new ContactList();

            default:break;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
