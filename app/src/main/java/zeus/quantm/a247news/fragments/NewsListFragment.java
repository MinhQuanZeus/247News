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
import zeus.quantm.a247news.network.XMLController;

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

        setnewList(categoryId, rvNews);

        return view;
    }

    public NewsListFragment setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    private void setnewList(int categoryId , RecyclerView rvNews){

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
        switch (categoryId){
            case 0:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            case 1:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            case 2:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            case 3:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            case 4:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            case 5:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            case 6:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            case 7:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            case 8:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            case 9:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;
            }
            default:{
                new XMLController(getContext(), rvNews , gridLayoutManager).execute(getResources().getString(R.string.url_trangchu));
                break;

            }
        }
    }

}
