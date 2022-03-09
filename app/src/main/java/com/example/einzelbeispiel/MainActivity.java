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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MatrNr = Matrikelnummer.getText().toString();
                char[] result = MatrNr.toCharArray();
                String alphabet = "abcdefghi";

                for (int i=1; i<result.length; i+=2){
                    if (result[i] == '0'){
                        result[i] = 'j';
                    }else{
                        result[i] = alphabet.charAt(Integer.parseInt(String.valueOf(result[i])) -1);
                    }
                }
                String myString = String.valueOf(result);
                ausgabe_Berechnung.setText(myString);

            }
        });


    }

    public void starClient() {
        TCPClient thread = new TCPClient();
        thread.start();
    }

    class TCPClient extends Thread {
        public void run() {
            try {
                String sentence = Matrikelnummer.getText().toString();

                Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToServer.writeBytes(sentence + '\n');

                ausgabe_server.setText(inFromServer.readLine());
                clientSocket.close();

            } catch (Exception Networkerror) {
                ausgabe_server.setText("Network Error");
            }
        }
    }
}

