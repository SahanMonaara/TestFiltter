package com.example.android.filtertest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.filtertest.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /* ActionBar actionBar;
     Context mContext;*/
    EditText txtMenuIndex,txtMenuName;
    Button removeButton,addButton,updateButton;
   /* Spinner spinner;*/
    List<String> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* actionBar = getSupportActionBar();
        actionBar.setTitle("Filter");
        mContext = getApplicationContext();*/
        txtMenuName = (EditText) findViewById(R.id.textMenuName);
        txtMenuIndex = (EditText) findViewById(R.id.textMenuIndex);
        removeButton = (Button) findViewById(R.id.removeButton);
        addButton = (Button) findViewById(R.id.addButton);
        updateButton = (Button) findViewById(R.id.updateButton);

        listItem = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("MenuItem", MODE_PRIVATE);
        String results = sharedPreferences.getString("Menu","[]");
        results = results.substring(1,results.length()-1);
        if (!results.isEmpty()){
            if (results.contains(",")){
                String[] items = results.split(",");
                for (String item:items){
                    listItem.add(item.trim());
                }
            }
            else {
                listItem.add(results);
            }
        }

    }
    public void addButtonClicked(View view){
        listItem.add(txtMenuName.getText().toString().trim());
        applyChanged();
    }
    public void removeButtonClicked(View view){
        int index = Integer.parseInt(txtMenuIndex.getText().toString().trim());
        if (index>= listItem.size()){
            return;
        }
        listItem.remove(index);
        applyChanged();
    }
    public void updateButtonClicked(View view){
        int index = Integer.parseInt(txtMenuIndex.getText().toString().trim());
        if (index>= listItem.size()){
            return;
        }
        listItem.set(index,txtMenuName.getText().toString().trim());
        applyChanged();
    }
    private void applyChanged(){
        SharedPreferences sharedPreferences = getSharedPreferences("MenuItem",MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Menu",listItem.toString());
        editor.apply();
        invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        for (int i = 0 ;i < listItem.size() ; i++ ){
            menu.add(0, i , Menu.NONE, listItem.get(i));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        for (int i = 0 ; i < listItem.size() ; i++){
            if (i== item.getItemId()){
                Toast.makeText(getApplicationContext(), listItem.get(i),Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        menu.clear();
        //myMenuInflater.inflate(R.menu.menu2, menu); // here you show the other menu
        inflater.inflate(R.menu.filter_menu, menu);

        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
               // Showing selected spinner item
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        spinner.setVisibility(View.GONE);
    }

    public void spinner(MenuItem item) {

        spinner.setVisibility(View.VISIBLE);

        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("All");
        categories.add("Pepe Jeans");
        categories.add("Hilton");
        categories.add("Mobitel");
        categories.add("Mango");
        categories.add("Classic Travels");
        categories.add("Odel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }*/
}
