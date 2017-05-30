package com.example.mike.sqlitesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //creates reference an input object
    EditText input = null;
    //creates reference a textView object
    TextView data = null;
    //creates reference an object for the MyDBHandler class
    MyDBHandler dbHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking tbe interface and the MyDBHandler class together

        //links the object to the xml
        input = (EditText)findViewById(R.id.enterData);
        //links the textView object
        data = (TextView)findViewById(R.id.myText);
        //dbhandler object  only context (this) matters because variables are already set in the other class
        dbHandler = new MyDBHandler(this, null, null, 1);

        //calls on the method to print the data on the screen
        printDatabase();


    }

    //adding a product to the database
    public void addButtonClicked(View v){
        //creates a product that is about to be entered(puts in entered text as name)
        Products product = new Products(input.getText().toString());
        //calls the addproduct method in the other class to add this product entered
        dbHandler.addProduct(product);
        //calls print database to update the TextView
        printDatabase();
    }

    //deleting a product from the database
    public void deleteButtonClicked(View v){
        //calls the deleteProduct method in the other class to delete the product that has this name
        dbHandler.deleteProduct(input.getText().toString());
        //calls printdatabase to update the TextView
        printDatabase();
    }

    //sets the text to whatever we receive when calling toString method
    public void printDatabase(){
        //puts all the data in the other toString method to the dbstring variable
        String dbString = dbHandler.toString();
        //sets the text of the textView on the string
        data.setText(dbString);
        //sets the input equal to 0
        input.setText("");
    }
}
