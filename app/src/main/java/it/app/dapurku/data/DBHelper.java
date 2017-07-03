package it.app.dapurku.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Islam on 10/05/2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    final String CREATE_RESEP_TABLE = "CREATE TABLE "
            + ResepContract.ResepEntry.TABLE_NAME + " ("
            + ResepContract.ResepEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ResepContract.ResepEntry.COL0_RESEP_ID + " INTEGER NOT NULL, "
            + ResepContract.ResepEntry.COL1_RESEP_GAMBAR + " TEXT NOT NULL, "
            + ResepContract.ResepEntry.COL2_RESEP_NAMA + " TEXT NOT NULL, "
            + ResepContract.ResepEntry.COL3_RESEP_BAHAN + " TEXT NOT NULL, "
            + ResepContract.ResepEntry.COL4_RESEP_CARA_MASAK + " TEXT NOT NULL, "
            + ResepContract.ResepEntry.COL5_RESEP_FAVORITE + " INTEGER NOT NULL "
            + ")";

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dapurku.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RESEP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + ResepContract.ResepEntry.TABLE_NAME);
        onCreate(db);
    }
}
