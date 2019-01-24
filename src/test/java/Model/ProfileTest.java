package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ProfileTest {

    @Test
    public void setMaxCentripetal() {
        Profile p = createProfile();

        p.setEstimatedFuelZero(0);
        assertEquals(0, p.getEstimatedFuelZero());

        p.setEstimatedFuelZero(-2);
        assertEquals(0, p.getEstimatedFuelZero());

        p.setEstimatedFuelZero(5);
        assertEquals(5, p.getEstimatedFuelZero());
    }

    @Test
    public void isEmptyTank() {
        Profile p = createProfile();

        p.setEstimatedFuelZero(5);
        assertFalse(p.isEmptyTank());

        p.setEstimatedFuelZero(3);
        assertFalse(p.isEmptyTank());

        p.setEstimatedFuelZero(2);
        assertTrue(p.isEmptyTank());
    }

    @Test
    public void calculateCentripetal() {
        Profile p = createProfile();

        assertEquals(0, p.calculateCentripetal(0));
        assertEquals(140, p.calculateCentripetal(10));
    }

    @Test
    public void estimateTank() {
        Profile p = createProfile();

        assertEquals(-1, p.estimateTank());
    }

    private Profile createProfile() {
        return new Profile(
            new Driver(0, "TestUser", 0, "test"),
            new Car(2000, "00-xx-xx", "model", "audi", 0x111111, 60, 1400)
        );
    }
}
