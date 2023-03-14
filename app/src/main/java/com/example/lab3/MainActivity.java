package com.example.lab3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("lab3", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadBook();
        Log.i("lab3", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lab3", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lab3", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lab3", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lab3", "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState); // saves all view data to the bundle
        Log.i("lab3", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState); // restore all view data

        EditText bookIdText = findViewById(R.id.editTextTextPersonName1); // since we don't care about the other fields, delete them
        EditText authorText = findViewById(R.id.editTextTextPersonName5);
        EditText descriptionText = findViewById(R.id.editTextTextPersonName4);
        EditText priceText = findViewById(R.id.editTextTextPersonName2);

        bookIdText.getText().clear();
        authorText.getText().clear();
        descriptionText.getText().clear();
        priceText.getText().clear();

        Log.i("lab3", "onRestoreInstanceState");
    }

    public void addBook(View view) {

        // declare book attributes
        String bookId;
        String title;
        int ISBN;
        String author;
        String description;
        double price;

        // initialize all edit text objects
        EditText bookIdText = findViewById(R.id.editTextTextPersonName1); // create edit text object from id
        EditText titleText = findViewById(R.id.editTextTextPersonName3);
        EditText ISBNText = findViewById(R.id.editTextTextPersonName6);
        EditText authorText = findViewById(R.id.editTextTextPersonName5);
        EditText descriptionText = findViewById(R.id.editTextTextPersonName4);
        EditText priceText = findViewById(R.id.editTextTextPersonName2);

        // start by collecting all relevant book data from plain text fields
        bookId = bookIdText.getText().toString(); // set book id's value to the input text using get text, also type casts to string
        title = titleText.getText().toString();
        ISBN = (ISBNText.getText().toString().equals("")) ? -1 : (Integer.parseInt(ISBNText.getText().toString())); // retrieve as string first then parse into int or double as needed, fancy if else ternary operator
        author = authorText.getText().toString();
        description = descriptionText.getText().toString();
        price = (priceText.getText().toString().equals("")) ? 0 : (Double.parseDouble(priceText.getText().toString())); // more ternary operator shenanigans

        // now that we have collected all our input data we can create our toast message and display it
        // creating the toast object and message
        Toast myMessage = Toast.makeText(this, String.format("Book (%s) and the price (%.2f)", title, price), Toast.LENGTH_LONG);
        myMessage.show(); // actually displaying the message

        // now we can save all of the data into a shared pref object to persist it
        SharedPreferences myBook = getSharedPreferences("book", 0); // create shared pref
        SharedPreferences.Editor myEditor = myBook.edit(); // create editor obj

        // start saving all the data
        myEditor.putString("id", bookId);
        myEditor.putString("title", title);
        myEditor.putString("isbn", Integer.toString(ISBN));
        myEditor.putString("author", author);
        myEditor.putString("description", description);
        myEditor.putString("price", Double.toString(price));

        myEditor.apply(); // apply the changes
    }

    public void clearFields(View view) { // simple method calling clear on the edit text objects text attribute

        // initialize all edit text objects
        EditText bookIdText = findViewById(R.id.editTextTextPersonName1); // create edit text object from id
        EditText titleText = findViewById(R.id.editTextTextPersonName3);
        EditText ISBNText = findViewById(R.id.editTextTextPersonName6);
        EditText authorText = findViewById(R.id.editTextTextPersonName5);
        EditText descriptionText = findViewById(R.id.editTextTextPersonName4);
        EditText priceText = findViewById(R.id.editTextTextPersonName2);

        bookIdText.getText().clear();
        titleText.getText().clear();
        ISBNText.getText().clear();
        authorText.getText().clear();
        descriptionText.getText().clear();
        priceText.getText().clear();
    }

    public void loadBookButton(View view) { // separate method for button
        loadBook();
    }

    private void loadBook() {

        // initialize all edit text objects
        EditText bookIdText = findViewById(R.id.editTextTextPersonName1); // create edit text object from id
        EditText titleText = findViewById(R.id.editTextTextPersonName3);
        EditText ISBNText = findViewById(R.id.editTextTextPersonName6);
        EditText authorText = findViewById(R.id.editTextTextPersonName5);
        EditText descriptionText = findViewById(R.id.editTextTextPersonName4);
        EditText priceText = findViewById(R.id.editTextTextPersonName2);

        // create shared pref
        SharedPreferences myBook = getSharedPreferences("book", 0);

        // set all the edit text back to the saved values
        bookIdText.setText(myBook.getString("id", ""));
        titleText.setText(myBook.getString("title", ""));
        ISBNText.setText(myBook.getString("isbn", ""));
        authorText.setText(myBook.getString("author", ""));
        descriptionText.setText(myBook.getString("description", ""));
        priceText.setText(myBook.getString("price", ""));
    }
}