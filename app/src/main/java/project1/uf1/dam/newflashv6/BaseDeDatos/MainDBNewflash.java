package project1.uf1.dam.newflashv6.BaseDeDatos;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import project1.uf1.dam.newflashv6.R;

public class MainDBNewflash extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);



        DataBaseManager manager = new DataBaseManager(this);
        //manager.insertar();
        //https://www.youtube.com/watch?v=02klx2fooEk

    }
}
