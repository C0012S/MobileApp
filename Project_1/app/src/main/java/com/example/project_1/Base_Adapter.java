package com.example.project_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Base_Adapter extends BaseAdapter {
    Context mContext = null;
    ArrayList<SiteInfo> mData = null;
    LayoutInflater mLayoutInflater = null;

    public Base_Adapter(Context context, ArrayList<SiteInfo> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemLayout = convertView;
        if (itemLayout == null) {
            itemLayout = mLayoutInflater.inflate(R.layout.listview_item_layout, null);
        }

        TextView site_nameTv =(TextView) itemLayout.findViewById(R.id.tv_site_name);
        TextView urlTv = (TextView) itemLayout.findViewById(R.id.tv_url);
        TextView userTv = (TextView) itemLayout.findViewById(R.id.tv_user);

        site_nameTv.setText(mData.get(position).site_name);
        urlTv.setText(mData.get(position).url);
        userTv.setText(mData.get(position).user);

        return itemLayout;
    }

    public void add(int index, SiteInfo addData) {
        mData.add(index, addData);
        notifyDataSetChanged();
    }

    public void delete(int index) {
        mData.remove(index);
        notifyDataSetChanged();
    }

    public void update(int index, SiteInfo data) {
        mData.set(index, data);
        notifyDataSetChanged();
    }
}
