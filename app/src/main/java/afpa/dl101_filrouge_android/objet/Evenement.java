package afpa.dl101_filrouge_android.objet;

/**
 * Created by DL101 on 23/10/2017.
 */

public class Evenement {
    private int id;
    private String titre;
    private String description;
    private int dateLongDebut;
    private int dateLongFin;

    public Evenement(String titre, String description, int dateLongDebut, int dateLongFin) {
        this.id = 0;
        this.titre = titre;
        this.description = description;
        this.dateLongDebut = dateLongDebut;
        this.dateLongFin = dateLongFin;
    }

    public Evenement(int id, String titre, String description, int dateLongDebut, int dateLongFin) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateLongDebut = dateLongDebut;
        this.dateLongFin = dateLongFin;
    }

    public String toString() {
        return titre + " " + description;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public long getDateIntDebut() {
        return dateLongDebut;
    }

    public long getDateIntFin() {
        return dateLongFin;
    }
}
