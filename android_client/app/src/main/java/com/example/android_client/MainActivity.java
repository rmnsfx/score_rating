package com.example.android_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int IDM_OPEN = 101;
    private static final int IDM_SAVE = 102;


    public boolean onCreateOptionsMenu(Menu menu)
    {
        // добавляем пункты меню
        menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, "Открыть");
        menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, "Сохранить");
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // get your ToggleButton
        final ToggleButton tgbutton = (ToggleButton) findViewById(R.id.toggleButton);

        // attach an OnClickListener
        tgbutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(tgbutton.isChecked()){
                    //Button is ON
                    Toast.makeText(getBaseContext(), "Button is ON", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Button is OFF
                    Toast.makeText(getBaseContext(), "Button is OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Стол 1");
        categories.add("Стол 2");
        categories.add("Стол 3");
        categories.add("Стол 4");
        categories.add("Стол 5");
        categories.add("Стол 6");
        categories.add("Стол 7");
        categories.add("Стол 8");
        categories.add("Стол 9");
        categories.add("Стол 10");
        categories.add("Стол 11");
        categories.add("Стол 12");
        categories.add("Стол 13");
        categories.add("Стол 14");
        categories.add("Стол 15");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



        ImageButton ib1 = (ImageButton) findViewById(R.id.imageButton );
        ImageButton ib2 = (ImageButton) findViewById(R.id.imageButton2 );
        ImageButton ib3 = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton ib4 = (ImageButton) findViewById(R.id.imageButton4);

        // Set a click listener for ImageButton
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Message to confirm button click
                Toast.makeText(getBaseContext(), "Push button 1", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for ImageButton
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Message to confirm button click
                Toast.makeText(getBaseContext(), "Push button 2", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for ImageButton
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Message to confirm button click
                Toast.makeText(getBaseContext(), "Push button 3", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for ImageButton
        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Message to confirm button click
                Toast.makeText(getBaseContext(), "Push button 4", Toast.LENGTH_SHORT).show();
            }
        });

    }




}
