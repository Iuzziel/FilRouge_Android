package afpa.dl101_filrouge_android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Vector;

import afpa.dl101_filrouge_android.objet.Meteo;

public class MeteoManager {
    private static final String TABLE_METEO = "meteo";

    private static final String COL_LOCATION = "location";
    private static final int NUM_COL_LOCATION = 0;
    private static final String COL_DESCRIPTION = "description";
    private static final int NUM_COL_DESCRIPTION = 1;
    private static final String COL_ICONE = "icone";
    private static final int NUM_COL_ICONE = 2;
    private static final String COL_TEMPERATURE = "temperature";
    private static final int NUM_COL_TEMPERATURE = 3;
    private static final String COL_PRESSION = "pression";
    private static final int NUM_COL_PRESSION = 4;
    private static final String COL_HUMIDITE = "humidite";
    private static final int NUM_COL_HUMIDITE = 5;
    private static final String COL_VENT = "vent";
    private static final int NUM_COL_VENT = 6;
    private static final String COL_NUAGE = "nuage";
    private static final int NUM_COL_NUAGE = 7;
    private static final String COL_LASTUPDATE = "lastUpdate";
    private static final int NUM_COL_LASTUPDATE = 8;
    private static final String COL_DATE = "dateConcerne";
    private static final int NUM_COL_DATE = 9;

    private static final String NOM_BDD = "calendrierMeteoBDD";
    private static final int VERSION = 1;

    private SQLiteDatabase bdd;
    private GestBDD gestBDD;

    public MeteoManager(Context context) {
        gestBDD = new GestBDD(context, NOM_BDD, null, VERSION);
    }

    public void open() {
        // Si la BDD existe, on ouvre la BDD en ecriture,
        // sinon le systeme appelle la methode onCreate()
        bdd = gestBDD.getWritableDatabase();
    }

    public void close() {
        bdd.close();
    }

    /**
     * Recupere tout la Meteo a une dateLong donnee.
     *
     * @param date date en int, format YYYYMMDD
     * @return Un vecteur comprenant la meteo a cette date,
     * chercher ensuite la location voulue dedans
     */
    public Vector<Meteo> getMeteoJour(int date) {
        open();

        Cursor c = bdd.query(TABLE_METEO,
                new String[]{COL_LOCATION, COL_DESCRIPTION, COL_ICONE, COL_TEMPERATURE,
                        COL_PRESSION, COL_HUMIDITE, COL_VENT, COL_NUAGE, COL_LASTUPDATE, COL_DATE},
                //date + " BETWEEN " + COL_DEBUT + " AND " + COL_FIN,
                COL_DATE + " = " + date,
                null, null, null, null);

        //Cursor c = bdd.rawQuery("SELECT * FROM " + TABLE_METEO, null);

        if (c.getCount() != 0) {
            return cursorToVectorEv(c);
        } else {
            return null;
        }
    }

    /**
     * Transforme un curseur de Meteo en vecteur d'objet Meteo
     *
     * @param c Transforme un curseur en vecteur d'objet Meteo
     * @return vecteur d'objet Meteo
     */
    private Vector<Meteo> cursorToVectorEv(Cursor c) {
        if (c.getCount() == 0)
            return null;

        Vector<Meteo> vMeteo = new Vector<Meteo>();

        c.moveToFirst();
        do {
            vMeteo.add(new Meteo(c.getString(NUM_COL_LOCATION),
                    c.getString(NUM_COL_DESCRIPTION),
                    c.getString(NUM_COL_ICONE),
                    c.getDouble(NUM_COL_TEMPERATURE),
                    c.getDouble(NUM_COL_PRESSION),
                    c.getDouble(NUM_COL_HUMIDITE),
                    c.getDouble(NUM_COL_VENT),
                    c.getDouble(NUM_COL_NUAGE),
                    c.getString(NUM_COL_LASTUPDATE),
                    c.getString(NUM_COL_DATE)));
        } while (c.moveToNext());
        c.close();

        return vMeteo;
    }

    /**
     * Insert un objet meteo dans la base.
     *
     * @param meteo Seul l'id n'est pas requis, car auto-increment.
     * @return long, id qui vient d'etre cree.
     */
    public long insertMeteo(Meteo meteo) {
        open();
        ContentValues valMeteo = new ContentValues();
        valMeteo.put(COL_LOCATION, meteo.getLocation());
        valMeteo.put(COL_DESCRIPTION, meteo.getDescription());
        valMeteo.put(COL_ICONE, meteo.getIcone());
        valMeteo.put(COL_TEMPERATURE, meteo.getTemperature());
        valMeteo.put(COL_PRESSION, meteo.getPressure());
        valMeteo.put(COL_HUMIDITE, meteo.getHumidity());
        valMeteo.put(COL_VENT, meteo.getWindSpeed());
        valMeteo.put(COL_NUAGE, meteo.getCloudPerc());
        valMeteo.put(COL_LASTUPDATE, meteo.getLastUpdate());
        valMeteo.put(COL_DATE, meteo.getDate());
        long new_id = 0;
        try {
            new_id = bdd.insert(TABLE_METEO, null, valMeteo);
        } catch (Exception e) {
            Log.e("MeteoManager", "Erreur a l'insert dans la base");
            e.printStackTrace();
        }
        return new_id;
    }

    /**
     * Supprimer la meteo de la base a une date et location
     *
     * @param meteo necessite la location et la date
     * @return int, le nombre d'enregistrement supprime
     */
    public int deleteEvenement(Meteo meteo) {
        open();
        int tmp = bdd.delete(TABLE_METEO, COL_LOCATION + " = " + meteo.getLocation()
                + " AND " + COL_DATE + " = " + meteo.getDate(), null);
        return tmp;
    }

    /**
     * Met a jour un enregistrement a partir de la location et de la date.
     * Necessite de recuperer l'objet original, de le modifier, puis de le reinserer.
     *
     * @param meteo Complet
     */
    public void updateMeteo(Meteo meteo) {
        open();
        ContentValues valMeteo = new ContentValues();
        valMeteo.put(COL_LOCATION, meteo.getLocation());
        valMeteo.put(COL_DESCRIPTION, meteo.getDescription());
        valMeteo.put(COL_ICONE, meteo.getIcone());
        valMeteo.put(COL_TEMPERATURE, meteo.getTemperature());
        valMeteo.put(COL_PRESSION, meteo.getPressure());
        valMeteo.put(COL_HUMIDITE, meteo.getHumidity());
        valMeteo.put(COL_VENT, meteo.getWindSpeed());
        valMeteo.put(COL_NUAGE, meteo.getCloudPerc());
        valMeteo.put(COL_LASTUPDATE, meteo.getLastUpdate());
        valMeteo.put(COL_DATE, meteo.getDate());
        bdd.update(TABLE_METEO, valMeteo, COL_LOCATION + " = " + meteo.getLocation()
                + " AND " + COL_DATE + " = " + meteo.getDate(), null);
    }
}
