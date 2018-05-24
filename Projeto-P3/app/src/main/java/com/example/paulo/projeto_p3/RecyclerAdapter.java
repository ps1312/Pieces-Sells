package com.example.paulo.projeto_p3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.itemListHolder> {


    private Context c;
    private List<itemList> mockData;

    public RecyclerAdapter(Context c, List<itemList> mockData){
        this.c = c;
        this.mockData = mockData;
    }

    @NonNull
    @Override
    public itemListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_list, parent, false);
        itemListHolder holder = new itemListHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull itemListHolder holder, int position) {
        holder.itemNome.setText(mockData.get(position).getItemName());
        holder.itemDesc.setText(mockData.get(position).getDescription());
        holder.itemQuantity.setText(String.valueOf(mockData.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mockData.size();
    }


    class itemListHolder extends RecyclerView.ViewHolder {

        TextView itemNome;
        TextView itemDesc;
        TextView itemQuantity;

        public itemListHolder(View itemView) {
            super(itemView);
            this.itemNome = itemView.findViewById(R.id.itemNome);
            this.itemDesc = itemView.findViewById(R.id.itemDesc);
            this.itemQuantity = itemView.findViewById(R.id.itemQuantity);
        }
    }
}
