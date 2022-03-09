package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

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


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starClient();
            }
        });



    }

    public void starClient(){
        TCPClient thread = new TCPClient();
        thread.start();
    }

    public class TCPClient extends Thread {
        public void main(String argv[]) throws Exception {
            String sentence = Matrikelnummer.getText().toString();

            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(sentence + '\n');

            ausgabe_server.setText(inFromServer.readLine());
            clientSocket.close();
        }
    }
}