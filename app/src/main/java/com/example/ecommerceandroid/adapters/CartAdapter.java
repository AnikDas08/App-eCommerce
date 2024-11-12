package com.example.ecommerceandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceandroid.R;
import com.example.ecommerceandroid.databinding.ItemCartBinding;
import com.example.ecommerceandroid.model.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.cartViewHolder> {
    Context context;
    ArrayList<Product> products;

    public CartAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new cartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull cartViewHolder cartViewHolder, int i) {
        Product product=products.get(i);
        Glide.with(context)
                .load(product.getPimage())
                .into(cartViewHolder.binding.CartImage);
        cartViewHolder.binding.CartNameId.setText(product.getName());
        cartViewHolder.binding.CartPriceId.setText("BDT "+product.getContact());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class cartViewHolder extends RecyclerView.ViewHolder{
        ItemCartBinding binding;
        public cartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemCartBinding.bind(itemView);
        }
    }
}
