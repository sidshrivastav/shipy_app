package com.ensa.shipy.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ensa.shipy.ItemDetailActivity;
import com.ensa.shipy.ItemDetailFragment;
import com.ensa.shipy.ItemListActivity;
import com.ensa.shipy.R;
import com.ensa.shipy.dummy.DummyContent;
import com.ensa.shipy.enums.OrderStatusEnum;

import java.util.ArrayList;
import java.util.List;

import static com.ensa.shipy.ItemDetailFragment.ARG_ITEM_ID;

public class HRMAdapter extends RecyclerView.Adapter<HRMAdapter.ViewHolder> {

    ArrayList<OrderStatusEnum> mValues;
    ItemListActivity mParentActivity;
    boolean mTwoPane;

    public HRMAdapter(ItemListActivity parent, ArrayList<OrderStatusEnum> items, boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);

        if(mTwoPane){
            Bundle arguments = new Bundle();
            arguments.putInt(ARG_ITEM_ID, mValues.get(0).getCode());
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            mParentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        switch (mValues.get(position)){
            case NewOrder:
                holder.mContentView.setText("New Order");
                break;
            case CancelledOrder:
                holder.mContentView.setText("Cancelled Order");
                break;
            case OrdersReturned:
                holder.mContentView.setText("Order Returned");
                break;
            case RedirectedOrders:
                holder.mContentView.setText("Redirected Order");
                break;
            case DeliveryOnProgress:
                holder.mContentView.setText("Delivery En Route");
                break;
        }

        holder.itemView.setTag(mValues.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt(ARG_ITEM_ID, mValues.get(position).getCode());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ARG_ITEM_ID, mValues.get(position).getCode());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mContentView;
        LinearLayout root;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContentView = (TextView) itemView.findViewById(R.id.content);
            root = (LinearLayout) itemView.findViewById(R.id.root);
        }
    }
}
