package com.ddit.project.supermarketcheckouter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ddit.project.supermarketcheckouter.Product;
import com.google.gson.Gson;

public class AdminProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_details);

        // Get the product list JSON from the intent
        String productListJson = getIntent().getStringExtra("productList");

        // Parse the JSON and display product details
        displayProductDetails(productListJson);
        ImageView backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Navigate back to the previous activity
            }
        });
    }

    private void displayProductDetails(String productListJson) {
        try {
            Gson gson = new Gson();
            Product[] products = gson.fromJson(productListJson, Product[].class);

            LinearLayout productDetailsLayout = findViewById(R.id.recycle_ll);

            if (products != null) {
                for (Product product : products) {
                    // Create a LinearLayout to hold the product name and quantity
                    LinearLayout productLayout = new LinearLayout(this);
                    productLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    productLayout.setOrientation(LinearLayout.HORIZONTAL);

                    // Create a TextView for the product name
                    TextView nameTextView = new TextView(this);
                    nameTextView.setLayoutParams(new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            0.2f
                    ));
                    nameTextView.setText(product.getName());
                    nameTextView.setTextColor(getResources().getColor(R.color.purple_200));
                    nameTextView.setTextSize(18);

                    // Create a TextView for the quantity
                    TextView quantityTextView = new TextView(this);
                    LinearLayout.LayoutParams quantityParams = new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            0.2f
                    );
                    quantityParams.setMargins(20, 0, 0, 0);
                    quantityTextView.setLayoutParams(quantityParams);
                    quantityTextView.setText(product.getProduct_items());
                    quantityTextView.setTextColor(getResources().getColor(R.color.purple_200));
                    quantityTextView.setTextSize(18);
                    // Add the TextViews to the productLayout
                    productLayout.addView(nameTextView);
                    productLayout.addView(quantityTextView);

                    // Add the productLayout to the productDetailsLayout
                    productDetailsLayout.addView(productLayout);
                }
            }else {
                // If no products, display a message
                TextView textView = new TextView(this);
                textView.setText("No product details available.");
                productDetailsLayout.addView(textView);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // If an error occurs, display an error message
            TextView textView = new TextView(this);
            textView.setText("Error loading product details.");
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            textView.setLayoutParams(layoutParams);
            RelativeLayout productDetailsLayout = findViewById(R.id.productDetailsLayout);
            productDetailsLayout.addView(textView);
        }
    }
}
