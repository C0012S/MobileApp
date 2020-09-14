package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    public int getCount() {
        return mData.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return mData.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout = mLayoutInflater.inflate(R.layout.list_view_item_layout, null);

        TextView nameTv = (TextView) itemLayout.findViewById(R.id.tv_name);
        TextView numberTv = (TextView) itemLayout.findViewById(R.id.tv_number);
        TextView departmentTv = (TextView) itemLayout.findViewById(R.id.tv_dept);

        nameTv.setText(mData.get(position).mName);
        numberTv.setText(mData.get(position).mNumber);
        departmentTv.setText(mData.get(position).mDepartment);

        return itemLayout;
    }
}
