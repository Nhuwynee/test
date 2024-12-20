package com.example.cuoiky_nhom8;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private FeatureAdapter adapter;
    private List<FeaturedItem> items;
    private List<FeaturedItem> filteredItems;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchactivity, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvFeatured);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);

        // Dữ liệu giả lập
        items = new ArrayList<>();
        items.add(new FeaturedItem("Tiểu thuyết", "Seller Information 1", R.drawable.ic_launcher_foreground));
        items.add(new FeaturedItem("Văn học", "Seller Information 2", R.drawable.ic_launcher_foreground));
        items.add(new FeaturedItem("Trinh thám", "Seller Information 3", R.drawable.ic_launcher_foreground));
        items.add(new FeaturedItem("Tiểu thuyết", "Seller Information 4", R.drawable.ic_launcher_foreground));
        items.add(new FeaturedItem("Trinh thám", "Seller Information 5", R.drawable.ic_launcher_foreground));
        items.add(new FeaturedItem("Văn học", "Seller Information 6", R.drawable.ic_launcher_foreground));

        filteredItems = new ArrayList<>(items); // Sao chép dữ liệu gốc vào filteredItems
        adapter = new FeatureAdapter(filteredItems); // Sử dụng filteredItems trong adapter
        recyclerView.setAdapter(adapter);


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


