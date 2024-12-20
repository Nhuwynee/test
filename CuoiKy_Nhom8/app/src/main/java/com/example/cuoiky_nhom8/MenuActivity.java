package com.example.cuoiky_nhom8;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MenuActivity extends AppCompatActivity {
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu); // Đảm bảo layout này chứa BottomNavigationView
        frameLayout = findViewById(R.id.container);

        // Load fragment mặc định khi mở ứng dụng
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
        }

        // Gắn sự kiện cho BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.nav_featured) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
                    }
                if (id == R.id.nav_feed) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new FilterFragment()).commit();
                }
                if (id == R.id.nav_settings) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new PageWomenFragment()).commit();
                }
                if (id == R.id.nav_profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new TabFragment()).commit();
                }

                    return true;
                }

        });
    }
}
