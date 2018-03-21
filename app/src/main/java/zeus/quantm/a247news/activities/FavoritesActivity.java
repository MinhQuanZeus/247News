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

public class FavoritesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    RecyclerView rvFavorites;
    private List<New> favorites = new ArrayList<>();
    private NewsAdapter newsAdapter;
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

        favorites = new ArrayList<>();
        favorites.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        favorites.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        favorites.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        favorites.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        favorites.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        favorites.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));

        newsAdapter = new NewsAdapter(this, favorites);
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
