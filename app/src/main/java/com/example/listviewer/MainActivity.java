package com.example.listviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.listviewer.Customer;
import com.example.listviewer.Dbhandler;
import com.example.listviewer.R;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
//    Map<String, String> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dbhandler handler = new Dbhandler(this,"bank",null,1);
//        handler.onUpgrade();
//        handler.addCustomer(new Customer("Yash Kakade",5000,"ulwe",20));
//        handler.addCustomer(new Customer("Maya Kakade",5000,"ulwe",40));
//        handler.addCustomer(new Customer("Ravina Kakade",5000,"ulwe",24));
//        handler.addCustomer(new Customer("Renu Kakade",5000,"ulwe",25));




//        showing all customers in list view
        ArrayList<String> cust = new ArrayList<>();

        listView = findViewById(R.id.listview);

        List<Customer> allcust = handler.getAllCustomer();

        Customer[] arr = new Customer[allcust.size()];

        for (int i = 0; i < allcust.size(); i++)
            arr[i] = allcust.get(i);

        for(Customer c : allcust){
            Log.d("mytag","id : "+ c.getCid() +" NAME : "+c.getCname()+" Balance : "+c.getBalance());
            cust.add(c.getCname() + " ( "+c.getBalance()+")");



        }

//        using Array Adapter
//        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cust);
//        listView.setAdapter(ad);

//        using Custom Adapter
        CustomerAdapter ad = new CustomerAdapter(this,R.layout.custdetails,allcust);
        listView.setAdapter(ad);




        handler.close();


    }
}