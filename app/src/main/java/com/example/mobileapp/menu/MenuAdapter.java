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
    private List<Dish> listDishes;
    private View.OnClickListener onAddDishButtonListener;

    public MenuAdapter(Context context, List<Dish> listDishes) {
        this.context = context;
        this.listDishes = listDishes;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView nameDishTextView;
        TextView descriptionDishTextView;
        TextView costDishTextView;
        TextView currencyTextView;
        Button increaseAmountButton;
        Button decreaseAmountButton;
        TextView amountTextView;
        Button addDishButton;
        Dish dish;
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
        final ViewHolder viewHolder;
        View listView = view;

        if(listView == null) {
            listView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
            viewHolder = new ViewHolder();

            viewHolder.imageView = listView.findViewById(R.id.image);
            viewHolder.nameDishTextView = listView.findViewById(R.id.nameDish);
            viewHolder.descriptionDishTextView = listView.findViewById(R.id.descriptionDish);
            viewHolder.costDishTextView = listView.findViewById(R.id.costDish);
            viewHolder.currencyTextView = listView.findViewById(R.id.currency);
            viewHolder.increaseAmountButton = listView.findViewById(R.id.increaseAmount);
            viewHolder.decreaseAmountButton = listView.findViewById(R.id.decreaseAmount);
            viewHolder.amountTextView = listView.findViewById(R.id.ammount);
            viewHolder.addDishButton = listView.findViewById(R.id.addDish);



            viewHolder.addDishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onAddDishButtonListener != null ) onAddDishButtonListener.onClick(view);
                }
            });

            listView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) listView.getTag();
        }

        viewHolder.imageView.setImageResource(listDishes.get(position).getImage());
        viewHolder.nameDishTextView.setText(listDishes.get(position).getNameDish());
        viewHolder.descriptionDishTextView.setText(listDishes.get(position).getDescriptionOfDish());
        viewHolder.costDishTextView.setText(String.valueOf(listDishes.get(position).getCostDish()));
        viewHolder.currencyTextView.setText(listDishes.get(position).getCurrency());
        viewHolder.amountTextView.setText(String.valueOf(listDishes.get(position).getAmount()));


        viewHolder.increaseAmountButton.setTag(new Integer(position));
        viewHolder.decreaseAmountButton.setTag(new Integer(position));

        viewHolder.dish = new Dish(
                viewHolder.nameDishTextView,
                viewHolder.costDishTextView,
                viewHolder.currencyTextView,
                viewHolder.amountTextView
        );

        //TODO przekazac Dish do MenuActivity gdzie bedzie dodawany do listy i przekazywany do kolejnej aktywności
        viewHolder.addDishButton.setTag(viewHolder.dish);

         viewHolder.increaseAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listDishes.get(position).increaseAmount();
                viewHolder.amountTextView.setText(String.valueOf(listDishes.get(position).getAmount()));

            }
        });

        viewHolder.decreaseAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listDishes.get(position).decreaseAmount();
                viewHolder.amountTextView.setText(String.valueOf(listDishes.get(position).getAmount()));
            }
        });

/*----------------------------------------------------------------------------------------------------------------
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Position "+position, Toast.LENGTH_SHORT).show();
            }
        });

        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOrderedDishes.add(listDishes.get(position));


               // Toast.makeText(context, position + 1 +" position "+ " has been added to order" +" ammount :"+ listDishes.get(position).getAmount(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, listDishes.get(position).toString(), Toast.LENGTH_SHORT).show();


                //ustawiam na 0 i wyswietlam
                listDishes.get(position).setAmount(0);
                amountTextView.setText(String.valueOf(listDishes.get(position).getAmount()));
            }
        });

         */


        return listView;
    }

    public void setOnAddDishButtonListener(View.OnClickListener onAddDishButtonListener) {

        this.onAddDishButtonListener = onAddDishButtonListener;
    }
}