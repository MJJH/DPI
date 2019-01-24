package Model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class Profile implements Serializable {

    private Driver owner;

    private Car type;

    private Location current;

    private int estimatedFuelZero;
    private float maxCentripetal;

    private Set<Message> messages = new LinkedHashSet<>();

    public Profile(Driver owner, Car type) {
        this.owner = owner;
        this.type = type;
    }

    public Driver getOwner() {
        return owner;
    }

    public void setOwner(Driver owner) {
        this.owner = owner;
    }

    public Car getType() {
        return type;
    }

    public void setType(Car type) {
        this.type = type;
    }

    public Location getCurrent() {
        return current;
    }

    public void setCurrent(Location current) {
        this.current = current;
    }

    public int getEstimatedFuelZero() {
        return estimatedFuelZero;
    }

    public void setEstimatedFuelZero(int estimatedFuelZero) {
        if (estimatedFuelZero >= 0)
            this.estimatedFuelZero = estimatedFuelZero;
    }

    public float getMaxCentripetal() {
        return maxCentripetal;
    }

    public void setMaxCentripetal(float maxCentripetal) {
        if (maxCentripetal > this.maxCentripetal)
            this.maxCentripetal = maxCentripetal;
    }

    public Set<Message> getMessages() {
        if (messages == null)
            messages = new LinkedHashSet<>();

        return messages;
    }

    public boolean isEmptyTank() {
        return this.getEstimatedFuelZero() < 3;
    }

    public Message lastMessage() {
        return latestMessage(0);
    }

    public Message latestMessage(int offset) {
        return (Message) this.getMessages().toArray()[this.getMessages().size() - 1 - offset];
    }

    public int calculateCentripetal(int angle) {
        // https://courses.lumenlearning.com/physics/chapter/6-3-centripetal-force/
        if (angle == 0)
            return 0;
        return this.getType().getWeight() / angle;
    }

    public int estimateTank() {
        if (this.getMessages().size() < 2)
            return -1;

        Message last = lastMessage();
        double dif = latestMessage(1).tank - last.tank;

        if (dif == 0)
            return Integer.MAX_VALUE;

        return (int) Math.ceil(last.tank / dif);
    }

    public boolean update(Message update) {
        this.getMessages().add(update);

        this.setMaxCentripetal(calculateCentripetal(update.steer));
        this.setCurrent(update.gps);
        this.setEstimatedFuelZero(estimateTank());

        return true;
    }

    public boolean isCentripetalDead() {
        return this.getMaxCentripetal() > 3;
    }
}
