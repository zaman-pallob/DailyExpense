package com.example.dailyexpense.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.dailyexpense.R;
import java.util.ArrayList;


public class HistoryListAdapter extends BaseAdapter {
    Context context;
    ArrayList<ModelData>data;
    LayoutInflater inflater;

    public HistoryListAdapter(Context context) {
    this.context=context;
    data=new ArrayList<>();
    inflater= LayoutInflater.from(context);
    }

    public void setData(ArrayList<ModelData>data){

        this.data=data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyVewHolder holder;
        if (view==null){

            view=inflater.inflate(R.layout.historyblock,viewGroup,false);
            holder=new MyVewHolder(view);
            holder.Name.setText(data.get(i).getName());
            holder.Value.setText(String.valueOf(data.get(i).getValue())+" \u09F3");
            holder.DateText.setText(data.get(i).getDate());
            view.setTag(holder);
        }else{
           holder=(MyVewHolder)view.getTag();
            holder.Name.setText(data.get(i).name);
            holder.Value.setText(String.valueOf(data.get(i).value)+" \u09F3");
            holder.DateText.setText(data.get(i).getDate());
        }
        return view;
    }


    class MyVewHolder{
        TextView Name,Value,DateText;

        public MyVewHolder(View view) {
            Name=view.findViewById(R.id.name);
            Value=view.findViewById(R.id.value);
            DateText=view.findViewById(R.id.date);
        }
    }
}
