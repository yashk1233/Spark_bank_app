package com.example.listviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer> {

    private List<Customer> arr;

    public CustomerAdapter(@NonNull Context context, int resource, @NonNull List<Customer> arr) {
        super(context, resource, arr);
        this.arr= arr;
    }

    @Nullable
    @Override
    public Customer getItem(int position) {
        return arr.get(position);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custdetails,parent,false);
        TextView t= convertView.findViewById(R.id.textView);
        TextView balance= convertView.findViewById(R.id.balance);

        t.setText( getItem(position).getCname());
        balance.setText("Balance : ₹ "+getItem(position).getBalance());

        return convertView;
    }
}


