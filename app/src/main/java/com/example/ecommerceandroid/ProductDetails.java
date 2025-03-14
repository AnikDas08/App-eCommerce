package com.example.ecommerceandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.ecommerceandroid.databinding.ActivityProductDetailsBinding;

import java.util.ArrayList;

public class ProductDetails extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
    int total=1;
    String b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String name=getIntent().getStringExtra("name");
        String details=getIntent().getStringExtra("details");
        String roll=getIntent().getStringExtra("roll");
        String prices=getIntent().getStringExtra("price");
        int price=Integer.parseInt(prices);
        String image=getIntent().getStringExtra("image");

        binding.ProductText.setText(name);
        binding.DetailPrice.setText(prices);
        binding.ProductDetailwork.setText(details);
        Glide.with(this)
                .load(image)
                .into(binding.ProductImage);

        binding.Decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int item = Integer.parseInt(binding.Quantites.getText().toString());
                if (item > 1) {
                    total = item - 1;
                    binding.DetailPrice.setText(String.format("%d", price * total));
                    b = String.valueOf(total);
                    binding.Quantites.setText(b);
                } else {
                    total = 1;
                    b = String.valueOf(total);
                    binding.Quantites.setText(b);
                }
            }
        });

        binding.Increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int item=Integer.parseInt(binding.Quantites.getText().toString());
                total=item+1;
                binding.DetailPrice.setText(String.format("%d",price * total));
                b=String.valueOf(total);
                binding.Quantites.setText(b);

            }
        });




        binding.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*ProgressDialog dialog=new ProgressDialog(view.getContext());
                dialog.setTitle("Uploading");
                dialog.show();

                String userName=binding.NameEdit.getText().toString();
                String useremail=binding.Email.getText().toString();
                String userNumber=binding.NumberEdit.getText().toString();

                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference root=db.getReference("order");
                OrderInsert order=new OrderInsert(roll,name,course,userName,useremail,userNumber,binding.Quantites.getText().toString(),image,binding.DetailPrice.getText().toString());
                root.child(roll).setValue(order);

                dialog.dismiss();*/



                Intent intent=new Intent(ProductDetails.this,OrderActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("details",details);
                intent.putExtra("quantity",binding.Quantites.getText().toString());
                intent.putExtra("price",binding.DetailPrice.getText().toString());
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });
        //lkdjfkdjfjdkljkjd

        getSupportActionBar().setTitle(name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*if(item.getItemId()==R.id.CartId){
            startActivity(new Intent(ProductDetails.this,CartActivity.class));
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProductDetails.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}