package afpa.dl101_filrouge_android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class GestBDD extends SQLiteOpenHelper {
    private static final String TABLE_EVENEMENT = "evenement";

    private static final String COL_ID = "id_event";
    private static final String COL_TITRE = "titre";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_DEBUT = "date_debut";
    private static final String COL_FIN = "date_fin";
    private static final String COL_LOCATION = "location";

    private static final String CREATE_TABLE_EVENEMENT = "CREATE TABLE " + TABLE_EVENEMENT + " (" +
            COL_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " +
            COL_TITRE + " TEXT NOT NULL , " +
            COL_DESCRIPTION + " TEXT, " +
            COL_DEBUT + " NUMERIC NOT NULL, " +
            COL_FIN + " NUMERIC NOT NULL, " +
            COL_LOCATION + " TEXT NOT NULL );";

    private static final String DROP_TABLE_EVENEMENT = "DROP TABLE IF EXISTS " + TABLE_EVENEMENT + ";";

    private static final String TABLE_METEO = "meteo";

    // COL_LOCATION FOREIGN KEY de TABLE_EVENEMENT
    // private static final String COL_LOCATION = "location";
    private static final String COL_METEO_DESCRIPTION = "description";
    private static final String COL_ICONE = "icone";
    private static final String COL_TEMPERATURE = "temperature";
    private static final String COL_PRESSION = "pression";
    private static final String COL_HUMIDITE = "humidite";
    private static final String COL_VENT = "vent";
    private static final String COL_NUAGE = "nuage";
    private static final String COL_UPDATE = "update";
    private static final String COL_DATE = "date";

    private static final String CREATE_TABLE_METEO = "CREATE TABLE " + TABLE_METEO + " (" +
            COL_LOCATION + " TEXT PRIMARY KEY NOT NULL, " +
            COL_METEO_DESCRIPTION + " TEXT NOT NULL, " +
            COL_ICONE + " TEXT NOT NULL, " +
            COL_TEMPERATURE + " NUMERIC NOT NULL, " +
            COL_PRESSION + " NUMERIC NOT NULL, " +
            COL_HUMIDITE + " NUMERIC NOT NULL, " +
            COL_VENT + " NUMERIC NOT NULL, " +
            COL_NUAGE + " NUMERIC NOT NULL, " +
            COL_UPDATE + " NUMERIC NOT NULL, " +
            COL_DATE + " NUMERIC PRIMARY KEY NOT NULL );";

    private static final String DROP_TABLE_METEO = "DROP TABLE IF EXISTS " + TABLE_METEO + ";";


    // Constructeur
    protected GestBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO Enlever le droptable sur la prod
        db.execSQL(DROP_TABLE_EVENEMENT);
        db.execSQL(DROP_TABLE_METEO);
        Log.i("BASE", "onCreate() Création BDD");
        db.execSQL(CREATE_TABLE_EVENEMENT);
        db.execSQL(CREATE_TABLE_METEO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_EVENEMENT);
        db.execSQL(DROP_TABLE_METEO);
        onCreate(db);
    }
}
