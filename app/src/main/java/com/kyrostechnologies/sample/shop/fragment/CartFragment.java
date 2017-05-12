package com.kyrostechnologies.sample.shop.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyrostechnologies.sample.shop.R;
import com.kyrostechnologies.sample.shop.adapter.CartListAdapter;
import com.kyrostechnologies.sample.shop.data.GlobalVariable;
import com.kyrostechnologies.sample.shop.model.ItemModel;
import com.kyrostechnologies.sample.shop.widget.DividerItemDecoration;

public class CartFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private GlobalVariable global;
    private CartListAdapter mAdapter;
    private TextView item_total, price_total;
    private LinearLayout lyt_notfound;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, null);
        global = (GlobalVariable) getActivity().getApplication();

        item_total = (TextView) view.findViewById(R.id.item_total);
        price_total = (TextView) view.findViewById(R.id.price_total);
        lyt_notfound = (LinearLayout) view.findViewById(R.id.lyt_notfound);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //set data and list adapter
        mAdapter = new CartListAdapter(getActivity(), global.getCart());
        recyclerView.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new CartListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ItemModel obj) {
                dialogCartAction(obj, position);
            }
        });

        ((Button) view.findViewById(R.id.bt_checkout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAdapter.getItemCount() != 0) {
                    checkoutConfirmation();
                }
            }
        });

        setTotalPrice();

        if (mAdapter.getItemCount() == 0) {
            lyt_notfound.setVisibility(View.VISIBLE);
        } else {
            lyt_notfound.setVisibility(View.GONE);
        }
        return view;
    }

    private void setTotalPrice() {
        item_total.setText(" - " + global.getCartItemTotal() + " Items");
        price_total.setText(" $ " + global.getCartPriceTotal());
    }

    private void dialogCartAction(final ItemModel model, final int position) {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cart_option);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        ((TextView) dialog.findViewById(R.id.title)).setText(model.getName());
        final TextView qty = (TextView) dialog.findViewById(R.id.quantity);
        qty.setText(model.getTotal() + "");
        ((ImageView) dialog.findViewById(R.id.img_decrease)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getTotal() > 1) {
                    model.setTotal(model.getTotal() - 1);
                    qty.setText(model.getTotal() + "");
                }
            }
        });
        ((ImageView) dialog.findViewById(R.id.img_increase)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setTotal(model.getTotal() + 1);
                qty.setText(model.getTotal() + "");
            }
        });
        ((Button) dialog.findViewById(R.id.bt_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                global.updateItemTotal(model);
                mAdapter.notifyDataSetChanged();
                setTotalPrice();
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.bt_remove)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                global.removeCart(model);
                mAdapter.notifyDataSetChanged();
                setTotalPrice();
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void checkoutConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Checkout Confirmation");
        builder.setMessage("Are you sure continue to checkout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                global.clearCart();
                mAdapter.notifyDataSetChanged();
                Snackbar.make(view, "Checkout success", Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

}
