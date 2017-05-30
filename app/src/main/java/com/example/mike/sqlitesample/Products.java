package com.example.mike.sqlitesample;

/**
 * Created by mike on 8/18/2015.
 */
public class Products {

    //product id
    private int _id = 0;
    //product name
    private String _productName = "";

    //another constructor
    public Products(){

    }

    //constructor method
    public Products(String _productName) {
        this._productName = _productName;
    }

    //getter and setter methods
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public int get_id() {
        return _id;
    }

    public String get_productName() {
        return _productName;
    }
}
