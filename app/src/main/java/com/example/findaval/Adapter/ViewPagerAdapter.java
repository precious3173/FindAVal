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


    public static class MainActivity extends AppCompatActivity {

        private AppBarConfiguration appBarConfiguration;
        private ActivityMainBinding binding;
        FindFriend findFriend;
        ContactList contactList;
        ChatFragment chatFragment;
        ViewPager2 viewPager2;
        TabLayout tabLayout;
        ViewPagerAdapter viewPagerAdapter;
        Toolbar toolbar;
        private Menu menu;


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
            toolbar = binding.toolbar;



            ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
            fragmentArrayList.add( chatFragment);
            fragmentArrayList.add(findFriend);
            fragmentArrayList.add(contactList);

            ArrayList<String>fragmentTitleList = new ArrayList<>();
            fragmentTitleList.add("Chats");
            fragmentTitleList.add("Find Friend");
            fragmentTitleList.add("Contacts");


              viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

            viewPager2.setAdapter(viewPagerAdapter);

            new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    //tab.setText(viewPagerAdapter.getTabTitle(position));
                    if (position ==0){
                        tab.setText("Chat");
                    }else if (position == 1){
                        tab.setText("Find Match");
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


            toolbar.inflateMenu(R.menu.menu_main);

            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.action_settings:
                            Intent intent = new Intent(getApplicationContext(), Settings.class);
                            startActivity(intent);
                            return true;

                        case R.id.profile:
                            Intent intent2 = new Intent(getApplicationContext(), Profile.class);
                            startActivity(intent2);
                            return true;
                    }


                    return false;
                }
            });
        }

        @Override
        public void onBackPressed() {
            if (viewPager2.getCurrentItem() == 0) {
                // If the user is currently looking at the first step, allow the system to handle the
                // Back button. This calls finish() on this activity and pops the back stack.
                super.onBackPressed();
            } else {
                // Otherwise, select the previous step.
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
            }
        }



    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //        // Inflate the menu; this adds items to the action bar if it is present.
    //        getMenuInflater().inflate(R.menu.menu_main, menu);
    //        return true;
    //    }

    //    @Override
    //    public boolean onOptionsItemSelected(MenuItem item) {
    //        // Handle action bar item clicks here. The action bar will
    //        // automatically handle clicks on the Home/Up button, so long
    //        // as you specify a parent activity in AndroidManifest.xml.
    //        int id = item.getItemId();
    //
    //        //noinspection SimplifiableIfStatement
    //        if (id == R.id.action_settings) {
    //            return true;
    //        }
    //
    //        return super.onOptionsItemSelected(item);
    //    }

        @Override
        public boolean onSupportNavigateUp() {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            return NavigationUI.navigateUp(navController, appBarConfiguration)
                    || super.onSupportNavigateUp();
        }


    }
}
