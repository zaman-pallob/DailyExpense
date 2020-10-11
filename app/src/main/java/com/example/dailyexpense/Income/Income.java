package com.example.dailyexpense.Income;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dailyexpense.Adapter.CustomListAdapter;
import com.example.dailyexpense.Adapter.Data;
import com.example.dailyexpense.DataBase.DataBase;
import com.example.dailyexpense.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Income extends Fragment implements View.OnClickListener,DatePickerDialog.OnDateSetListener{
    Context context;
    View view;
    TextView datePicker;
    ListView listView;
    Button addItem,clearAll,SaveButton;
    CustomListAdapter adapter;
    int counter;
    ArrayList<Data> objectList;
    DatePickerDialog datePickerDialog;
    DataBase dataBase;
    String currentDate;
    int day=0,month=0,year=0;
    public Income(Context context) {
        this.context=context;
        dataBase=new DataBase(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        counter=1;
        view = inflater.inflate(R.layout.incomeexpense, container, false);
        listView=view.findViewById(R.id.expenseList);
        clearAll=view.findViewById(R.id.clearButton);
        addItem=view.findViewById(R.id.addItembutton);
        SaveButton=view.findViewById(R.id.saveData);
        datePicker=view.findViewById(R.id.datePicker);
        adapter=new CustomListAdapter(context);
        listView.setAdapter(adapter);
        addItem.setOnClickListener(this);
        datePicker.setOnClickListener(this);
        clearAll.setOnClickListener(this);
        SaveButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addItembutton:
                adapter.setNumberofObjects(counter++);
                break;
            case R.id.clearButton:
                adapter.setNumberofObjects(0);
                counter=1;
                break;
            case R.id.saveData:
                int value=0;
                objectList=adapter.getObjects();
                if (year!=0 &month!=0&day!=0){
                    for (int i=0;i<objectList.size();i++){
                        if(objectList.get(i).getFieldValue().isEmpty()){
                            value=0;
                        }else {
                            value=Integer.parseInt(objectList.get(i).getFieldValue());
                        }
                        dataBase.insert(1,objectList.get(i).getFieldName(),value,currentDate);
                    }
                }else {

                    Toast.makeText(context, "Please Choose a date first", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.datePicker:
                datePickerDialog();
                break;

        }
    }
    public  void datePickerDialog(){
        datePickerDialog =new DatePickerDialog(
                getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker picker, int year, int monthindex, int dayofmonth) {
        String daystring=dayofmonth<10?"0"+dayofmonth:String.valueOf(dayofmonth);
        String monthstring=(monthindex+1)<10?"0"+(monthindex+1):String.valueOf(monthindex+1);
        day=dayofmonth;
        month=monthindex+1;
        this.year=year;
        currentDate=daystring+"-"+monthstring+"-"+year;
        datePicker.setText(currentDate);
        datePickerDialog.dismiss();
    }

}
