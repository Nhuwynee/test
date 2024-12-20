package com.example.cuoiky_nhom8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private FeatureAdapter adapter;
    private List<FeaturedItem> items;
    private List<FeaturedItem> filteredItems;
    private Spinner spinner;
    FrameLayout frameLayout;
    TabLayout tabLayout;
    ViewFlipper viewFlipper;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_activity, container, false);

        if (getArguments() != null) {
            FeaturedItem book = (FeaturedItem) getArguments().getSerializable("book");
        }

        frameLayout = view.findViewById(R.id.framelayout_audio);
        tabLayout = view.findViewById(R.id.tabLayout);

        // Khởi tạo fragment mặc định
        getParentFragmentManager().beginTransaction().replace(R.id.framelayout_audio, new WomenFragment())
                .addToBackStack(null)
                .commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new WomenFragment();
                        break;
                    case 1:
                        fragment = new MenFragment();
                        break;
                    case 2:
                        fragment = new KidsFragment();
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


        // Xử lý tìm kiếm
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                filterItems(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Lọc danh sách item khi người dùng thay đổi từ khóa tìm kiếm
                return false;
            }
        });

        return view;
    }
    private void filterItems(String query) {
        filteredItems.clear(); // Xóa dữ liệu cũ trong filteredItems

        if (query.isEmpty()) {
            filteredItems.addAll(items); // Nếu không có từ khóa tìm kiếm, hiển thị tất cả item
        } else {
            // Lọc các item có chứa từ khóa tìm kiếm
            for (FeaturedItem item : items) {
                if (item.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        item.getSellerInfo().toLowerCase().contains(query.toLowerCase())) {
                    filteredItems.add(item);
                }
            }
        }

        // Cập nhật lại adapter với dữ liệu đã lọc
        adapter.notifyDataSetChanged();
    }
}
