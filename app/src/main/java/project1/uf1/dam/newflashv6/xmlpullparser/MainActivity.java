package project1.uf1.dam.newflashv6.xmlpullparser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import project1.uf1.dam.newflashv6.NoticiaVisualizacion.WebVisual;
import project1.uf1.dam.newflashv6.R;
import project1.uf1.dam.newflashv6.categoria.ActividadDetalle;
import project1.uf1.dam.newflashv6.categoria.AdaptadorCategorias;
import project1.uf1.dam.newflashv6.categoria.Categoria;
import project1.uf1.dam.newflashv6.categoria.ListCategorias;
import project1.uf1.dam.newflashv6.model.RSSFeed;
import project1.uf1.dam.newflashv6.parser.NewsFeedParser;

/**
 * Error de cuando el movil no tiene acceso a internet
 * el error puede ser por la siguiente linea de codigo  new DoRssFeedTask().execute(TOPSTORIES);
 * por que al arrancar el proceso no puede acceder a los datos de internet para obtener la informacion
 *
 * la solucion es que tenemos que verificar si tenemos acceso a internet antes de ejecutar la siguiente opcion
 */


public class MainActivity extends Activity implements OnItemClickListener {
    //variables
    TabHost TbH;
    private ListView mRssListView;
    private NewsFeedParser mNewsFeeder;
    private List<RSSFeed> mRssFeedList;
    private RssAdapter mRssAdap;
    private CheckBox Favoritos;

    //Variables categoria
    private GridView gridView;
    private AdaptadorCategorias adaptador;





    //pagina o rss de donde seleccionamos el contenido  de la informacion
    private static final String TOPSTORIES = "http://www.sport.es/es/rss/last_news/rss.xml";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*categorias*/
        gridView = (GridView) findViewById(R.id.grid);
        adaptador = new AdaptadorCategorias(this);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(this);

        mRssListView = (ListView) findViewById(R.id.rss_list_view);
        mRssFeedList = new ArrayList<RSSFeed>();
        mRssListView.setOnItemClickListener(this);

