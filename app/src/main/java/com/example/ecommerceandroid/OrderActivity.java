package com.example.ecommerceandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.ecommerceandroid.adapters.OrderAdapter;
import com.example.ecommerceandroid.databinding.ActivityOrderBinding;
import com.example.ecommerceandroid.model.OrderShow;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name=getIntent().getStringExtra("name");
        String course=getIntent().getStringExtra("course");
        String quantity=getIntent().getStringExtra("quantity");
        String contact=getIntent().getStringExtra("contact");
        String image=getIntent().getStringExtra("image");

        OrderShow order=new OrderShow(course,name,quantity,contact,image);

        ArrayList<OrderShow> list = new ArrayList<>();
        list.add(order);

        OrderAdapter adapter=new OrderAdapter(this,list);
        binding.Rv.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.Rv.setLayoutManager(layoutManager);










    }
}