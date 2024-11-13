package com.example.ecommerceandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.ecommerceandroid.databinding.ActivityMainBinding;
import com.example.ecommerceandroid.databinding.ActivityProductDetailsBinding;
import com.example.ecommerceandroid.model.OrderInsert;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductDetails extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
    int a=1;
    String b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String name=getIntent().getStringExtra("name");
        String course=getIntent().getStringExtra("course");
        String roll=getIntent().getStringExtra("roll");
        String contact=getIntent().getStringExtra("contact");
        int price=Integer.parseInt(contact);
        String image=getIntent().getStringExtra("image");

        binding.ProductText.setText(name);
        binding.DetailPrice.setText(contact);
        binding.ProductDetailwork.setText(course);
        Glide.with(this)
                .load(image)
                .into(binding.ProductImage);

        String userName=binding.NameEdit.getText().toString();
        String userNumber=binding.NumberEdit.getText().toString();

        binding.Decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = Integer.parseInt(binding.Quantites.getText().toString());
                if (c > 1) {
                    a = c - 1;
                    binding.DetailPrice.setText(String.format("%d", price * a));
                    b = String.valueOf(a);
                    binding.Quantites.setText(b);
                } else {
                    a = 1;
                    b = String.valueOf(a);
                    binding.Quantites.setText(b);
                }
            }
        });

        binding.Increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c=Integer.parseInt(binding.Quantites.getText().toString());
                a=c+1;
                binding.DetailPrice.setText(String.format("%d",price * a));
                b=String.valueOf(a);
                binding.Quantites.setText(b);

            }
        });


        binding.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog dialog=new ProgressDialog(view.getContext());
                dialog.setTitle("Uploading");
                dialog.show();

                String userName=binding.NameEdit.getText().toString();
                String useremail=binding.Email.getText().toString();
                String userNumber=binding.NumberEdit.getText().toString();

                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference root=db.getReference("order");
                OrderInsert order=new OrderInsert(roll,name,course,userName,useremail,userNumber,binding.Quantites.getText().toString(),image,binding.DetailPrice.getText().toString());
                root.child(roll).setValue(order);

                dialog.dismiss();

                Intent intent=new Intent(ProductDetails.this,OrderActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("course",course);
                intent.putExtra("quantity",binding.Quantites.getText().toString());
                intent.putExtra("contact",contact);
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
        if(item.getItemId()==R.id.CartId){
            startActivity(new Intent(ProductDetails.this,CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}