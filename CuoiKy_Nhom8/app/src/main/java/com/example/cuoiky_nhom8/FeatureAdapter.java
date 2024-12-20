package com.example.cuoiky_nhom8;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> {
    private List<FeaturedItem> items;

    public FeatureAdapter(List<FeaturedItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feature, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeaturedItem item = items.get(position);
        holder.sellerInfo.setText(item.getSellerInfo());
        holder.title.setText(item.getTitle());
        holder.itemImage.setImageResource(item.getImageResourceId());
        // Set bookmark icon logic if needed (e.g., changing the image based on a condition)
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sellerInfo, title, detail;
        ImageView itemImage, bookmarkIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            sellerInfo = itemView.findViewById(R.id.seller_info);
            itemImage = itemView.findViewById(R.id.item_image);
            bookmarkIcon = itemView.findViewById(R.id.bookmark_icon);
        }
    }
}
