package Model;

import java.io.Serializable;

public class Driver implements Serializable {

    private int born;
    private String name;

    private int licenseAge;

    private String gender;

    public Driver(int born, String name, int licenseAge, String gender) {
        this.born = born;
        this.name = name;
        this.licenseAge = licenseAge;
        this.gender = gender;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLicenseAge() {
        return licenseAge;
    }

    public void setLicenseAge(int licenseAge) {
        this.licenseAge = licenseAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
