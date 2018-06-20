package com.example.paulo.projeto_p3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.paulo.projeto_p3.db.SQLitePiecesHelper;
import com.parse.ParseUser;


public class ListPendingPiecesActivity extends AppCompatActivity {

    private RecyclerView pendingPiecesRV;

    public RecyclerAdapter recyclerAdapter;

    private SQLitePiecesHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pending_pieces);

        db = SQLitePiecesHelper.getInstance(this);

        //Aplicando funcionalidades na RecyclerView
        pendingPiecesRV = findViewById(R.id.pending_pieces_rv);
        pendingPiecesRV.setLayoutManager(new LinearLayoutManager(this));
        pendingPiecesRV.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        pendingPiecesRV.setItemAnimator(new DefaultItemAnimator());
        pendingPiecesRV.setAdapter(recyclerAdapter);

        ParseUser currentUser = ParseUser.getCurrentUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Criar intent para iniciar o servico de download do feed
        Intent downloadService = new Intent(getApplicationContext(), DownloadDataFromServer.class);
        startService(downloadService);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Registrar o broadcastreceiver dinamico quando o usuario estiver com o app em primeiro plano
        IntentFilter intentFilter = new IntentFilter(DownloadDataFromServer.DOWNLOAD_COMPLETE);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(onDownloadCompleteEvent, intentFilter);
    }

    //Evento para quando o broadcast for recebido
    private BroadcastReceiver onDownloadCompleteEvent = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(), "Pecas carregadas, exibindo a lista.", Toast.LENGTH_LONG).show();
            new CarregarListaPecas().execute();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_add:
                startActivity(new Intent(getApplicationContext(), AddItemActivity.class));
                return true;

            case R.id.btn_config:
                startActivity(new Intent(getApplicationContext(), ConfigActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class CarregarListaPecas extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            Cursor c = db.getItems();
            c.getCount();
            return c;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            if (cursor != null) {
                pendingPiecesRV.setAdapter(new RecyclerAdapter(getApplicationContext(), cursor));
            }
        }
    }
}
