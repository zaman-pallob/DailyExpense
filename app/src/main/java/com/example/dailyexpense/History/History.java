package com.example.dailyexpense.History;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dailyexpense.Adapter.HistoryListAdapter;
import com.example.dailyexpense.Adapter.ModelData;
import com.example.dailyexpense.DataBase.DataBase;
import com.example.dailyexpense.R;

import java.util.ArrayList;
import java.util.Calendar;

public class History extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Context context;
    View view;

    Spinner spinner;
    DatePickerDialog pickerDialog1, pickerDialog2;
    String[] types = {"Expense", "Income"};
    ArrayAdapter arrayAdapter;
    TextView from, to,total;
    String from_date,to_date;
    int selectionvalue=0;
    int from_year, from_month, from_day, to_year, to_month, to_day; //initialize them to current date in onStart()/onCreate()
    DatePickerDialog.OnDateSetListener from_dateListener, to_dateListener;
    HistoryListAdapter adapter;
    DataBase object;
    ListView listView;
    ArrayList<ModelData>data;
    public History(Context context) {
        this.context = context;
        object=new DataBase(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.historylaout, container, false);
        spinner = view.findViewById(R.id.spinnerId);
        listView=view.findViewById(R.id.historyList);

        from = view.findViewById(R.id.fromDate);
        to = view.findViewById(R.id.toDate);
        total=view.findViewById(R.id.calculation);
        from.setOnClickListener(this);
        to.setOnClickListener(this);
        from.setText(currentDate());
        to.setText(currentDate());
        adapter=new HistoryListAdapter(context);
        listView.setAdapter(adapter);
        data=object.getList(0,from_date,to_date);
        adapter.setData(data);
        total.setText("Total Expense : "+calculate(data));
        spinner.setOnItemSelectedListener(this);
        arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, types);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        from_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthindex, int dayofmonth) {
                String daystring=dayofmonth<10?"0"+dayofmonth:String.valueOf(dayofmonth);
                String monthstring=(monthindex+1)<10?"0"+(monthindex+1):String.valueOf(monthindex+1);
                from_date=daystring+"-"+monthstring+"-"+year;
                from.setText(from_date);
                data=object.getList(selectionvalue,from_date,to_date);
                adapter.setData(data);
                pickerDialog1.dismiss();
                if (selectionvalue==0){
                    total.setText("Total Expense : "+calculate(data));
                }else{
                    total.setText("Total Income : "+calculate(data));
                }


            }
        };

        to_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthindex, int dayofmonth) {
                String daystring=dayofmonth<10?"0"+dayofmonth:String.valueOf(dayofmonth);
                String monthstring=(monthindex+1)<10?"0"+(monthindex+1):String.valueOf(monthindex+1);
                to_date=daystring+"-"+monthstring+"-"+year;
                to.setText(to_date);
                data=object.getList(selectionvalue,from_date,to_date);
                adapter.setData(data);
                pickerDialog2.dismiss();
                if (selectionvalue==0){
                    total.setText("Total Expense : "+calculate(data));
                }else{
                    total.setText("Total Income : "+calculate(data));
                }

            }
        };


        return view;
    }
    public  String calculate(ArrayList<ModelData>data){
        int sum=0;
        int length=data.size();
        for (int i=0;i<length;i++){
            sum+=data.get(i).getValue();
        }

        return String.valueOf(sum)+" \u09F3";
    }
    public  String currentDate(){
            from_day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            from_month=Calendar.getInstance().get(Calendar.MONTH)+1;
            from_year=Calendar.getInstance().get(Calendar.YEAR);
            to_day=from_day;
            to_month=from_month;
            to_year=from_year;

        String daystring=from_day<10?"0"+from_day:String.valueOf(from_day);
        String monthstring=from_month<10?"0"+from_month:String.valueOf(from_month);
        from_date= daystring+"-"+monthstring+"-"+from_year;
        to_date=from_date;
        return to_date;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectionvalue=i;
        data=object.getList(selectionvalue,from_date,to_date);
        adapter.setData(data);
        if (selectionvalue==0){
            total.setText("Total Expense : "+calculate(data));
        }else{
            total.setText("Total Income : "+calculate(data));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fromDate:
                pickerDialog1 = new DatePickerDialog(context, from_dateListener,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                pickerDialog1.show();
                break;
            case R.id.toDate:
                pickerDialog2 = new DatePickerDialog(context, to_dateListener,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                pickerDialog2.show();
                break;
        }
    }
}
