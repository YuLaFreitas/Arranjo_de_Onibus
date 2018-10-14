package com.teocracia.arranjo_de_onibus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper{

    private static final String nome_banco = "veiculos";
    private static final int versao = 1;

    private final String GERAR_TABELA ="CREATE TABLE "
            + nome_banco +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "passageiro TEXT," +
            "poltrona INTEGER, " +
            "carro INTEGER," +
            "valor REAL);";

    public BancoDeDados(Context context){
        super(context, nome_banco, null, versao);
    }


    //Código gerado automaticamente
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GERAR_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + nome_banco);

        onCreate(db);
    }

    //INSERIR
    public long inserir(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.insert(nome_banco, null, cv);

    }

    //Este metodo realiza uma busca em geral no banco de dados
    public List<ContentValues>pesquisarAll(){
        String sql = "SELECT * FROM veiculos ORDER BY id;";
        //String where[] = null;

        return pesquisar(sql, null);
    }

    //organiza um array para ser usado na busca
    private List<ContentValues> pesquisar(String sql, String where[]) {

        List<ContentValues> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(sql, where);

        if(c.moveToFirst()){
            do{

                ContentValues cv = new ContentValues();

                cv.put("id", c.getInt(c.getColumnIndex("id")));
                cv.put("passageiro", c.getString(c.getColumnIndex("passageiro")));
                cv.put("poltrona", c.getInt(c.getColumnIndex("poltrona")));
                cv.put("carro", c.getInt(c.getColumnIndex("carro")));
                cv.put("valor", c.getInt(c.getColumnIndex("valor")));

                lista.add(cv);

                /*int a = 1;
                a++;
                lista.remove(a);*/

            }while(c.moveToNext());

        }
        return lista;
    }

    //EXCLUIR
   public  void excluirBanco(){

       SQLiteDatabase db = getWritableDatabase();
       String sql = "DROP TABLE IF EXISTS veiculo;";
       db.execSQL(sql);



   }
}
