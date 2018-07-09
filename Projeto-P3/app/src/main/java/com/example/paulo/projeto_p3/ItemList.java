package com.example.paulo.projeto_p3;

public class ItemList {

    private String itemName;
    private String id;
    private String description;
    private Integer quantity;
    private Integer status;


    public ItemList(String name, String id, String description, Integer quantity, Integer status){
        this.itemName = name;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.id = id;
    }

    public String getId() {
        return id;
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

    public Integer getStatus() {
        return status;
    }
}
