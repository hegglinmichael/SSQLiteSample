package com.example.mike.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mike on 8/18/2015.
 */

//class sued for working with database

public class MyDBHandler extends SQLiteOpenHelper{

    //setting variable for database version
    private static final int DATABASE_VERSION = 1;
    //need db extention telling database file
    private static final String DATABASE_NAME = "products.db";
    //setting the name of the table in the database
    public static final String TABLE_PRODUCTS="products";
    //setting names for the colunms and rows
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_PRODUCTNAME="productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //calls this if updating the version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //telling it to delete the current table
        db.execSQL("DROP_TABLE_IF_EXISTS"+TABLE_PRODUCTS);
        //calls the oncreate method that should have new code in it
        onCreate(db);
    }

    //setting up the database table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_PRODUCTS+"("+
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "
                + COLUMN_PRODUCTNAME + " TEXT " + ");";
        //executing the query
        db.execSQL(query);
    }

    //add a new row to the database (for when the user clicks the add button)
    public void addProduct(Products product) {
        //allows you to set different values for different column all in 1 1line (list of values)
        ContentValues values = new ContentValues();
        //putting a value in
        values.put(COLUMN_PRODUCTNAME, product.get_productName());
        //object that is basically key to the database (db is equal to the database we are going to write to)
        SQLiteDatabase db = getWritableDatabase();
        //inserts this into the database (new product/row)
        db.insert(TABLE_PRODUCTS, null, values);
        //need to close the database when done
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String productName) {
        //object that is basically key to the database (db is equal to the database we are going to write to)
        SQLiteDatabase db = getWritableDatabase();
        //deleting the specific row using the product name as they key differentiators
        db.execSQL("DELETE FROM" + TABLE_PRODUCTS + "WHERE" + COLUMN_PRODUCTNAME + "=\""
                + productName + "\";");
    }

    //Method to print out the data as a string so that you can visually see whats happening
    public String toString(){
        //string to return
        String dbString = "";
        //object that is basically key to the database (db is equal to the database we are going to write to)
        SQLiteDatabase db = getWritableDatabase();
        //telling it to select everything in
        String query = "SELECT * FROM"+TABLE_PRODUCTS+"WHERE 1";

        //cursor point to a location in your results
        Cursor cursor = db.rawQuery(query, null);
        //move to the first row in your results
        cursor.moveToFirst();

        //saying make sure there are still results to parse
        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("productName"))!=null){
                //adding stuff onto the string
                dbString += cursor.getString(cursor.getColumnIndex("productName"));
                //putting each one onto a new line
                dbString+="\n";
            }
        }
        //closing the database
        db.close();

        //returning the string
        return dbString;
    }


}
