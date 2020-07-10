package com.gestor.jonny.red.SharedActivities.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gestor.jonny.red.R;
import com.gestor.jonny.red.SharedActivities.Models.ListSelectorModel;

import java.util.ArrayList;

public class ItemSelectorAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ListSelectorModel> options;

    public ItemSelectorAdapter(Context context, ArrayList<ListSelectorModel> options) {
        this.context = context;
        this.options = options;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int i) {
        return options.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = (LayoutInflater.from(context)).inflate(R.layout.list_selector_item, null);
        TextView itemText = view.findViewById(R.id.text);
        itemText.setText(options.get(i).getValue());
        return view;
    }
}
