package com.example.gk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassAdapter extends BaseAdapter {

    Context context;
    ArrayList<Class> listC;

    public ClassAdapter(Context c, ArrayList<Class> ds){
        this.context = c;
        this.listC = ds;
    }


    @Override
    public int getCount() {
        return listC.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inf = ((Activity) context).getLayoutInflater();
        view = inf.inflate(R.layout.item_class, null);

        TextView tv_col1 = view.findViewById(R.id.tv_col1);
        TextView tv_col2= view.findViewById(R.id.tv_col2);
        TextView tv_col3 = view.findViewById(R.id.tv_col3);
        TextView tv_col4 = view.findViewById(R.id.tv_col4);
        TextView tv_col5 = view.findViewById(R.id.tv_col5);

        tv_col1.setText(listC.get(i).col1);
        tv_col2.setText(listC.get(i).col2);
        tv_col3.setText(listC.get(i).col3);
        tv_col4.setText(listC.get(i).col4);
        tv_col5.setText(listC.get(i).col5);


        return view;

    }
}
