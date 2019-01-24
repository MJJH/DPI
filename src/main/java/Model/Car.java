package Model;

import java.io.Serializable;

public class Car implements Serializable {

    private int buildYear;
    private String plates;

    private String model;
    private String manufacturer;

    private int color;

    private int tankVolume;
    private int weight;

    public Car(int buildYear, String plates, String model, String manufacturer, int color, int tankVolume, int weight) {
        this.buildYear = buildYear;
        this.plates = plates;
        this.model = model;
        this.manufacturer = manufacturer;
        this.color = color;
        this.tankVolume = tankVolume;
        this.weight = weight;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(int tankVolume) {
        this.tankVolume = tankVolume;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
