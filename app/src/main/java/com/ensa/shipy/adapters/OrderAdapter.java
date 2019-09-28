package com.ensa.shipy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ensa.shipy.R;
import com.ensa.shipy.models.OrderModel;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    ArrayList<OrderModel> orders;

    public OrderAdapter(Context context, ArrayList<OrderModel> orders){
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderModel order = orders.get(position);

        holder.orderID.setText(order.getOrderId() + "");
        holder.productId.setText(order.getProductId() + "");
        holder.productName.setText(order.getProductTitle());
        holder.customerName.setText(order.getCustomerName());
        holder.customerAddress.setText(order.getDeliveryAddress());
    }

    @Override
    public int getItemCount() {
        return orders!=null?orders.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderID, productId, productName, customerName, customerAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID =  (TextView) itemView.findViewById(R.id.orderID);
            productId = (TextView) itemView.findViewById(R.id.productId);
            productName = (TextView) itemView.findViewById(R.id.productName);
            customerName = (TextView) itemView.findViewById(R.id.customerName);
            customerAddress = (TextView) itemView.findViewById(R.id.customerAddress);
        }
    }
}
