package org.darkbyte.classifieds.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 26/9/16.
 */

public class HomeItem_Model {

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    String itemImage;
    String itemId;
    String itemName;
    String itemType;
    String originalPrice;
    String sellingPrice;
    String itemDescription;
    List<String> images;
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public HomeItem_Model(String itemId, String itemName, String itemType, String originalPrice, String sellingPrice, String itemDescription, List<String> images) {

        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.originalPrice = originalPrice;
        this.sellingPrice = sellingPrice;
        this.itemDescription = itemDescription;
        this.images = images;
    }

    public HomeItem_Model(String itemName, String itemType, String originalPrice, String sellingPrice, String itemDescription, ArrayList<String> images) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.originalPrice = originalPrice;
        this.sellingPrice = sellingPrice;
        this.itemDescription = itemDescription;
        this.images = images;
    }

    public HomeItem_Model(String itemName, String originalPrice, String itemDescription) {
        this.itemName = itemName;
        this.originalPrice = originalPrice;
        this.itemDescription = itemDescription;
    }

    public HomeItem_Model() {

    }

    public String getItemName() {
        return itemName;
    }

    public String getItemImage() {
        return itemImage;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}