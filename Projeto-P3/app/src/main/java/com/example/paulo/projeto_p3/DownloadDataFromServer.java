package com.example.paulo.projeto_p3;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.paulo.projeto_p3.db.SQLitePiecesHelper;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class DownloadDataFromServer extends IntentService {

    public static final String DOWNLOAD_COMPLETE =  "com.example.paulo.projeto_p3.DOWNLOAD_COMPLETE";

    public static final String NEW_PIECE_AVAILABLE = "com.example.paulo.projeto_p3.NEW_PIECE_AVAILABLE";

    public SQLitePiecesHelper db;

    public boolean newPieces = false;

    public DownloadDataFromServer() {
        super("DownloadDataFromServer");
    }


    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        db = SQLitePiecesHelper.getInstance(getApplicationContext());

        Parse.initialize(getApplicationContext());
        final ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> request = ParseQuery.getQuery("Produto");
        request.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                //Erro no index das peças
                if (e != null) {
                    e.printStackTrace();
                } else {
                    for (ParseObject piece : objects) {
                        ItemList item = db.getItem(piece.getObjectId());

                        //Produto nao existe no banco, partiu adicionar e enviar notificacao se houver peças novas
                        if (item == null) {
                            newPieces = true;
                            item = new ItemList(piece.getString("name"), piece.getObjectId(), piece.getString("description"), piece.getInt("quantity"), piece.getInt("status"));
                            db.insertPiece(item, currentUser.getUsername());
                        } else {

                            //se existe, atualizar status de acordo com o servidor
                            if (!piece.get("status").equals(item.getStatus())) {
                                db.updateItem(item.getId(), piece.getInt("status"));
                            }
                        }
                    }
                }
            }
        });

        if (newPieces){
            sendBroadcast(new Intent(NEW_PIECE_AVAILABLE));
        }

        //Terminou de adicionar os novos itens no banco, enviar broadcast para carregar o index
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(DOWNLOAD_COMPLETE));
    }
}
