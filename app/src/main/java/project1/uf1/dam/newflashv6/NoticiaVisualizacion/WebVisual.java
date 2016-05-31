package project1.uf1.dam.newflashv6.NoticiaVisualizacion;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import project1.uf1.dam.newflashv6.R;


public class WebVisual extends Activity {

    private static final String TAG = WebVisual.class.getSimpleName();
    WebView Vweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_visual);

        // Recuperar url
        String urlExtra = getIntent().getStringExtra("url-extra");


        Vweb = (WebView) findViewById(R.id.idWebview);

        // Habilitar Javascript en el renderizado
        Vweb.getSettings().setJavaScriptEnabled(true);

        // Transmitir localmente
        Vweb.setWebViewClient(new WebViewClient());

        // Cargar el contenido de la url
        Vweb.loadUrl(urlExtra);



    }
}
