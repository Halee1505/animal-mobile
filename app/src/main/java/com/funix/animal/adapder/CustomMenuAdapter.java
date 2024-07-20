package com.funix.animal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.funix.animal.R;

import java.util.List;

public class CustomMenuAdapter extends BaseAdapter {

    private Context context;
    private List<MenuItem> menuItems;
    private int[] images;

    public CustomMenuAdapter(Context context, List<MenuItem> menuItems, int[] images) {
        this.context = context;
        this.menuItems = menuItems;
        this.images = images;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.menu_item_with_image, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.menu_item_image);
        TextView textView = convertView.findViewById(R.id.menu_item_title);

        MenuItem menuItem = menuItems.get(position);

        imageView.setImageResource(images[position]);
        textView.setText(menuItem.getTitle());

        return convertView;
    }
}
