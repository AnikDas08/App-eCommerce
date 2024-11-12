package com.example.ecommerceandroid.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceandroid.R;
import com.example.ecommerceandroid.model.OrderShow;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.myOrderView> {

    Context context;
    ArrayList<OrderShow> orders;

    public OrderAdapter(Context context, ArrayList<OrderShow> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public myOrderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new myOrderView(LayoutInflater.from(context).inflate(R.layout.order,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myOrderView myOrderView, int i) {
        myOrderView.name.setText(orders.get(i).getName());
        myOrderView.price.setText(orders.get(i).getContact());
        myOrderView.quantity.setText(orders.get(i).getQuantity());
        myOrderView.details.setText(orders.get(i).getCourse());
        Glide.with(myOrderView.img.getContext())
                .load(orders.get(i).getImage())
                .into(myOrderView.img);

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class myOrderView extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,price,quantity,details;
        @SuppressLint("WrongViewCast")
        public myOrderView(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.ImageViewId);
            name=itemView.findViewById(R.id.NameText);
            price=itemView.findViewById(R.id.PriceText);
            quantity=itemView.findViewById(R.id.QuantityText);
            details=itemView.findViewById(R.id.DescriptionId);
        }
    }
}
