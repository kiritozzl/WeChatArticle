package com.example.kirito.wechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirito.wechat.R;
import com.example.kirito.wechat.entity.Item;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kirito on 2016.10.20.
 */

public class ListAdapter extends BaseAdapter {
    private List<Item> items;
    private Context context;

    public ListAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.items,null);
            holder = new viewHolder(convertView);
            convertView.setTag(holder);
        }
        Item item = items.get(position);
        holder = (viewHolder) convertView.getTag();
        holder.iv.setImageURI(item.getImg());
        holder.tv_source.setText("来源:"+item.getSource());
        holder.tv_title.setText(item.getTitle());

        return convertView;
    }

    class viewHolder{
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.tv_source)
        TextView tv_source;
        @BindView(R.id.tv_title)
        TextView tv_title;

        public viewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
