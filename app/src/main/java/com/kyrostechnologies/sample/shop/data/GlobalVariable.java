package com.kyrostechnologies.sample.shop.data;

import android.app.Application;

import com.kyrostechnologies.sample.shop.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariable extends Application {
    private List<ItemModel> cart = new ArrayList<>();

    public void addCart(ItemModel model) {
        cart.add(model);
    }
    public void removeCart(ItemModel model) {
        for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getId()==model.getId()){
                cart.remove(i);
                break;
            }
        }
    }
    public void clearCart() {
        cart.clear();
    }
    public List<ItemModel> getCart() {
        return cart;
    }
    public long getCartPriceTotal() {
        long total = 0;
        for (int i = 0; i < cart.size(); i++) {
            total = total + cart.get(i).getSumPrice();
        }
        return total;
    }
    public int getCartItemTotal() {
        int total = 0;
        for (int i = 0; i < cart.size(); i++) {
            total = total + cart.get(i).getTotal();
        }
        return total;
    }
    public int getCartItem() {
        return cart.size();
    }
    public void updateItemTotal(ItemModel model) {
        for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getId()==model.getId()){
                cart.remove(i);
                cart.add(i, model);
                break;
            }
        }
    }

    public boolean isCartExist(ItemModel model){
        for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getId()==model.getId()){
                return true;
            }
        }
        return false;
    }
}
