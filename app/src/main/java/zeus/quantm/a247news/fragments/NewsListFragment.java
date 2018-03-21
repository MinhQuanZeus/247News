package zeus.quantm.a247news.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zeus.quantm.a247news.R;
import zeus.quantm.a247news.adapters.NewsAdapter;
import zeus.quantm.a247news.models.New;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment{
    RecyclerView rvNews;
    private int categoryId;
    private List<New> newList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    public NewsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        rvNews = (RecyclerView) view.findViewById(R.id.rv_news);
        newList = new ArrayList<>();
        newList.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        newList.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        newList.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        newList.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        newList.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        newList.add(new New("Hello", "https://www.google.com.vn/?gfe_rd=cr&dcr=0&ei=RneyWq_aAYGihwOKq6P4AQ","1111111111111111","","http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        newsAdapter = new NewsAdapter(getContext(), newList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),
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
        rvNews.setAdapter(newsAdapter);
        rvNews.setLayoutManager(gridLayoutManager);
        return view;
    }

    public NewsListFragment setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

}
