package com.example.findaval;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import com.example.findaval.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    FindFriend findFriend;
    ContactList contactList;
    ChatFragment chatFragment;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        findFriend = new FindFriend();
        contactList = new ContactList();
        chatFragment = new ChatFragment();


        viewPager2 = binding.viewPager2;
        tabLayout = binding.tabLayout;



        ArrayList<Fragment>fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add( chatFragment);
        fragmentArrayList.add(findFriend);
        fragmentArrayList.add(contactList);

        ArrayList<String>fragmentTitleList = new ArrayList<>();
        fragmentTitleList.add("Chats");
        fragmentTitleList.add("Find Friend");
        fragmentTitleList.add("Contacts");


          viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

        viewPager2.setAdapter(viewPagerAdapter);
//        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> viewPager2.setCurrentItem(tab.getPosition(), true)
//
//        ).attach();
//

        new  TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //tab.setText(viewPagerAdapter.getTabTitle(position));
                if (position ==0){
                    tab.setText("Chat");
                }else if (position == 1){
                    tab.setText("Find Friend");
                }else {
                    tab.setText("Contacts");
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

//    @Override
//    public void onBackPressed() {
//        if (viewPager2.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}