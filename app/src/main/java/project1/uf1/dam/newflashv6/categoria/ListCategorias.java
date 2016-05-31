package project1.uf1.dam.newflashv6.categoria;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import project1.uf1.dam.newflashv6.R;
import project1.uf1.dam.newflashv6.model.RSSFeed;
import project1.uf1.dam.newflashv6.parser.NewsFeedParser;
import project1.uf1.dam.newflashv6.xmlpullparser.MainActivity;

public class ListCategorias extends Activity {
    private RssAdapter mRssAdap;
    private ListView mRssListView;
    private List<RSSFeed> mRssFeedList;
    private NewsFeedParser mNewsFeeder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categorias);
         mRssListView = (ListView) findViewById(R.id.listViewCategorias);
       // itemDetallado = Categoria.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        Bundle bund = getIntent().getExtras();
        //Toast toast2 = Toast.makeText(getApplicationContext(), bund.getString("Deportes"), Toast.LENGTH_SHORT);
        //toast2.show();
        MainActivity ma = new MainActivity();
        new DoRssFeedTask().execute(bund.getString("URL"));

    }
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
                view = View.inflate(ListCategorias.this, R.layout.rss_list_item, null);
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
            prog = new ProgressDialog(ListCategorias.this);
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
                    mRssAdap = new RssAdapter(ListCategorias.this, R.layout.rss_list_item,
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
