package com.example.ecommerceandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecommerceandroid.adapters.ProductAdapter;
import com.example.ecommerceandroid.databinding.ActivityCatagoryBinding;
import com.example.ecommerceandroid.model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CatagoryActivity extends AppCompatActivity {
    ActivityCatagoryBinding binding;
    ProductAdapter productAdapter;
    String catagory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCatagoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        catagory=getIntent().getStringExtra("category_name");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("products");
        Query query = reference.orderByChild("name").equalTo(catagory);

        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(query, Product.class)
                .build();
        binding.TextView.setText(catagory);
        productAdapter= new ProductAdapter(options, this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.Rv.setLayoutManager(layoutManager);
        binding.Rv.setAdapter(productAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        productAdapter.startListening();  // Start listening for Firebase data
    }

    @Override
    protected void onStop() {
        super.onStop();
        productAdapter.stopListening();  // Stop listening for Firebase data
    }

}