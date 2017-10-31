package afpa.dl101_filrouge_android.objet;

public class Meteo {
    private String location;
    private String description;
    private String icone;
    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double windSpeed;
    private Double cloudPerc;
    private String update;
    private String date;

    public Meteo(String location, String date) {
        this.location = location;
        this.date = date;
    }

    public Meteo(String location, String description, String icone, Double temperature,
                 Double pressure, Double humidity, Double windSpeed,
                 Double cloudPerc, String update, String date) {
        this.location = location;
        this.description = description;
        this.icone = icone;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.cloudPerc = cloudPerc;
        this.update = update;
        this.date = date;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getCloudPerc() {
        return cloudPerc;
    }

    public void setCloudPerc(Double cloudPerc) {
        this.cloudPerc = cloudPerc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
