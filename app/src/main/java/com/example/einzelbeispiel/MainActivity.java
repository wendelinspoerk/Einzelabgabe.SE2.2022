package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button1;
    TextView ausgabe_server;


    EditText Matrikelnummer;
    Button button2;
    TextView ausgabe_Berechnung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        ausgabe_server = findViewById(R.id.ausgabe_server);

        Matrikelnummer = findViewById(R.id.Matrikelnummer);
        button2 = findViewById(R.id.button2);
        ausgabe_Berechnung = findViewById(R.id.ausgabe_berechnung);



    }
}