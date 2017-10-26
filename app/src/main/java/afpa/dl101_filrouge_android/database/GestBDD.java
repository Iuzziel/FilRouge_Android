package afpa.dl101_filrouge_android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DL101 on 23/10/2017.
 */

class GestBDD extends SQLiteOpenHelper {
    private static final String TABLE_EVENEMENT = "evenement";

    private static final String COL_ID = "id_event";
    private static final String COL_TITRE = "titre";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_DEBUT = "date_debut";
    private static final String COL_FIN = "date_fin";

    private static final String CREATE_TABLE_EVENEMENT = "CREATE TABLE " + TABLE_EVENEMENT + " (" +
            COL_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " +
            COL_TITRE + " TEXT NOT NULL , " +
            COL_DESCRIPTION + " TEXT, " +
            COL_DEBUT + " NUMERIC NOT NULL, " +
            COL_FIN + " NUMERIC NOT NULL );";

    private static final String DROP_TABLE_EVENEMENT = "DROP TABLE IF EXISTS " + TABLE_EVENEMENT + ";";


    // Constructeur
    protected GestBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO Enlever le droptable sur la prod
        db.execSQL(DROP_TABLE_EVENEMENT);
        Log.i("BASE", "onCreate() Création BDD");
        db.execSQL(CREATE_TABLE_EVENEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_EVENEMENT);
        onCreate(db);
    }
}
