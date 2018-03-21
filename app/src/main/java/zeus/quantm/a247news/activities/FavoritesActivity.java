package zeus.quantm.a247news.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zeus.quantm.a247news.R;
import zeus.quantm.a247news.adapters.NewsAdapter;
import zeus.quantm.a247news.models.New;
import zeus.quantm.a247news.network.XMLFavorite;

public class FavoritesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    RecyclerView rvFavorites;
    private NewsAdapter newsAdapter;
    XMLFavorite xmlFavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle("Yêu thích");
        rvFavorites = (RecyclerView) findViewById(R.id.rv_favorites);

        xmlFavorite = new XMLFavorite(getApplicationContext());
        setUpUI();


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpUI();
    }

    private void setUpUI(){
        if(xmlFavorite.ReadXMLFavorite() != null){
            newsAdapter = new NewsAdapter(this, xmlFavorite.ReadXMLFavorite() , xmlFavorite.ReadXMLFavorite() );
            GridLayoutManager gridLayoutManager = new GridLayoutManager(
                    this,
                    2,
                    LinearLayoutManager.VERTICAL,
                    false
            );
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (position % 3 == 0 ? 2 : 1);
                }
            });
            rvFavorites.setAdapter(newsAdapter);
            rvFavorites.setLayoutManager(gridLayoutManager);
        }
    }
}
