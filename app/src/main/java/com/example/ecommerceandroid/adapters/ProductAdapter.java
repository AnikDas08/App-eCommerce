package com.example.ecommerceandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceandroid.ProductDetails;
import com.example.ecommerceandroid.R;
import com.example.ecommerceandroid.databinding.ItemProductBinding;
import com.example.ecommerceandroid.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProductAdapter extends FirebaseRecyclerAdapter<Product,ProductAdapter.myViewholder> {
    Context context;
    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder myViewholder, int i, @NonNull Product product) {
        myViewholder.binding.level.setText(product.getName());
        myViewholder.binding.price.setText("BDT "+product.getPrice());
        myViewholder.binding.details.setText(product.getDetails());
        Glide.with(myViewholder.binding.image.getContext())
                .load(product.getPimage())
                .into(myViewholder.binding.image);
        myViewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ProductDetails.class);
                intent.putExtra("name",product.getName());
                intent.putExtra("details",product.getDetails());
                intent.putExtra("roll",product.getRoll());
                intent.putExtra("price",product.getPrice());
                intent.putExtra("image",product.getPimage());
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product,viewGroup,false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

        ItemProductBinding binding;
        public myViewholder(@NonNull View itemView) {
            super(itemView);
            binding=ItemProductBinding.bind(itemView);
        }
    }
}
