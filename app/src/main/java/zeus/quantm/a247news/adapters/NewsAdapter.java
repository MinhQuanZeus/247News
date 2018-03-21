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
import zeus.quantm.a247news.network.XMLController;
import zeus.quantm.a247news.network.XMLFavorite;

/**
 * Created by QuanT on 3/21/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolderItem> {
    private Context context;
    private List<New> news = new ArrayList<>();
    private List<New> newsFavortite;
    XMLFavorite xmlFavorite;

    public NewsAdapter(Context context, List<New> topSongModelList, List<New> listFavor) {
        this.context = context;
        this.news = topSongModelList;
        this.newsFavortite = listFavor;
        xmlFavorite = new XMLFavorite(context);
        newsFavortite = xmlFavorite.ReadXMLFavorite();
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


    public class ViewHolderItem extends RecyclerView.ViewHolder {

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
                    //TODO save bookmark
                    try {
                        if (newsFavortite == null) {
                            newsFavortite = new ArrayList<>();
                            ivBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));
                            newsFavortite.add((New) ivBookmark.getTag());
                            Toast.makeText(context, "Bạn vừa lưu bài báo này vào tệp lưu", Toast.LENGTH_LONG).show();
                            xmlFavorite.WriteXMLFavorite((ArrayList<New>) newsFavortite);
                            return;
                        }


                        boolean isExist = false;
                        int i = 0;

                        for (New n : newsFavortite
                                ) {
                            if (n.title.compareToIgnoreCase(((New) ivBookmark.getTag()).title) == 0) {
                                isExist = true;
                                break;
                            }
                            i++;
                        }

                        if (isExist) {
                            ivBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));
                            newsFavortite.remove(i);
                            Toast.makeText(context, "Bạn vừa xóa bài báo này khỏi tệp lưu", Toast.LENGTH_LONG).show();
                            xmlFavorite.WriteXMLFavorite((ArrayList<New>) newsFavortite);
                        } else {
                            ivBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));
                            newsFavortite.add((New) ivBookmark.getTag());
                            xmlFavorite.WriteXMLFavorite((ArrayList<New>) newsFavortite);
                            Toast.makeText(context, "Bạn vừa lưu bài báo này vào tệp lưu", Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        Log.e("Loi", e.toString());
                    }

                }
            });
            view = itemView;
        }

        public void setData(New newModel) {
            if (!newModel.image.isEmpty()) {
                Picasso.get().load(newModel.image).into(ivNew);
            }
            tvTitle.setText(newModel.title);
            tvDescription.setText(newModel.description);
            tvTime.setText(newModel.pubDate);

            setIvBookMark(ivBookmark, newsFavortite, newModel);

        }

        private void setIvBookMark(ImageView ivBookmark, List<New> listFov, New n) {
            if (listFov != null) {
                for (New n2 : listFov
                        ) {
                    if (n2.title.compareToIgnoreCase(n.title) == 0) {
                        ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
                        ivBookmark.setTag(n);
                        return;
                    }
                }
            }
            ivBookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
            ivBookmark.setTag(n);
        }
    }
}
