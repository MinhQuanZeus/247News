package zeus.quantm.a247news.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import zeus.quantm.a247news.R;
import zeus.quantm.a247news.activities.ReaderActivity;
import zeus.quantm.a247news.models.New;

/**
 * Created by QuanT on 3/21/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolderItem>{
    private Context context;
    private List<New> news = new ArrayList<>();
    public NewsAdapter(Context context, List<New> topSongModelList) {
        this.context = context;
        this.news = topSongModelList;
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_new, parent, false);
        return new ViewHolderItem(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderItem holder, final int position) {
        holder.setData(news.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewNew(news.get(position));
            }
        });
    }

    private void viewNew(New aNew) {
        Intent i = new Intent(this.context, ReaderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("newModel", aNew);
        i.putExtras(bundle);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    public class ViewHolderItem extends RecyclerView.ViewHolder{

        ImageView ivNew;
        ImageView ivBookmark;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvTime;
        View view;

        public ViewHolderItem(View itemView) {
            super(itemView);
            ivNew = (ImageView) itemView.findViewById(R.id.iv_new);
            ivBookmark = (ImageView) itemView.findViewById(R.id.iv_bookmark);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            ivBookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "This is teh option help", Toast.LENGTH_LONG).show();
                    //TODO save bookmark
                }
            });
            view = itemView;
        }

        public void setData(New newModel){
            Picasso.get().load(newModel.image).into(ivNew);
            tvTitle.setText(newModel.title);
            tvDescription.setText(newModel.description);
            tvTime.setText(newModel.pubDate);
        }
    }
}
