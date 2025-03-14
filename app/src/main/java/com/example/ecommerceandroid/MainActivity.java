package com.example.ecommerceandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.PixelCopy;
import android.widget.Toast;

import com.example.ecommerceandroid.adapters.CatagoryAdapter;
import com.example.ecommerceandroid.adapters.ProductAdapter;
import com.example.ecommerceandroid.databinding.ActivityMainBinding;
import com.example.ecommerceandroid.model.Catagory;
import com.example.ecommerceandroid.model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ProductAdapter productAdapter;
    ActivityMainBinding binding;
    CatagoryAdapter catagoryAdapter;
    ActionBarDrawerToggle toggle;
    ArrayList<Catagory> catagories;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home) {
                    Toast.makeText(MainActivity.this, "Home activity", Toast.LENGTH_SHORT).show();
                } else if(item.getItemId() == R.id.nav_settings) {
                    Toast.makeText(MainActivity.this, "Setting activity", Toast.LENGTH_SHORT).show();
                } else if(item.getItemId() == R.id.nav_about) {
                    Toast.makeText(MainActivity.this, "About activity", Toast.LENGTH_SHORT).show();
                }
                binding.drawerLayout.closeDrawers();
                return true;
            }
        });

        /*binding.searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (enabled) {
                    binding.drawerLayout.openDrawer(binding.navigationView);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                // Handle Search Confirmed
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                if (buttonCode == MaterialSearchBar.BUTTON_NAVIGATION) {
                    binding.drawerLayout.openDrawer(binding.navigationView);
                }
            }
        });*/





        initSlider();
        initCatagory();
        initProduct();
        setupSearchBar();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSearchBar() {
        binding.searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCategories(s.toString()); // Filter categories as user types
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.searchBar.setOnSearchActionListener(new com.mancj.materialsearchbar.MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled) {
                    // Reset the list when search is closed
                    catagoryAdapter.filterList(catagories);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                // Handle Search Confirmed
                System.out.println("kdjf");
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                if (buttonCode == MaterialSearchBar.BUTTON_NAVIGATION) {
                    binding.drawerLayout.openDrawer(binding.navigationView);
                }
            }
        });
    }

    private void filterCategories(String query) {
        ArrayList<Catagory> filteredList = new ArrayList<>();
        for (Catagory category : catagories) {
            if (category.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(category);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No categories found", Toast.LENGTH_SHORT).show();
        }

        // Update the adapter with the filtered list
        catagoryAdapter.filterList(filteredList);
    }




    public void initProduct(){

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), Product.class)
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

        catagories= new ArrayList<>();
        catagories.add(new Catagory("পাঞ্জাবি",R.drawable.catagoryfoodimg));
        catagories.add(new Catagory("জামা",R.drawable.catagoryjursyimag));
        catagories.add(new Catagory("শাড়",R.drawable.catagoryelectronicsimg));
        catagories.add(new Catagory("shirt",R.drawable.catagoryfoodimg));
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