package com.example.dailyexpense.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.dailyexpense.Adapter.ModelData;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    Context context;

    public static final String DatabaseName = "DatabaseofDailyExpense.db";
    public static final String TableName = "TableInformation";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ITEM";
    public static final String COL_3 = "AMOUNT";
    public static final String COL_4 = "DATE";

    public DataBase(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + " (ID INTEGER,ITEM TEXT,AMOUNT INTEGER,DATE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public void insert(int type, String itemName, int itemvalue, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, type);
        contentValues.put(COL_2, itemName);
        contentValues.put(COL_3, itemvalue);
        contentValues.put(COL_4, date);

        long result = db.insert(TableName, null, contentValues);
        if (result==-1)
        {
            Toast.makeText(context, "Failed to insert", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<ModelData> getList(int type,String fromdate, String todate) {
        SQLiteDatabase db = this.getWritableDatabase();
       ArrayList<ModelData> data = new ArrayList<>();
        String SQL = "SELECT ITEM,AMOUNT,DATE FROM " + TableName + " WHERE ID="+type+" AND DATE BETWEEN " + "'" + fromdate + "'" + " AND " + "'" + todate + "'";
        Cursor cursor = db.rawQuery(SQL, null);
        while (cursor.moveToNext()) {
            data.add(new ModelData(cursor.getString(0), cursor.getInt(1), cursor.getString(2)));
            System.out.println(cursor.getString(0)+"  "+cursor.getInt(1)+"  "+cursor.getString(2));
        }


        return data;
    }


}
