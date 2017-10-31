package afpa.dl101_filrouge_android.objet;

public class Evenement {
    private int id;
    private String titre;
    private String description;
    private int dateLongDebut;
    private int dateLongFin;
    private String location;

    public Evenement(String titre, String description, int dateLongDebut, int dateLongFin, String location) {
        this.id = 0;
        this.titre = titre;
        this.description = description;
        this.dateLongDebut = dateLongDebut;
        this.dateLongFin = dateLongFin;
        this.location = location;
    }

    public Evenement(int id, String titre, String description, int dateLongDebut, int dateLongFin, String location) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateLongDebut = dateLongDebut;
        this.dateLongFin = dateLongFin;
        this.location = location;
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

    public int getDateLongDebut() {
        return dateLongDebut;
    }

    public int getDateLongFin() {
        return dateLongFin;
    }

    public String getLocation() {
        return location;
    }
}
