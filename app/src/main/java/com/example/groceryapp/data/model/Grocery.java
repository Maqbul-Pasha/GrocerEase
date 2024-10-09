package com.example.groceryapp.data.model;

public class Grocery {
    private String id;
    private String name;
    private String category;
    public Grocery (String id, String name, String category){
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Grocery(){
    }

    //getter and setter
    public String getName(){ return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return name + "(\n"+category+")";
    }
}