        /*verificar el estado de la red
         *
          * si tiene conexion nos enviara ala lista de las noticias
           * y sino hay nos mandara ala lista de favoritos que tenemos en la base de datos*/
        /*if (!verificaConexion(this)) {
            Toast.makeText(getBaseContext(), "No tienes conexion a internet. Accediendo A la lista de favoritos ", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, project1.uf1.dam.newflashv6.BaseDeDatos.Favoritos.class);
            startActivity(i);
            this.finish();

        }else{
            new DoRssFeedTask().execute(TOPSTORIES);
        }*/
        if (!compruebaConexion(this)) {
            Toast.makeText(getBaseContext(),"Necesaria conexión a internet ", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(MainActivity.this, project1.uf1.dam.newflashv6.BaseDeDatos.MainDBNewflash.class);
            startActivity(i);

        }else {
            new DoRssFeedTask().execute(TOPSTORIES);
        }

        /*********************************************************************/

        //TABHOST


        TbH = (TabHost) findViewById(R.id.tabHost); //llamamos al Tabhost
        TbH.setup();                                                         //lo activamos

        TabHost.TabSpec tab1 = TbH.newTabSpec("tab1");  //aspectos de cada Tab (pestaña)
        TabHost.TabSpec tab2 = TbH.newTabSpec("tab2");


        tab1.setIndicator("Noticias");    //qué queremos que aparezca en las pestañas
        tab1.setContent(R.id.tab1); //definimos el id de cada Tab (pestaña)

        tab2.setIndicator("Categorias");
        tab2.setContent(R.id.tab2);


        TbH.addTab(tab1); //añadimos los tabs ya programados
        TbH.addTab(tab2);
        /***********************************************************************/

        /*************************************************************************/

        //Visualizacion EN La activity WebVisual


        // Registrar escucha de la lista
        mRssListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RSSFeed rssFeed = mRssFeedList.get(position);


                // Obtene url de la entrada seleccionada
                String url = rssFeed.getLink();

                // Nuevo intent explícito
                Intent i = new Intent(MainActivity.this, WebVisual.class);

                // Setear url
                i.putExtra("url-extra", url);

                // Iniciar actividad
                startActivity(i);


            }
        });
        /************************************************************************************/
        //Registrar escucha de las categorias



    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      Categoria item = (Categoria) parent.getItemAtPosition(position);

        //Intent intent = new Intent(this, ActividadDetalle.class);//nos dice a que activity nos redirige

        //Intent intent = new Intent(this, ListCategorias.class);
        //intent.putExtra(ListCategorias.EXTRA_PARAM_ID, item.getId());//con esta linea le pasamos el id
      //  String pruebaItemToString = String.valueOf(item.getId());
        //Toast toast1 = Toast.makeText(getApplicationContext(), pruebaItemToString, Toast.LENGTH_SHORT);
        //toast1.show();
      /*  if(item.getId()==1008063696){
            Toast toast2 = Toast.makeText(getApplicationContext(), "esto es deportes", Toast.LENGTH_SHORT);
            toast1.show();
        }*/
        switch (item.getId()){
            case 1008063696:
                Toast toast2 = Toast.makeText(getApplicationContext(), "esto es deportes", Toast.LENGTH_SHORT);
                toast2.show();
                Intent intent = new Intent(this, ListCategorias.class);
                String deporte = "http://www.sport.es/es/rss/last_news/rss.xml";
                intent.putExtra("URL",deporte.toString());
                startActivity(intent);

            break;
            case 733949427:
                Toast toast3 = Toast.makeText(getApplicationContext(), "esto es Politica", Toast.LENGTH_SHORT);
                toast3.show();
                Intent intent2 = new Intent(this, ListCategorias.class);
                String Politica = "http://ep00.epimg.net/rss/politica/portada.xmll";
                intent2.putExtra("URL",Politica.toString());
                startActivity(intent2);
            break;
            case 946222095:
                Toast toast4 = Toast.makeText(getApplicationContext(), "esto es Economia", Toast.LENGTH_SHORT);
                toast4.show();
                Intent intent3 = new Intent(this, ListCategorias.class);
                String Economia = "http://ep00.epimg.net/rss/economia/portada.xml";
                intent3.putExtra("URL",Economia.toString());
                startActivity(intent3);
                break;
            case -1856112383:
                Toast toast5 = Toast.makeText(getApplicationContext(), "esto es Musica", Toast.LENGTH_SHORT);
                toast5.show();
                Intent intent4 = new Intent(this, ListCategorias.class);
                String Musica = "http://elpais.com/tag/rss/musica/a/";
                intent4.putExtra("URL", Musica.toString());
                startActivity(intent4);

            break;
            case 2100413:
                Toast toast6 = Toast.makeText(getApplicationContext(), "esto es Cine", Toast.LENGTH_SHORT);
                toast6.show();
                Intent intent5 = new Intent(this, ListCategorias.class);
                String Cine = "http://elpais.com/tag/rss/cine/a/";
                intent5.putExtra("URL",Cine.toString());
                startActivity(intent5);

                break;
            case -540287989:
                Toast toast7 = Toast.makeText(getApplicationContext(), "esto es Informatica", Toast.LENGTH_SHORT);
                toast7.show();
                Intent intent6 = new Intent(this, ListCategorias.class);
                String Informatica = "http://ep00.epimg.net/rss/tecnologia/portada.xml";
                intent6.putExtra("URL", Informatica.toString());
                startActivity(intent6);

                break;
            case 63410260:
                Toast toast8 = Toast.makeText(getApplicationContext(), "esto es Anime", Toast.LENGTH_SHORT);
                toast8.show();
                Intent intent7 = new Intent(this, ListCategorias.class);
                String Anime = "http://www.sport.es/es/rss/last_news/rss.xml";
                intent7.putExtra("URL",Anime.toString());
                startActivity(intent7);
                break;
            case 1418640976:
                Toast toast9 = Toast.makeText(getApplicationContext(), "esto es NewFlash", Toast.LENGTH_SHORT);
                toast9.show();
                Intent intent8 = new Intent(this, ListCategorias.class);
                String Newflash= "http://misiontokyo.com/feed";
                intent8.putExtra("URL", Newflash.toString());
                startActivity(intent8);
            break;

        }

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                            new Pair<View, String>(view.findViewById(R.id.imagen_coche),
                                    ActividadDetalle.VIEW_NAME_HEADER_IMAGE)
                    );

            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        } else
            startActivity(intent);*/
     //startActivity(intent);
    }

   /* public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }else {
                bConectado = false;
            }
        }
        return bConectado;
    }*/
   public static boolean compruebaConexion(Context context) {

       boolean connected = false;

       ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

       // Recupera todas las redes (tanto móviles como wifi)
       NetworkInfo[] redes = connec.getAllNetworkInfo();

       for (int i = 0; i < redes.length; i++) {
           // Si alguna red tiene conexión, se devuelve true
           if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
               connected = true;
           }
       }
       return connected;
   }


   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categoria item = (Categoria) parent.getItemAtPosition(position);

        Intent intent2 = new Intent(MainActivity.this, ActividadDetalle.class);


        intent2.putExtra(ActividadDetalle.EXTRA_PARAM_ID, item.getId());


            startActivity(intent2);
    }*/


    private class RssAdapter extends ArrayAdapter<RSSFeed> {
        private List<RSSFeed> rssFeedLst;

        public RssAdapter(Context context, int textViewResourceId, List<RSSFeed> rssFeedLst) {
            super(context, textViewResourceId, rssFeedLst);
            this.rssFeedLst = rssFeedLst;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            RssHolder rssHolder = null;
            if (convertView == null) {
                view = View.inflate(MainActivity.this, R.layout.rss_list_item, null);
                rssHolder = new RssHolder();
                rssHolder.rssTitleView = (TextView) view.findViewById(R.id.rss_title_view);
                rssHolder.rssDescription = (TextView) view.findViewById(R.id.rss_description);
                view.setTag(rssHolder);
            } else {
                rssHolder = (RssHolder) view.getTag();
            }
            RSSFeed rssFeed = rssFeedLst.get(position);
            rssHolder.rssTitleView.setText(rssFeed.getTitle());
            rssHolder.rssDescription.setText(rssFeed.getDescription());
            return view;
        }
    }

    static class RssHolder {
        public TextView rssTitleView,rssDescription;
        public ImageView rssimagen;
    }

    //public class DoRssFeedTask (int idCategoria) extends AsyncTask<String, Void, List<RSSFeed>> {
    public class DoRssFeedTask extends AsyncTask<String, Void, List<RSSFeed>> {
        ProgressDialog prog;
        String jsonStr = null;
        Handler innerHandler;

        @Override
        protected void onPreExecute() {
            prog = new ProgressDialog(MainActivity.this);
            prog.setMessage("Cargando Datos....");
            prog.show();
        }

        @Override
        protected List<RSSFeed> doInBackground(String... params) {
            for (String urlVal : params) {
                mNewsFeeder = new NewsFeedParser(urlVal);
            }
            mRssFeedList = mNewsFeeder.parse();
            return mRssFeedList;
        }

        @Override
        protected void onPostExecute(List<RSSFeed> result) {
            prog.dismiss();
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mRssAdap = new RssAdapter(MainActivity.this, R.layout.rss_list_item,
                            mRssFeedList);
                    int count = mRssAdap.getCount();
                    if (count != 0 && mRssAdap != null) {
                        mRssListView.setAdapter(mRssAdap);
                    }
                }
            });
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}
