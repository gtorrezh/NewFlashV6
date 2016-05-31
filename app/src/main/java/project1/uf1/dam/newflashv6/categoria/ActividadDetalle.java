package project1.uf1.dam.newflashv6.categoria;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import project1.uf1.dam.newflashv6.R;

import project1.uf1.dam.newflashv6.xmlpullparser.MainActivity.DoRssFeedTask;

/**
 * Actividad que muestra la imagen del item extendida
 */
public class ActividadDetalle extends AppCompatActivity {

    public static final String EXTRA_PARAM_ID = null;
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private Categoria itemDetallado;
    private ImageView imagenExtendida;
    //categorias
    private static final String Deportes = "";
    private static final String Politica = "";
    private static final String Economia ="";
    private static final String Musica ="";
    private static final String Cine ="";
    private static final String Informatica="";
    private static final String Anime ="";
    private static final String Puig ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_detalle);



        // Obtener el coche con el identificador establecido en la actividad principal
       // itemDetallado = Categoria.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        //String pruebaItemToString = String.valueOf(itemDetallado);
        //Toast toast = Toast.makeText(getApplicationContext(),getIntent().getIntExtra(EXTRA_PARAM_ID,0), Toast.LENGTH_SHORT);
        //toast.show();

        //Toast t = toast.makeText(getIntent().getIntExtra(EXTRA_PARAM_ID,0),toast.show();

        /*imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);

        cargarImagenExtendida();*/

    }

   /* private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(itemDetallado.getIdDrawable())
                .into(imagenExtendida);
    }*/
    private void cargarTexto(){}


}
