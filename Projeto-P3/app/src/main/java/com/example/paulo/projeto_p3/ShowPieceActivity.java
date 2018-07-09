package com.example.paulo.projeto_p3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.paulo.projeto_p3.db.SQLitePiecesHelper;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ShowPieceActivity extends AppCompatActivity {

    private TextView itemNameTv;
    private TextView itemDescTv;
    private TextView itemQuantityTv;
    private TextView itemStatusTv;
    private TextView itemCreatedByTv;
    private Button buyButton;

    private SQLitePiecesHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_piece);

        db = SQLitePiecesHelper.getInstance(getApplicationContext());

        Intent intent = getIntent();
        String itemNome = intent.getStringExtra("itemNome");
        String itemDesc = intent.getStringExtra("itemDesc");
        String itemQuantity = intent.getStringExtra("itemQuantity");
        String createdBy = intent.getStringExtra("createdBy");
        String status = intent.getStringExtra("status");
        final String id = intent.getStringExtra("id");

        itemNameTv = findViewById(R.id.piece_name_tv);
        itemDescTv = findViewById(R.id.piece_desc_tv);
        itemQuantityTv = findViewById(R.id.piece_quantity_tv);
        itemStatusTv = findViewById(R.id.piece_status_tv);
        itemCreatedByTv = findViewById(R.id.piece_createdBy_tv);
        buyButton = findViewById(R.id.status_button);

        itemNameTv.setText(itemNome);
        itemDescTv.setText(itemDesc);
        itemQuantityTv.setText(itemQuantity);
        itemCreatedByTv.setText(createdBy);

        //Item ainda não foi adquirido
        if (status.equals("0")) {
            itemStatusTv.setText("Em falta");
            buyButton.setText("Adquirir");
        } else {
            itemStatusTv.setText("Comprado");
            buyButton.setEnabled(false);
        }

        //Somente usuario com role de "admin" pode adquirir as peçasLoginActivity
        if (ParseUser.getCurrentUser().getUsername().equals("admin")) {
            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemStatusTv.setText("Comprado");
                    buyButton.setText("Adquirido");
                    buyButton.setEnabled(false);
                    db.updateItem(id, 1);
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Produto");
                    // encontrar pelo id e marcar status como 1 (comprado)
                    query.getInBackground(id, new GetCallback<ParseObject>() {
                        public void done(ParseObject gameScore, ParseException e) {
                            if (e == null) {
                                gameScore.put("status", 1);
                                gameScore.saveInBackground();
                            } else {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        } else {
            buyButton.setText("sem permissão");
            buyButton.setEnabled(false);
        }
    }
}
