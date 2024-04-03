package com.ddit.project.supermarketcheckouter.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddit.project.supermarketcheckouter.Models.Order_GetSet;
import com.ddit.project.supermarketcheckouter.Product;
import com.ddit.project.supermarketcheckouter.R;
import com.google.gson.Gson;

import java.util.ArrayList;
public class OrderAdminAdapter extends RecyclerView.Adapter<OrderAdminAdapter.ViewHolder> {

    private ArrayList<Order_GetSet> mItems = new ArrayList<>();
    private Context mContext;
    private clickcallback mListener;

    public OrderAdminAdapter(Context context, clickcallback listener) {
        mContext = context;
        mListener = listener;
    }

    public void additem(ArrayList<Order_GetSet> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void removeitem(int pos) {
        if (pos >= 0 && pos < mItems.size()) {
            mItems.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_orderlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView approve_tv;
        TextView ondate_tv;
        TextView ref_id;
        TextView order_status;
        TextView amount;
        TextView order_id;
        TextView username_tv;
        TextView product_name_tv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            approve_tv = itemView.findViewById(R.id.approve_tv);
            ondate_tv = itemView.findViewById(R.id.ondate_tv);
            ref_id = itemView.findViewById(R.id.ref_id);
            order_status = itemView.findViewById(R.id.order_status);
            amount = itemView.findViewById(R.id.amount);
            order_id = itemView.findViewById(R.id.order_id);
            username_tv = itemView.findViewById(R.id.username_tv);
            product_name_tv = itemView.findViewById(R.id.product_name_tv);
            itemView.setOnClickListener(this);
        }

        void setData(Order_GetSet item) {
            ondate_tv.setText(item.getOndate() + "");
            ref_id.setText(item.getPayment_refid() + "");
            amount.setText(item.getTotal_amount() + "");
            order_id.setText(item.getOrder_id() + "");
            username_tv.setText(item.getUser_name() + "");
            order_status.setText(item.getPayment_status() + "");
            if (item.getPayment_status().equals("success")) {
                order_status.setTextColor(mContext.getResources().getColor(R.color.colorSuccess));
            } else {
                order_status.setTextColor(mContext.getResources().getColor(R.color.colorError));
            }
            // Parse product list JSON and extract product names and quantities
            StringBuilder productDetailsBuilder = new StringBuilder();
            try {
                Gson gson = new Gson();
                Product[] products = gson.fromJson(item.getProductlist(), Product[].class);
                for (Product product : products) {
                    if (productDetailsBuilder.length() > 0) {
                        productDetailsBuilder.append(", ");
                    }
                    productDetailsBuilder.append(product.getName()).append(" (Quantity: ").append(product.getProduct_items()).append(")");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Set product names and quantities
            String productDetails = productDetailsBuilder.toString();
            if (!productDetails.isEmpty()) {
                product_name_tv.setText("Products: " + productDetails);
            } else {
                product_name_tv.setText("Product Names Not Available");
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                mListener.clickDetailsOrder(mItems.get(position), position);
            }
        }
    }

    public interface clickcallback {
        void clickDetailsOrder(Order_GetSet item, int pos);
    }
}
