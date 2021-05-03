package org.gptccherthala.virtualqueue.business;

public class BusinessDatabase {
    public String name;
    public String address;
    public long phone;
    public int pincode;
    public String description;
    public String category;
    public int imagePath;
    public String imageUrl;
    public String type;
    public String bId;
    public boolean isOpen;

    public BusinessDatabase() {
    }

    public BusinessDatabase(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public BusinessDatabase(int imagePath) {
        this.imagePath = imagePath;
    }

    public BusinessDatabase(int imagePath, String type, String category) {
        this.category = category;
        this.imagePath = imagePath;
        this.type = type;
    }

    public BusinessDatabase(String name, String imageUrl, String bId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.bId = bId;
    }

    public BusinessDatabase(String name, String address, long phone, int pincode, String description, String category, String imageUrl) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.pincode = pincode;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImagePath() {
        return imagePath;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
