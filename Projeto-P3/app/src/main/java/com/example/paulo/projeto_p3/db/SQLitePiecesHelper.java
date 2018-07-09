package com.example.paulo.projeto_p3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.paulo.projeto_p3.ItemList;

public class SQLitePiecesHelper extends SQLiteOpenHelper {

    //Campos inicializadores da instancia do banco
    private static final String DATABASE_NAME = "pieces";

    public static final String DATABASE_TABLE = "products";

    private static final int DB_VERSION = 1;

    public SQLitePiecesHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    private static SQLitePiecesHelper db;
    public static SQLitePiecesHelper getInstance(Context c) {
        if (db==null) {
            db = new SQLitePiecesHelper(c.getApplicationContext());
        }
        return db;
    }

    //constantes que representam os campos do bd
    public static final String ITEM_ROWID = IndexReaderContract._ID;
    public static final String ITEM_ID = IndexReaderContract.ID;
    public static final String ITEM_NAME = IndexReaderContract.NAME;
    public static final String ITEM_DESCRIPTION = IndexReaderContract.DESCRIPTION;
    public static final String ITEM_QUANTITY = IndexReaderContract.QUANTITY;
    public static final String ITEM_STATUS = IndexReaderContract.STATUS;
    public static final String ITEM_CREATOR = IndexReaderContract.CREATED_BY;

    //Comando sql usado na criacao do banco
    private static final String CREATE_DB_COMMAND = "CREATE TABLE " + DATABASE_TABLE + " (" +
            ITEM_ROWID +" integer primary key autoincrement, "+
            ITEM_ID +" String not null, "+
            ITEM_NAME + " text not null, " +
            ITEM_DESCRIPTION + " text not null, " +
            ITEM_QUANTITY + " integer not null, " +
            ITEM_CREATOR + " text not null, " +
            ITEM_STATUS + " boolean not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException("Não da upgrade ainda");
    }

    //Array com todas as colunas da tabela
    public final static String[] columns = {ITEM_ROWID, ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, ITEM_QUANTITY, ITEM_CREATOR, ITEM_STATUS};

    //Retornar o item pelo seu id
    public ItemList getItem(String id) throws SQLException {
        SQLiteDatabase readableDB = db.getReadableDatabase();
        Cursor query = readableDB.query(DATABASE_TABLE, columns, ITEM_ID + "=?", new String[]{id}, null, null, null);
        //Forçar consulta
        if (query.getCount() > 0) {
            query.moveToFirst();
            ItemList i = new ItemList(query.getString(query.getColumnIndexOrThrow(ITEM_NAME)),
                    query.getString(query.getColumnIndexOrThrow(ITEM_ID)),
                    query.getString(query.getColumnIndexOrThrow(ITEM_DESCRIPTION)),
                    query.getInt(query.getColumnIndexOrThrow(ITEM_QUANTITY)),
                    query.getInt(query.getColumnIndexOrThrow(ITEM_STATUS)));
            return i;
        }
        return null;
    }

    public Cursor getItems() throws SQLException {
        SQLiteDatabase readableDB = db.getReadableDatabase();
        Cursor query = readableDB.query(DATABASE_TABLE, columns, ITEM_STATUS + " = 0", null, null, null, null);
        return query;
    }

    public Cursor getHistoric() throws SQLException {
        SQLiteDatabase readableDB = db.getReadableDatabase();
        Cursor query = readableDB.query(DATABASE_TABLE, columns, ITEM_STATUS + " = 1", null, null, null, null);
        return query;
    }

    public long insertPiece(ItemList item, String username) {
        return insertPiece(item.getItemName(), item.getId(), item.getDescription(), item.getQuantity(), username, item.getStatus());
    }

    public long insertPiece(String name, String id, String description, Integer quantity, String createdBy, Integer status) {
        //Colocar tudo em um content value para add no banco
        ContentValues item = new ContentValues();
        item.put(ITEM_NAME, name);
        item.put(ITEM_ID, id);
        item.put(ITEM_DESCRIPTION, description);
        item.put(ITEM_QUANTITY, quantity);
        item.put(ITEM_CREATOR, createdBy);
        item.put(ITEM_STATUS, status);

        //Pegar uma instancia writable
        SQLiteDatabase writableDB = db.getWritableDatabase();

        return writableDB.insert(DATABASE_TABLE, null, item);
    }

    public boolean updateItem(String id, int status) {
        SQLiteDatabase writableDB = db.getWritableDatabase();
        ContentValues item = new ContentValues();
        item.put(ITEM_STATUS, status);
        int hasUpdated = writableDB.update(DATABASE_TABLE, item, ITEM_ID + " = ?", new String[]{id});
        if (hasUpdated == 1) {
            return true;
        } else {
            return false;
        }
    }
}
