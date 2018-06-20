package com.example.paulo.projeto_p3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.paulo.projeto_p3.db.IndexReaderContract;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.itemListHolder> {


    private Context c;
    private Cursor cursor;

    public RecyclerAdapter(Context c, Cursor cursor){
        this.c = c;
        this.cursor = cursor;
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
        cursor.moveToPosition(position);
        holder.itemNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(IndexReaderContract.NAME)));
        holder.itemDesc.setText(cursor.getString(cursor.getColumnIndexOrThrow(IndexReaderContract.DESCRIPTION)));
        holder.itemQuantity.setText(String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(IndexReaderContract.QUANTITY))));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    class itemListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemNome;
        TextView itemDesc;
        TextView itemQuantity;

        public itemListHolder(View itemView) {
            super(itemView);
            this.itemNome = itemView.findViewById(R.id.itemNome);
            this.itemDesc = itemView.findViewById(R.id.itemDesc);
            this.itemQuantity = itemView.findViewById(R.id.itemQuantity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cursor.moveToPosition(getAdapterPosition());
            Intent showPieceIntent = new Intent(c, ShowPieceActivity.class);
            showPieceIntent.putExtra("itemNome", cursor.getString(cursor.getColumnIndexOrThrow(IndexReaderContract.NAME)));
            showPieceIntent.putExtra("itemDesc", cursor.getString(cursor.getColumnIndexOrThrow(IndexReaderContract.DESCRIPTION)));
            showPieceIntent.putExtra("itemQuantity", String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(IndexReaderContract.QUANTITY))));
            showPieceIntent.putExtra("status", String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(IndexReaderContract.STATUS))));
            showPieceIntent.putExtra("createdBy", String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(IndexReaderContract.CREATED_BY))));
            showPieceIntent.putExtra("id", cursor.getString(cursor.getColumnIndexOrThrow(IndexReaderContract.ID)));
            c.startActivity(showPieceIntent);
        }
    }
}
