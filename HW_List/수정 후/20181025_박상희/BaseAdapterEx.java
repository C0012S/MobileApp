package com.example.hw_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseAdapterEx extends BaseAdapter {
    Context mContext = null;
    ArrayList<Student> mData = null;
    LayoutInflater mLayoutInflater = null;

    public BaseAdapterEx(Context context, ArrayList<Student> data) {
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

        TextView nameTv = (TextView) itemLayout.findViewById(R.id.t_name);
        TextView numberTv = (TextView) itemLayout.findViewById(R.id.t_number);
        TextView departmentTv = (TextView) itemLayout.findViewById(R.id.t_dept);

        nameTv.setText(mData.get(position).mName);
        numberTv.setText(mData.get(position).mNumber);
        departmentTv.setText(mData.get(position).mDepartment);

        return itemLayout;
    }

    public void add(int index, Student addData) {
        mData.add(index, addData);
        notifyDataSetChanged();
    }

    public void delete(int index) {
        mData.remove(index);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
