package com.example.paulo.projeto_p3;

public class ItemList {

    private String itemName;
    private String description;
    private Integer quantity;


    public ItemList(String name, String description, Integer quantity){
        this.itemName = name;
        this.description = description;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
