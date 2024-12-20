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

public class WomenFragment extends Fragment {
    private FeatureAdapter adapter;
    private List<FeaturedItem> items;
    private List<FeaturedItem> filteredItems;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.women, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvFeatured);
        RecyclerView recyclerView2 = view.findViewById(R.id.rvFeatured2);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager1);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);

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
        recyclerView2.setAdapter(adapter);

        return view;
    }


}

