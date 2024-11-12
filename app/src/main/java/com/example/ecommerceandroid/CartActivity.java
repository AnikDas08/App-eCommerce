package com.example.ecommerceandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.ecommerceandroid.adapters.CartAdapter;
import com.example.ecommerceandroid.databinding.ActivityCartBinding;
import com.example.ecommerceandroid.model.Product;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    CartAdapter cartAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        products=new ArrayList<>();

        cartAdapter=new CartAdapter(this,products);

        binding.CartRecyler.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}