package com.example.cuoiky_nhom8;


public class FeaturedItem implements Comparable<FeaturedItem>{
    private String title; // Tiêu đề item
    private String sellerInfo; // Thông tin người bán
    private int imageResourceId; // ID hình ảnh của item

    // Constructor
    public FeaturedItem(String title, String sellerInfo, int imageResourceId) {
        this.title = title;
        this.sellerInfo = sellerInfo;
        this.imageResourceId = imageResourceId;
    }

    // Getter và Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(String sellerInfo) {
        this.sellerInfo = sellerInfo;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
    @Override
    public int compareTo(FeaturedItem other) {
        return this.title.compareToIgnoreCase(other.title); // Sắp xếp theo tên thể loại, không phân biệt hoa thường
        // đổi mục cần sắp xếp
    }
}
