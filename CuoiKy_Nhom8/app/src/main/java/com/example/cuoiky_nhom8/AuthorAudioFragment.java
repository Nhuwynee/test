package com.example.cuoiky_nhom8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthorAudioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthorAudioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FeatureAdapter adapter;
    private List<FeaturedItem> items;
    private List<FeaturedItem> filteredItems;
    private Spinner spinner;
    public AuthorAudioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AuthorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuthorAudioFragment newInstance(String param1, String param2) {
        AuthorAudioFragment fragment = new AuthorAudioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}