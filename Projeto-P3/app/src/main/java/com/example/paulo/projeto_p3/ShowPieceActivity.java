package com.example.paulo.projeto_p3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowPieceActivity extends AppCompatActivity {

    private TextView itemNameTv;
    private TextView itemDescTv;
    private TextView itemQuantityTv;
    private TextView pieceStatusTv;
    private Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_piece);

        Intent intent = getIntent();
        String itemNome = intent.getStringExtra("itemNome");
        String itemDesc = intent.getStringExtra("itemDesc");
        String itemQuantity = intent.getStringExtra("itemQuantity");

        itemNameTv = findViewById(R.id.piece_name_tv);
        itemDescTv = findViewById(R.id.piece_desc_tv);
        itemQuantityTv = findViewById(R.id.piece_quantity_tv);
        pieceStatusTv = findViewById(R.id.piece_status_tv);
        buyButton = findViewById(R.id.status_button);

        itemNameTv.setText(itemNome);
        itemDescTv.setText(itemDesc);
        itemQuantityTv.setText(itemQuantity);


        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieceStatusTv.setText("Comprado");
                buyButton.setEnabled(false);
            }
        });
    }
}
