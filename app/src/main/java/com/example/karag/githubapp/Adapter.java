package com.example.karag.githubapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    // Adapter
    private List<Item> items;
    private static final int ITEM_COUNT = 50;
    private static final int TYPE_INACTIVE = 0;
    private static final int TYPE_ACTIVE = 1;

    private static final String Tag = Adapter.class.getSimpleName();

    public Adapter() {
        super();

        items = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < ITEM_COUNT; i++) {
            items.add(new Item("Item" + i, "This is the item number" + i, random.nextBoolean()));
        }
    }

    // Adapter_close
//Methods
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final int layout = viewType == TYPE_INACTIVE ? R.layout.item : R.layout.item_active;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = items.get(position);

        holder.title.setText(item.getTitle());
        holder.subtitle.setText(item.getSubtitle() + ", which is " + (item.isActive() ? "active" : "inactive"));

        // Span the item if active
        final ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;
            sglp.setFullSpan(item.isActive());
            holder.itemView.setLayoutParams(sglp);
        }
    }

    @Override
    public int getItemViewType(int position) {
        final Item item = items.get(position);

        return item.isActive() ? TYPE_ACTIVE : TYPE_INACTIVE;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

//Close Methods


//View Holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }

}
