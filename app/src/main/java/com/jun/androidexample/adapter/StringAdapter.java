package com.jun.androidexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jun.androidexample.R;

import java.util.List;

/**
 * Item为简单String的RecyclerView的Adapter
 */
public class StringAdapter extends
        RecyclerView.Adapter<StringAdapter.StringViewHolder> {

    private Context context;
    private List<String> dataList;

    public StringAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_string, parent, false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StringViewHolder holder, int position) {

        holder.textView.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class StringViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public StringViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
