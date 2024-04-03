package com.ddit.project.supermarketcheckouter.Models;

import com.ddit.project.supermarketcheckouter.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Order_GetSet {

    private String order_id;
    private String payment_refid;
    private String user_id;
    private String user_name;
    private String productlist;
    private String total_amount;
    private String ondate;
    private String payment_status;
    private String admin_approve;

    public Order_GetSet() {
    }

    public Order_GetSet(String order_id, String payment_refid, String user_id, String user_name,
                        String productlist, String total_amount, String ondate,
                        String payment_status, String admin_approve) {
        this.order_id = order_id;
        this.payment_refid = payment_refid;
        this.user_id = user_id;
        this.user_name = user_name;
        this.productlist = productlist;
        this.total_amount = total_amount;
        this.ondate = ondate;
        this.payment_status = payment_status;
        this.admin_approve = admin_approve;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPayment_refid() {
        return payment_refid;
    }

    public void setPayment_refid(String payment_refid) {
        this.payment_refid = payment_refid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProductlist() {
        return productlist;
    }

    public void setProductlist(String productlist) {
        this.productlist = productlist;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getOndate() {
        return ondate;
    }

    public void setOndate(String ondate) {
        this.ondate = ondate;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getAdmin_approve() {
        return admin_approve;
    }

    public void setAdmin_approve(String admin_approve) {
        this.admin_approve = admin_approve;
    }

    // Method to retrieve product names from JSON product list
    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        try {
            Gson gson = new Gson();
            Product[] products = gson.fromJson(productlist, Product[].class);
            for (Product product : products) {
                productNames.add(product.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productNames;
    }

    // Method to retrieve product quantity from JSON product list
    public String getProductQuantity() {
        try {
            Gson gson = new Gson();
            Product[] products = gson.fromJson(productlist, Product[].class);
            if (products != null && products.length > 0) {
                return products[0].getProduct_items(); // Assuming there's only one product in the list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}