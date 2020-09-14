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
    ArrayList<MainActivity.Student> mData = null;
    LayoutInflater mLayoutInflater = null;

    public BaseAdapterEx(Context context, ArrayList<MainActivity.Student> data) {
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
        //1.어댑터뷰가 재사용할 뷰를 넘겨주지 않은 경우에만 새로운 뷰를 생성
        View itemLayout = convertView;
        if (itemLayout == null) {
            itemLayout = mLayoutInflater.inflate(R.layout.list_view_item_layout, null);
        }

        //2. 현재 아이템에 내용을 변경할 뷰를 얻음
        TextView nameTv = (TextView) itemLayout.findViewById(R.id.tv_name);
        TextView numberTv = (TextView) itemLayout.findViewById(R.id.tv_number);
        TextView departmentTv = (TextView) itemLayout.findViewById(R.id.tv_dept);

        //3. 이름, 학번, 학과 데이터를 참조하여 레이아웃을 갱신
        nameTv.setText(mData.get(position).mName);
        numberTv.setText(mData.get(position).mNumber);
        departmentTv.setText(mData.get(position).mDepartment);

        return itemLayout;
    }

    public void add(int index, MainActivity.Student addData) {
        mData.add(index, addData);
        notifyDataSetChanged();
    }

    public void delete(int index){
        mData.remove(index);
        notifyDataSetChanged();
    }

    public void clear(){
        mData.clear();
        notifyDataSetChanged();
    }

}
