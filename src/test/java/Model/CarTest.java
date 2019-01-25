package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CarTest {

    @Test
    public void getBuildYear() {
        Car c = getCar();
        assertEquals(2000, c.getBuildYear());
    }

    @Test
    public void setBuildYear() {
        Car c = getCar();
        int year = 2001;
        c.setBuildYear(year);
        assertEquals(year, c.getBuildYear());
    }

    @Test
    public void getPlates() {
        Car c = getCar();
        assertEquals("35-gg-tz", c.getPlates());
    }

    @Test
    public void setPlates() {
        Car c = getCar();
        String plates = "00-xx-xx";
        c.setPlates(plates);
        assertEquals(plates, c.getPlates());
    }

    @Test
    public void getModel() {
        Car c = getCar();
        assertEquals("Almera", c.getModel());
    }

    @Test
    public void setModel() {
        Car c = getCar();
        String model = "Model";
        c.setModel(model);
        assertEquals(model, c.getModel());
    }

    @Test
    public void getManufacturer() {
        Car c = getCar();
        assertEquals("Nissan", c.getManufacturer());
    }

    @Test
    public void setManufacturer() {
        Car c = getCar();
        String manufacturer = "mf";
        c.setManufacturer(manufacturer);
        assertEquals(manufacturer, c.getManufacturer());
    }

    @Test
    public void getColor() {
        Car c = getCar();
        assertEquals(0x111111, c.getColor());
    }

    @Test
    public void setColor() {
        Car c = getCar();
        int color = 5;
        c.setColor(color);
        assertEquals(color, c.getColor());
    }

    @Test
    public void getTankVolume() {
        Car c = getCar();
        assertEquals(60, c.getTankVolume());
    }

    @Test
    public void setTankVolume() {
        Car c = getCar();
        int volume = 50;
        c.setTankVolume(volume);
        assertEquals(volume, c.getTankVolume());
    }

    @Test
    public void getWeight() {
        Car c = getCar();
        assertEquals(1500, c.getWeight());
    }

    @Test
    public void setWeight() {
        Car c = getCar();
        int weight = 1000;
        c.setWeight(weight);
        assertEquals(weight, c.getWeight());
    }

    private Car getCar() {
        return new Car(
                2000,
                "35-gg-tz",
                "Almera",
                "Nissan",
                0x111111,
                60,
                1500
        );
    }
}
