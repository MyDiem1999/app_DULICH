package ex.Travelapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main2Activity extends AppCompatActivity {
    BottomNavigationView navView;
    boolean status = false;
//    MenuItem menuItem;

    EditText searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        navView = findViewById(R.id.nav_view);

        loadFrament(new HomeFragment());

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedLister);
        navView.setSelectedItemId(R.id.navigation_home);

//        Tìm  kiếm
        searchView = findViewById(R.id.search_view);
        searchView.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedLister
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    //getSupportActionBar().setTitle("Home");
                    fragment = new HomeFragment();
                    loadFrament(fragment);
                    return true;

                case  R.id.navigation_notifications:
                    //getSupportActionBar().setTitle("Notification");
                    fragment = new NotificationFragment();
                    loadFrament(fragment);
                    return true;

                case R.id.navigation_account:
//                    getSupportActionBar().setTitle("Profile");
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    searchView.setVisibility(View.GONE);
                    fragment = new AccountFragment();
                    loadFrament(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFrament(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
