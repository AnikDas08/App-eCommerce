package com.example.ecommerceandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.PixelCopy;

import com.example.ecommerceandroid.adapters.CatagoryAdapter;
import com.example.ecommerceandroid.adapters.ProductAdapter;
import com.example.ecommerceandroid.databinding.ActivityMainBinding;
import com.example.ecommerceandroid.model.Catagory;
import com.example.ecommerceandroid.model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ProductAdapter productAdapter;
    ActivityMainBinding binding;
    CatagoryAdapter catagoryAdapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        initSlider();
        initCatagory();
        initProduct();


    }




    public void initProduct(){

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students"), Product.class)
                        .build();
        productAdapter=new ProductAdapter(options,this);

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.ProductList.setLayoutManager(layoutManager);
        binding.ProductList.setAdapter(productAdapter);

    }

    public void initSlider(){
        ArrayList<String> arrayList=new ArrayList<>();
        binding.carousel.addData(new CarouselItem(R.drawable.slider1,"Image 1"));
        binding.carousel.addData(new CarouselItem(R.drawable.slider2,"Image 2"));
        binding.carousel.addData(new CarouselItem(R.drawable.slider3,"Image 3"));

    }

    public void initCatagory(){

        ArrayList<Catagory> catagories= new ArrayList<>();
        catagories.add(new Catagory("Food",R.drawable.catagoryfoodimg));
        catagories.add(new Catagory("Jursy",R.drawable.catagoryjursyimag));
        catagories.add(new Catagory("Electronics",R.drawable.catagoryelectronicsimg));
        catagories.add(new Catagory("Food",R.drawable.catagoryfoodimg));
        catagories.add(new Catagory("Jursy",R.drawable.catagoryjursyimag));
        catagories.add(new Catagory("Electronics",R.drawable.catagoryelectronicsimg));
        catagories.add(new Catagory("Food",R.drawable.catagoryfoodimg));
        catagories.add(new Catagory("Jursy",R.drawable.catagoryjursyimag));
        catagories.add(new Catagory("Electronics",R.drawable.catagoryelectronicsimg));


        catagoryAdapter=new CatagoryAdapter(this,catagories);

        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        binding.catagorylist.setLayoutManager(layoutManager);
        binding.catagorylist.setAdapter(catagoryAdapter);




    }

    @Override
    protected void onStart() {
        super.onStart();
        productAdapter.startListening();
    }




}