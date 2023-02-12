package com.example.findaval.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.findaval.R;
import com.example.findaval.UI.Settings;
import com.example.findaval.UI.ChatFragment;
import com.example.findaval.UI.ContactList;
import com.example.findaval.UI.FindFriend;
import com.example.findaval.UI.Profile;
import com.example.findaval.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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
