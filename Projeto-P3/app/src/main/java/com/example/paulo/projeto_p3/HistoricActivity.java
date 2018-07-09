package com.example.paulo.projeto_p3;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.paulo.projeto_p3.db.SQLitePiecesHelper;

public class HistoricActivity extends AppCompatActivity {

    private RecyclerView pendingPiecesRV;

    public RecyclerAdapter recyclerAdapter;

    private SQLitePiecesHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);


        db = SQLitePiecesHelper.getInstance(this);

        //Aplicando funcionalidades na RecyclerView
        pendingPiecesRV = findViewById(R.id.pieces_historic_rv);
        pendingPiecesRV.setLayoutManager(new LinearLayoutManager(this));
        pendingPiecesRV.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        pendingPiecesRV.setItemAnimator(new DefaultItemAnimator());
        pendingPiecesRV.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new CarregarHistorico().execute();
    }

    class CarregarHistorico extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            Cursor c = db.getHistoric();
            c.getCount();
            return c;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            if (cursor != null) {
                pendingPiecesRV.setAdapter(new RecyclerAdapter(getApplicationContext(), cursor));
                pendingPiecesRV.getAdapter().notifyDataSetChanged();
            }
        }
    }
}
