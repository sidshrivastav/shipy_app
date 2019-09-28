package com.ensa.shipy;

import android.app.Activity;
import android.os.Bundle;

import com.ensa.shipy.adapters.HRMAdapter;
import com.ensa.shipy.adapters.OrderAdapter;
import com.ensa.shipy.enums.OrderStatusEnum;
import com.ensa.shipy.models.OrderModel;
import com.ensa.shipy.retrofit.ApiClient;
import com.ensa.shipy.retrofit.ApiInterface;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ensa.shipy.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    ArrayList<OrderModel> orderModels;
    OrderStatusEnum orderStatusEnum;
    OrderAdapter adapter;
    RecyclerView orderList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            orderStatusEnum = OrderStatusEnum.fromCode(getArguments().getInt(ARG_ITEM_ID));


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }

        orderModels = new ArrayList<>();

//        orderModels.add(new OrderModel());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        orderList = (RecyclerView) rootView.findViewById(R.id.orderList);

        // Show the dummy content as text in a TextView.
        if (orderStatusEnum != null) {
            switch (orderStatusEnum){
                case NewOrder:
                    getOrder("NewOrder");
                    break;
                case DeliveryOnProgress:
                    getOrder("DeliveryOnProgress");
                    break;
                case RedirectedOrders:
                    getOrder("RedirectedOrders");
                    break;
                case OrdersReturned:
                    getOrder("OrdersReturned");
                    break;
                case CancelledOrder:
                    getOrder("CancelledOrder");
                    break;
            }
        }

        return rootView;
    }

    private void getOrder(String status) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<OrderModel>> call = apiService.getOrders(status);
        call.enqueue(new Callback<List<OrderModel>>() {
            @Override
            public void onResponse(Call<List<OrderModel>> call, Response<List<OrderModel>> response) {
                Log.d("","");
                orderModels = (ArrayList<OrderModel>) response.body();
                adapter = new OrderAdapter(getContext(), orderModels);
                orderList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OrderModel>> call, Throwable t) {
                Log.d("","");

            }
        });
    }
}
