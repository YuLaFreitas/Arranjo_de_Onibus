package com.teocracia.arranjo_de_onibus;

import android.app.Activity;
import android.view.WindowManager;
import android.view.Window;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Face extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
    }

    public void onClick(View v) {

        EditText passageiro = findViewById(R.id.passEdit);
        EditText poltrona = findViewById(R.id.poltronaEdit);
        EditText pago = findViewById(R.id.pagoEdit);
        EditText carro = findViewById(R.id.carEdit);

        TextView id = findViewById(R.id.id);


        /*TextView pasTW = findViewById(R.id.pasDB);
        TextView poTW = findViewById(R.id.aceDB);
        TextView caTW = findViewById(R.id.carDB);
        TextView vaTW = findViewById(R.id.valorDB);*/

        switch (v.getId()) {

            case (R.id.guardar):

                BancoDeDados gerarDB = new BancoDeDados(this);

                //Declaro o Banco de Dados com tabela "veiculo"
                String nome, lugar, valor, car, msg = "";

                nome = passageiro.getText().toString();
                lugar = poltrona.getText().toString();
                valor = pago.getText().toString();
                car = carro.getText().toString();

                ContentValues cv = new ContentValues();

                try {
                    //cv.clear();

                    cv.put("passageiro", nome);
                    cv.put("carro", car);
                    cv.put("poltrona", lugar);
                    cv.put("valor", valor);

                    passageiro.setText("");
                    carro.setText("");
                    poltrona.setText("");
                    pago.setText("");
                    id.setText("");

                    if (gerarDB.inserir(cv) > 0) {

                        gerarDB.inserir(cv);
                        buscar();

                    } else msg = "OPS!! Erramos...";

                } catch (Exception e) {

                    msg = "Erro " + e;

                }

                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                break;

            case R.id.exluir:

                BancoDeDados del = new BancoDeDados(this);
                del.excluirBanco();

                break;

            case R.id.config:

                break;

            case R.id.dados:
                buscar();
                break;
        }
    }

    TableRow tr;

    public void buscar() {

        String msg = "";
        List<ContentValues> lista;

        try {

            lista = new BancoDeDados(this).pesquisarAll();

                    TableLayout tb = findViewById(R.id.exibirAll);

                    for (ContentValues cvArray : lista){

                        tr = new TableRow(this);

                     /*TextView col1 = new TextView(this);

                        col1.setText(String.valueOf(cvArray.getAsInteger("id")));

                        tr.addView(col1);*/

                        EditText col2 = new EditText(this);

                        col2.setText(cvArray.getAsString("passageiro"));
                        col2.setTextSize(10);

                        tr.addView(col2);

                        TextView col3 = new TextView(this);

                        col3.setText(String.valueOf(cvArray.getAsInteger("carro")));

                        tr.addView(col3);

                        TextView col4 = new TextView(this);

                        col4.setText(String.valueOf(cvArray.getAsInteger("poltrona")));

                        tr.addView(col4);

                        TextView col5 = new TextView(this);

                        col5.setText(String.valueOf(cvArray.getAsFloat("valor")));

                        tr.addView(col5);

                        tb.addView(tr);

                    }



        } catch (Exception e) {
            msg = "Erro " + e;
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
