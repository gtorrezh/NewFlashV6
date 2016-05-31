package project1.uf1.dam.newflashv6.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by usuario on 30/05/16.
 */
public class NewFlashDBHelper extends SQLiteOpenHelper {
    private static  final int VERSION_DATABASE = 1; // version de la base de datos
    private static final String Name_NewFlashDB = "NewFlashDB.db";//creacion de la base de datos

    public NewFlashDBHelper(Context context) {
        super(context,Name_NewFlashDB,null,VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(DataBaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
