package com.example.android_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;
import android.os.AsyncTask;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {


    private Socket socket;
    private static final int SERVERPORT = 5000;
    private static final String SERVER_IP = "192.168.0.6";
    private String str = "";
    private PrintWriter out = null;
    private int spinnerPosition = -1;
    private String inLine = "";
    private BufferedReader reader = null;



//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // добавляем пункты меню
//        menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, "Открыть");
//        menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, "Сохранить");
//        return true;
//    }

    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    class SendDataThread implements Runnable {

        @Override
        public void run() {


            try {

                if (socket != null) {

                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    out.println(str);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    class ReadDataThread implements Runnable {

        @Override
        public void run() {


            while (true) {

                    try {

                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        inLine = reader.readLine();

                        Log.i("response", inLine);

                    } catch (IOException e) {
                        Log.e("response", "" + e);
                    } catch (NullPointerException e) {
                        Log.e("response", "" + e);
                    }

            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // get your ToggleButton
        final ToggleButton tgbutton = findViewById(R.id.toggleButton);

        // attach an OnClickListener
        tgbutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(tgbutton.isChecked()){

                    //SOCKET Button is ON
                    new Thread(new ClientThread()).start();

                    new Thread(new ReadDataThread()).start();

                    Toast.makeText(getBaseContext(), "ВКЛ", Toast.LENGTH_SHORT).show();
                }
                else {
                    //SOCKET Button is OFF
                    try {
                        if (socket != null) socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(getBaseContext(), "ВЫКЛ", Toast.LENGTH_SHORT).show();
                }
            }
        });





        // Spinner element
        Spinner spinner = findViewById(R.id.spinner);

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
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // показываем позицию нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                spinnerPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



        ImageButton ib1 = findViewById(R.id.imageButton );
        ImageButton ib2 = findViewById(R.id.imageButton2 );
        ImageButton ib3 = findViewById(R.id.imageButton3);
        ImageButton ib4 = findViewById(R.id.imageButton4);

        // Set a click listener for ImageButton
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Message to confirm button click

                str = "table" + Integer.toString(spinnerPosition) + " button1";
                new Thread(new SendDataThread()).start();

                Toast.makeText(getBaseContext(), "Push button 1", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for ImageButton
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Message to confirm button click

                str = "table" + Integer.toString(spinnerPosition) + " button2";
                new Thread(new SendDataThread()).start();

                Toast.makeText(getBaseContext(), "Push button 2", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for ImageButton
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Message to confirm button click

                str = "table" + Integer.toString(spinnerPosition) + " button3";
                new Thread(new SendDataThread()).start();

                Toast.makeText(getBaseContext(), "Push button 3", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for ImageButton
        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Message to confirm button click

                str = "table" + Integer.toString(spinnerPosition) + " button4";
                new Thread(new SendDataThread()).start();

                Toast.makeText(getBaseContext(), "Push button 4", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
