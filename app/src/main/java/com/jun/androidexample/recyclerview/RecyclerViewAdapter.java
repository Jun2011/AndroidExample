package com.jun.androidexample.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jun.androidexample.R;

import java.util.List;

/**
 * RecyclerViewAdapter
 */
public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    // Item的类型：头部、普通、足部
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;

    private Context context;
    // 新闻列表数据
    private List<NewsList> newsLists;

    public RecyclerViewAdapter(Context context, List<NewsList> newsLists) {

        this.context = context;
        this.newsLists = newsLists;
    }

    @Override
    public int getItemCount() {

        return newsLists.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {


        if (position == 0) {

            // 最前面是头部
            return TYPE_HEADER;
        } else if (position == newsLists.size() + 1) {

            // 最后面是足部
            return TYPE_FOOTER;
        } else {

            return TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        RecyclerViewHolder recyclerViewHolder;
        switch (viewType) {
            // 加载头部布局
            case TYPE_HEADER:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.item_news_list_header, parent, false);
                recyclerViewHolder = new RecyclerViewHolder(view, viewType);
                return recyclerViewHolder;
            // 加载普通布局
            case TYPE_NORMAL:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.item_news_list_normal, parent, false);
                recyclerViewHolder = new RecyclerViewHolder(view, viewType);
                return recyclerViewHolder;
            // 加载足部布局
            case TYPE_FOOTER:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.item_news_list_footer, parent, false);
                recyclerViewHolder = new RecyclerViewHolder(view, viewType);
                return recyclerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

//        int layoutPosition = holder.getLayoutPosition();

        if (position == 0) {

            holder.headerTextView.setText("头部");
        } else if (position == newsLists.size() + 1) {

            holder.footerTextView.setText("足部");
        } else if (position != 0
                && position != newsLists.size() + 1) {

            Glide.with(context)
                    .load(newsLists.get(position - 1).getImageUrl())
                    .into(holder.imageView);
            holder.normalTextView.setText(newsLists.get(position - 1).getTitle());
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        // 将所有类型所涉及的View都声明一遍
        int viewType;
        ImageView imageView;
        TextView headerTextView, normalTextView, footerTextView;


        public RecyclerViewHolder(View itemView, int viewType) {
            super(itemView);

            this.viewType = viewType;

            // 根据不同类型进行有针对性的初始化
            if (viewType == TYPE_HEADER) {

                headerTextView = (TextView) itemView.findViewById(R.id.header_text_view);
            } else if (viewType == TYPE_FOOTER) {

                footerTextView = (TextView) itemView.findViewById(R.id.footer_text_view);
            } else {

                imageView = (ImageView) itemView.findViewById(R.id.image_view);
                normalTextView = (TextView) itemView.findViewById(R.id.normal_text_view);
            }
        }
    }

    // 更新数据
    public void updateData(List<NewsList> newsLists) {

        this.newsLists = newsLists;

        // 通知数据改变了
        notifyDataSetChanged();
    }

    // 添加数据
    public void addData(List<NewsList> newsLists) {

        if (this.newsLists == null) {

            // 如果原本的数据为空则调用更新
            updateData(newsLists);
        } else {

            this.newsLists.addAll(newsLists);

            // 通知数据改变了
            notifyDataSetChanged();
        }
    }
}
