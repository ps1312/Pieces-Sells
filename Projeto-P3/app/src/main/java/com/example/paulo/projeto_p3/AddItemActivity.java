package com.example.paulo.projeto_p3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    private EditText itemNomeEt;
    private EditText itemDescEt;
    private EditText itemQuantityEt;
    private Button addPieceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemNomeEt = findViewById(R.id.piece_name_et);
        itemDescEt = findViewById(R.id.piece_desc_et);
        itemQuantityEt = findViewById(R.id.piece_quantity_et);
        addPieceButton = findViewById(R.id.add_piece_button);

        addPieceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemNome = String.valueOf(itemNomeEt.getText());
                String itemDesc = String.valueOf(itemDescEt.getText());
                String itemQuantity = String.valueOf(itemQuantityEt.getText());

                Intent newItemPieceData = new Intent();

                if (itemNome.equals("") || itemDesc.equals("")) {
                    setResult(RESULT_CANCELED, newItemPieceData);
                } else {
                    newItemPieceData.putExtra("itemNome", itemNome);
                    newItemPieceData.putExtra("itemDesc", itemDesc);
                    newItemPieceData.putExtra("itemQuantity", itemQuantity);
                    setResult(RESULT_OK, newItemPieceData);
                }
                finish();
            }
        });
    }
}
