package com.example.project;

public class Items {
    private String itemname;
    private String itemcategory;
    private String itemprice;
    private String itemcolor;
    private String itemsize;

    public Items() {

    }

    public Items(String itemname,String itemcategory,String itemprice, String itemcolor, String itemsize){

        this.itemname=itemname;
        this.itemcategory=itemcategory;
        this.itemprice=itemprice;
        this.itemcolor=itemcolor;
        this.itemsize=itemsize;

    }

    public String getItemname() {
        return itemname;
    }

    public String getItemcategory() {
        return itemcategory;
    }

    public String getItemprice() {
        return itemprice;
    }

    public String getItemcolor(){
        return itemcolor;
    }

    public String getItemsize(){
        return itemsize;
    }

}
