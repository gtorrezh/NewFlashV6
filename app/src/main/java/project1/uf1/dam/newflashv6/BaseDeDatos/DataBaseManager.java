package project1.uf1.dam.newflashv6.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by usuario on 30/05/16.
 */
public class DataBaseManager {
    public static final String TABLE_NAME = "favoritos";

    public static final String FV_Id = "_id";
    public static final String FV_Link = "link";
    public static final String FV_Titulo="titulo";
    public static final String FV_Descripcion="descripcion";

    public static final String CREATE_TABLE = "create table"+TABLE_NAME+"("
            +FV_Id+" integer primary key autoincrement,"
            +FV_Link+"text not null,"
            +FV_Titulo+"text not null,"
            +FV_Descripcion+"text not null);";


    private NewFlashDBHelper helper;
    private SQLiteDatabase db;
    public DataBaseManager(Context context) {
      helper = new NewFlashDBHelper(context);
      db = helper.getWritableDatabase();
    }
    public ContentValues generarContentValues(String link,String titulo,String descripcion){
        ContentValues valores = new ContentValues();
        valores.put(FV_Link,link);
        valores.put(FV_Titulo,titulo);
        valores.put(FV_Descripcion,descripcion);
        return valores;
    }
    public void insertar(String link,String titulo,String descripcion){


        //INSERTAR VALORES FAVORITOS
        db.insert(TABLE_NAME,null,generarContentValues(link,titulo,descripcion));
    }
    public void insert2(String link,String titulo,String descripcion){
        //insertar favoritos valores
        db.execSQL("insert into "+TABLE_NAME+"values (null,'"+link+"',"+titulo+","+descripcion+")");
    }
}
