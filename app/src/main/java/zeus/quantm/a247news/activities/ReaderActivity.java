package zeus.quantm.a247news.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.support.v7.widget.Toolbar;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import zeus.quantm.a247news.R;
import zeus.quantm.a247news.models.New;
import zeus.quantm.a247news.utils.ScreenManager;

public class ReaderActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    private New newModel;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(100);
        progress.getProgressDrawable().setColorFilter(
                Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        webView = (WebView)findViewById(R.id.wv_reader);
        webView.setWebViewClient(new MyBrowser());
        webView.setWebChromeClient(new MyWebViewClient());
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        newModel = (New)bundle.getSerializable("newModel");
        Log.d("Reader", newModel.toString());
        getSupportActionBar().setTitle(newModel.title);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(newModel.link);
    }

    public void setValue(int progress) {
        this.progress.setProgress(progress);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reader_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bookmark:
                Toast.makeText(this, "This is teh option help", Toast.LENGTH_LONG).show();
                //TODO save bookmark
                break;
            default:
                break;
        }
        return true;
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            ReaderActivity.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }
}
