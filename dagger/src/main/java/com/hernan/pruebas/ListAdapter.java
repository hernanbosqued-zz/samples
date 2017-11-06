package com.hernan.pruebas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;
class ListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private String[] items;

    @Inject
    ListAdapter() {
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(items[position]);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.length : 0;
    }

    public void setItems(String[] items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
