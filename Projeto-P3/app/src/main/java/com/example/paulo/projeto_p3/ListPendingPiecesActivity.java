package com.example.paulo.projeto_p3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ListPendingPiecesActivity extends AppCompatActivity {

    private RecyclerView pendingPiecesRV;

    private List<itemList> mockData = new ArrayList<itemList>();

    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pending_pieces);

        mockData.add(new itemList("Embreagem", "Embreagem para carro 103", 1));
        mockData.add(new itemList("Bomba d'água", "Para carro 101", 3));
        mockData.add((new itemList("Caixa de direção", "Substituir defeito no carro 403", 1)));

        //Aplicando funcionalidades na RecyclerView
        pendingPiecesRV = findViewById(R.id.pending_pieces_rv);
        pendingPiecesRV.setLayoutManager(new LinearLayoutManager(this));
        pendingPiecesRV.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        pendingPiecesRV.setItemAnimator(new DefaultItemAnimator());
        pendingPiecesRV.setAdapter(new RecyclerAdapter(getApplicationContext(), mockData));
    }
}
