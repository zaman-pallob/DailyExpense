package com.example.dailyexpense.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dailyexpense.Expense.Expense;
import com.example.dailyexpense.History.History;
import com.example.dailyexpense.Income.Income;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {

    String Title[]={"Expense","Income","History"};
    ArrayList<Fragment> uFragment = new ArrayList<>();
    Context context;

    public PagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;


    }


    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:

                return new Expense(context);
            case 1:

                return new Income(context);

            default:
                return new History(context);
        }

    }

    @Override
    public int getCount() {
        return Title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Title[position];
    }

}