package afpa.dl101_filrouge_android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Vector;

import afpa.dl101_filrouge_android.objet.Evenement;

public class EvenementManager {
    private static final String TABLE_EVENEMENT = "evenement";
    private static final String COL_ID = "id_event";
    private static final int NUM_COL_ID = 0;
    private static final String COL_TITRE = "titre";
    private static final int NUM_COL_TITRE = 1;
    private static final String COL_DESCRIPTION = "description";
    private static final int NUM_COL_DESCRIPTION = 2;
    private static final String COL_DEBUT = "date_debut";
    private static final int NUM_COL_DEBUT = 3;
    private static final String COL_FIN = "date_fin";
    private static final int NUM_COL_FIN = 4;

    private static final String NOM_BDD = "calendrierMeteoBDD";
    private static final int VERSION = 1;

    private SQLiteDatabase bdd;
    private GestBDD gestBDD;

    public EvenementManager(Context context) {
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
     * Recupere tout les evenement en cours a une dateLong donnee.
     *
     * @param date date en int, format YYYYMMDD
     * @return Un vecteur comprenant les evenement se passant a cette date
     */
    public Vector<Evenement> getEvenementJour(int date) {
        open();
        /*
        Cursor c = bdd.query(TABLE_EVENEMENT,
                new String[]{COL_ID, COL_TITRE, COL_DESCRIPTION, COL_DEBUT, COL_FIN},
                date + " BETWEEN " + COL_DEBUT + " AND " + COL_FIN,
                //COL_DEBUT + " <= " + date + " AND " + COL_FIN + " >= " + date,
                null, null, null, null);
        */
        Cursor c = bdd.rawQuery("SELECT * FROM " + TABLE_EVENEMENT, null);
        Log.d("EvenementManager", "c.getCount() : " + c.getCount());
        if (c.getCount() != 0) {
            return cursorToVectorEv(c);
        } else {
            return null;
        }
    }

    /**
     * Transforme un curseur d'evenement en vecteur d'evenements
     *
     * @param c Transforme un curseur en vecteur d'evenement
     * @return vecteur d'evenement
     */
    private Vector<Evenement> cursorToVectorEv(Cursor c) {
        if (c.getCount() == 0)
            return null;

        Vector<Evenement> vEvenement = new Vector<Evenement>();

        c.moveToFirst();
        do {
            vEvenement.add(new Evenement(c.getInt(NUM_COL_ID),
                    c.getString(NUM_COL_TITRE),
                    c.getString(NUM_COL_DESCRIPTION),
                    c.getInt(NUM_COL_DEBUT),
                    c.getInt(NUM_COL_FIN)));
        } while (c.moveToNext());
        c.close();

        return vEvenement;
    }

    /**
     * Insert un objet evenement dans la base.
     *
     * @param evenement Seul l'id n'est pas requis, car auto-increment.
     * @return long, id qui vient d'etre cree.
     */
    public long insertEvenement(Evenement evenement) {
        open();
        ContentValues valEvent = new ContentValues();
        valEvent.put(COL_FIN, evenement.getDateIntFin());
        valEvent.put(COL_DEBUT, evenement.getDateIntDebut());
        valEvent.put(COL_DESCRIPTION, evenement.getDescription());
        valEvent.put(COL_TITRE, evenement.getTitre());
        long new_id = 0;
        try {
            new_id = bdd.insert(TABLE_EVENEMENT, null, valEvent);
        } catch (Exception e) {
            Log.e("EvenementManager", "Erreur a l'insert dans la base");
            e.printStackTrace();
        }
        return new_id;
    }

    /**
     * Supprimer un evenement de la base a partir de son id
     *
     * @param evenement necessite seulement l'id
     * @return int, le nombre d'enregistrement supprime
     */
    public int deleteEvenement(Evenement evenement) {
        open();
        int tmp = bdd.delete(TABLE_EVENEMENT, COL_ID + " = " + evenement.getId(), null);
        return tmp;
    }

    /**
     * Met a jour un enregistrement a partir de l'id.
     * Necessite de recuperer l'objet original, de le modifier, puis de le reinserer.
     *
     * @param evenement Complet
     */
    public void updateEvenement(Evenement evenement) {
        open();
        ContentValues valEvent = new ContentValues();
        valEvent.put(COL_TITRE, evenement.getTitre());
        valEvent.put(COL_DESCRIPTION, evenement.getDescription());
        valEvent.put(COL_DEBUT, evenement.getDateIntDebut());
        valEvent.put(COL_FIN, evenement.getDateIntFin());
        bdd.update(TABLE_EVENEMENT, valEvent, COL_ID + " = " + evenement.getId(), null);
    }
}
