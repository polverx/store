package com.oerr.store.products.models.items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long itemId;

    private String itemName;
    private String itemDescription;

    private long itemStockNumber;

    public ItemsEntity() {
    }

    public ItemsEntity(String itemName, String itemDescription, int itemStockNumber) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemStockNumber = itemStockNumber;

    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public long getItemStockNumber() {
        return itemStockNumber;
    }

    public void setItemStockNumber(long itemStockNumber) {
        this.itemStockNumber = itemStockNumber;
    }

}

