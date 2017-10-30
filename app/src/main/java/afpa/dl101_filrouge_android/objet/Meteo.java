package afpa.dl101_filrouge_android.objet;

public class Meteo {
    private String description;
    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double windSpeed;
    private Double cloudPerc;

    public Meteo(String description, Double temperature, Double pressure, Double humidity, Double windSpeed, Double cloudPerc) {
        this.description = description;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.cloudPerc = cloudPerc;
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
