package com.example.apibox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Dentro onCreate()");

        EditText casellaNome = findViewById(R.id.boxNome);
        EditText casellaCognome = findViewById(R.id.boxCognome);
        ImageButton loginButton = findViewById(R.id.bottoneLogin);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        loginButton.setOnClickListener(new View.OnClickListener() {    // classe astratta
            // istanziare un'interfaccia (OnClickListener)
            @Override
            public void onClick(View view) {
                // ottenere le stringhe inserite negli EditText del nome e cognome
                String nome = casellaNome.getText().toString().trim();
                String cognome = casellaCognome.getText().toString().trim();

                // Verifica se il nome e il cognome sono corretti
                boolean loginSuccessful = verificaCredenziali(nome, cognome);

                //Se il login è avvenuto con successo, mostra un messaggio di successo
                if(loginSuccessful){
                    Toast.makeText(MainActivity.this, "LOGIN EFFETTUATO CON SUCCESSO!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "CREDENZIALI NON VALIDE, RIPROVARE!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // questo metodo verifica òe credenziali dell'utente confrontandole con il database
    private boolean verificaCredenziali(String nome, String cognome){
        // query per verificare se nome e cognome sono presenti nel database
        String query = "SELECT * FROM users WHERE nome=" + nome + "AND cognome=" + cognome ;
        Cursor cursor = db.rawQuery(query, new String[]{nome, cognome});

        // verifica se la query ha restituito qualche risultato
        boolean loginSuccessful = cursor.getCount() > 0;

        // chiudo il cursore
        cursor.close();

        return loginSuccessful;
    }

    // Classe per la gestione del database
    private static class DBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "my_destroy.db";
        private static final int DATABASE_VERSION = 1;

        // query per creare la tabella "users"
        private static final
    }







}