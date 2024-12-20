package com.example.cuoiky_nhom8;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class TabFragment extends Fragment {

    FrameLayout frameLayout;
    TabLayout tabLayout;
    ViewFlipper viewFlipper;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_viewbooks_audio, container, false);

        if (getArguments() != null) {
            FeaturedItem book = (FeaturedItem) getArguments().getSerializable("book");
        }

        frameLayout = view.findViewById(R.id.framelayout_audio);
        tabLayout = view.findViewById(R.id.tabLayout);

        // Khởi tạo fragment mặc định
        getParentFragmentManager().beginTransaction().replace(R.id.framelayout_audio, new AboutAudioFragment())
                .addToBackStack(null)
                .commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new AboutAudioFragment();
                        break;
                    case 1:
                        fragment = new ReviewAudioFragment();
                        break;
                    case 2:
                        fragment = new AuthorAudioFragment();
                        break;
                }

                if (fragment != null) {
                    getParentFragmentManager().beginTransaction().replace(R.id.framelayout_audio, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Có thể để trống hoặc thực hiện một số hành động khi tab không được chọn nữa
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Có thể để trống hoặc thực hiện một số hành động khi tab đã được chọn lại
            }
        });


        return view;
    } }


