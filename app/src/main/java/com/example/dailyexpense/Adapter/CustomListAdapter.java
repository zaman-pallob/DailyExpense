package com.example.dailyexpense.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dailyexpense.R;

import java.util.ArrayList;


public class CustomListAdapter extends BaseAdapter {
    int numberofObjects=0;
    Context context;
    ArrayList<Data>data;
    ArrayList<ViewHolder>objectList;
    LayoutInflater inflater;
    boolean isPressed=false;
    public CustomListAdapter(Context context) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        objectList=new ArrayList<>();
    }
    public  void setNumberofObjects(int numberofObjects){
        this.numberofObjects=numberofObjects;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return numberofObjects;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
            if (view==null){

                view=inflater.inflate(R.layout.singleview,viewGroup,false);
                holder =new ViewHolder(view);
                objectList.add(holder);
                holder.Namewatchers=new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    holder.itemName=holder.fieldName.getText().toString();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                };

                holder.ValueWatchers=new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        holder.itemValue=holder.fieldValue.getText().toString();


                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                };

                holder.fieldName.addTextChangedListener(holder.Namewatchers);
                holder.fieldValue.addTextChangedListener(holder.ValueWatchers);
                view.setTag(holder);

            }else{
                holder=(ViewHolder)view.getTag();
            }

        return view;
    }
    class  ViewHolder{
        TextWatcher Namewatchers,ValueWatchers;
        EditText fieldName;
        EditText fieldValue;
        String itemName,itemValue;

        public ViewHolder(View view) {
            fieldName=view.findViewById(R.id.fieldName);
            fieldValue=view.findViewById(R.id.fieldValue);

        }
    }



  public ArrayList<Data> getObjects(){
      data=new ArrayList<>();
      for (int i=0;i<objectList.size()-1;i++){
                data.add(i,new Data(objectList.get(i).itemName,objectList.get(i).itemValue));
            }

     return data;
    }
}
