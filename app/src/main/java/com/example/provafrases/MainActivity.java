package com.example.provafrases;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //criar banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app",MODE_PRIVATE,null);

            // criar a tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS frases (id INTEGER PRIMARY KEY AUTOINCREMENT, frase VARCHAR(255) ) ");

            //inserir dados
       //     bancoDados.execSQL("INSERT INTO frases (frase) VALUES('Fala que odeia falsidade, mas usa Windows pirata.')");
       //     bancoDados.execSQL("INSERT INTO frases (frase) VALUES('Se a vida fosse um cartão de memória, eu formatava e começaria tudo de novo.')");
       //     bancoDados.execSQL("INSERT INTO frases (frase) VALUES('Quem nunca nunca sacudiu o mouse para o PC destravar?')");
       //     bancoDados.execSQL("INSERT INTO frases (frase) VALUES('Digito, logo existo.')");
       //     bancoDados.execSQL("INSERT INTO frases (frase) VALUES('Contra prints, não há argumento.')");

            //Recuperar frases
            Cursor cursor = bancoDados.rawQuery("SELECT id , frase FROM frases ",null);


            //indice da tabela
            int indiceId = cursor.getColumnIndex("id");
            int indiceFrase = cursor.getColumnIndex("frase");

            cursor.moveToFirst();

            while (cursor != null){
                Log.i("Resultado - ID: ",cursor.getString(indiceId));
                Log.i("Resultado - Frase: ",cursor.getString(indiceFrase));

                cursor.moveToNext();
            }

            int cont = cursor.getCount();
            // gerar um numero randon
            Random random = new Random();
            int numeroale = random.nextInt(cont) + 1;

            //nova consulta
            String queryId = "SELECT frase FROM frases WHERE id = " + numeroale;
            cursor = bancoDados.rawQuery(queryId, null);
            cursor.moveToFirst();

            int indFrase = cursor.getColumnIndex("frase");

            String getFrases = cursor.getString(indFrase);

          //  TextView textFrase = findViewById(R.id.textFrase);

          //  textFrase.setText(getFrases + "teste");

            String text = cursor.getString(indiceFrase);

            Log.i("RESULTADO - teste ", "frase: "+ text );

            TextView xxx = findViewById(R.id.textFrase);
            xxx.setText( text );

        }catch (Exception e){
            e.printStackTrace();
        }



    }


}