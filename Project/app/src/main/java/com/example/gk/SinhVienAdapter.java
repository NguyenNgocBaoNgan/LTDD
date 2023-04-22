package com.example.gk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SinhVienAdapter extends BaseAdapter {

    Context context;
    ArrayList<SinhVien> listSV;

    public SinhVienAdapter(Context c, ArrayList<SinhVien> ds){
        this.context = c;
        this.listSV = ds;
    }

    @Override
    public int getCount() {
        return listSV.size();
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
        view = inf.inflate(R.layout.item_sinhvien, null);

        TextView tv_name_sv = view.findViewById(R.id.tv_name_sv);
        TextView tv_date= view.findViewById(R.id.tv_date);
        TextView tv_idClass = view.findViewById(R.id.tv_idClass);

        tv_name_sv.setText(listSV.get(i).name);
        tv_date.setText(listSV.get(i).date);
        tv_idClass.setText(listSV.get(i).id_class);


        return view;
    }
}
