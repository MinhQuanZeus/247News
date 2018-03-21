package zeus.quantm.a247news.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.support.v7.widget.Toolbar;
import android.webkit.WebViewClient;

import zeus.quantm.a247news.R;
import zeus.quantm.a247news.models.New;
import zeus.quantm.a247news.utils.ScreenManager;

public class ReaderActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    private New newModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        webView = (WebView)findViewById(R.id.wv_reader);
        webView.setWebViewClient(new MyBrowser());
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        newModel = (New)bundle.getSerializable("newModel");
        Log.d("Reader", newModel.toString());
        webView.loadUrl(newModel.link);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
