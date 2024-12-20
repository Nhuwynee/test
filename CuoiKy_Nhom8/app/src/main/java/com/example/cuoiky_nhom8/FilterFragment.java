package com.example.cuoiky_nhom8;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment {
    private FeatureAdapter adapter;
    private List<FeaturedItem> items;
    private List<FeaturedItem> filteredItems;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filteractivity, container, false);
        spinner = view.findViewById(R.id.spinner1);
        RecyclerView recyclerView = view.findViewById(R.id.rvFeatured);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setupSpinner();
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

        return view;
    }

    private void setupSpinner() {
        String[] options = {"All", "Tiểu thuyết", "Trinh thám", "Văn học", "Self-help", "Tâm lý học", "Kỹ năng sống"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.item_list_filter, options);
        adapter.setDropDownViewResource(R.layout.item_filter);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                filterBooksByGenre(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không thực hiện gì khi không có mục nào được chọn
            }
        });
    }
    private void filterBooksByGenre(String genre) {
        updateFilteredList(genre);
    }

    private void updateFilteredList(String genre) {
        filteredItems.clear();
        if ("All".equalsIgnoreCase(genre)) {

            filteredItems.addAll(items);
        } else {
            for (FeaturedItem item : items) {
                if (item.getTitle().toLowerCase().contains(genre.toLowerCase()) ||
                        item.getSellerInfo().toLowerCase().contains(genre.toLowerCase())) {
                    filteredItems.add(item);
                    //muốn đổi cái title để lọc thì đổi getTitle hooặc getSellet..
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}


