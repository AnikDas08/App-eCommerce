package com.example.ecommerceandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceandroid.R;
import com.example.ecommerceandroid.databinding.ItemCatagoriesBinding;
import com.example.ecommerceandroid.model.Catagory;

import java.util.ArrayList;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.CtagoryViewHolder>  {
    Context context;
    ArrayList<Catagory> catagories;

    public CatagoryAdapter(Context context, ArrayList<Catagory> catagories) {
        this.context = context;
        this.catagories = catagories;
    }

    @NonNull
    @Override
    public CtagoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CtagoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_catagories,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CtagoryViewHolder ctagoryViewHolder, int i) {
        Catagory catagory=catagories.get(i);
        ctagoryViewHolder.binding.levels.setText(catagory.getName());
        ctagoryViewHolder.binding.images.setImageResource(catagory.getCimage());
    }

    @Override
    public int getItemCount() {
        return catagories.size();
    }

    public class CtagoryViewHolder extends RecyclerView.ViewHolder{
        ItemCatagoriesBinding binding;
        public CtagoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemCatagoriesBinding.bind(itemView);
        }
    }
}
