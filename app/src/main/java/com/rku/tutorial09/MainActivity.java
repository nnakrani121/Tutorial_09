package com.rku.tutorial09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {
    final static String FILE_ASSETS="data.json";
    final static String FILE_WRITE="data.txt";
    EditText edittext;
    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext = findViewById(R.id.edittext);
        data = findViewById(R.id.data);
    }

    public void readAssets(View view) {
        try {
            InputStream inputStream = getAssets().open(FILE_ASSETS);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String temp = "";
            while ((temp=bufferedReader.readLine()) !=null){
                sb.append(temp);
            }

            data.setText(sb.toString());
            inputStream.close();
            bufferedReader.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(View view) {

        try {
            FileInputStream fin = openFileInput(FILE_WRITE);
            int c;
            String temp = "";
            while ((c = fin.read()) !=-1){
                temp =temp +String.valueOf((char)c);
            }
            data.setText(temp);
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void writeFile(View view) {
        try {
            FileOutputStream fOut = openFileOutput(FILE_WRITE, Context.MODE_PRIVATE);
            String data = edittext.getText().toString();
            fOut.write(data.getBytes());
            fOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}