package com.example.mobileapp.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    List<Dish> listDishes;
    List<Dish> listOrderedDishes;

    public MenuAdapter(Context context, List<Dish> listDishes) {
        this.context = context;
        this.listDishes = listDishes;
        listOrderedDishes = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return listDishes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

        ImageView image = view.findViewById(R.id.image);
        TextView nameDish = view.findViewById(R.id.nameDish);
        TextView descriptionDish = view.findViewById(R.id.descriptionDish);
        TextView costDish = view.findViewById(R.id.costDish);
        TextView currency = view.findViewById(R.id.currency);
        Button increaseAmount = view.findViewById(R.id.increaseAmount);
        Button decreaseAmount = view.findViewById(R.id.decreaseAmount);
        final TextView amount = view.findViewById(R.id.ammount);
        Button addDish = view.findViewById(R.id.addDish);

        image.setImageResource(listDishes.get(position).getImage());
        nameDish.setText(listDishes.get(position).getNameDish());
        descriptionDish.setText(listDishes.get(position).getDescriptionOfDish());
        costDish.setText(String.valueOf(listDishes.get(position).getCostDish()));
        currency.setText(listDishes.get(position).getCurrency());
        amount.setText(String.valueOf(listDishes.get(position).getAmount()));


        increaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listDishes.get(position).increaseAmount();
                amount.setText(String.valueOf(listDishes.get(position).getAmount()));
            }
        });

        decreaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listDishes.get(position).decreaseAmount();
                amount.setText(String.valueOf(listDishes.get(position).getAmount()));
            }
        });

        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOrderedDishes.add(listDishes.get(position));
                listDishes.get(position).setAmount(0);
                amount.setText(String.valueOf(listDishes.get(position).getAmount()));

                Toast.makeText(context, position + 1 +" position "+ " has been added to order", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Position "+position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}