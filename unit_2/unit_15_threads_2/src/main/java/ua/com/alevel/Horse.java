package ua.com.alevel;

import java.util.Objects;
import java.util.Random;

public class Horse implements Runnable {

    private static final Random random = new Random();
    private static final int RACE_TRACK_DISTANCE = 1000;

    private final String color;
    private int currentDistance = 0;
    private Hippodrome hippodrome;

    public Horse(String color) {
        this.color = color;
    }

    public void addToHippodrome(Hippodrome hippodrome) {
        removeFromHippodrome();
        this.hippodrome = hippodrome;
        hippodrome.getHorses().add(this);
    }

    public void removeFromHippodrome() {
        if (hippodrome != null) {
            hippodrome.getHorses().remove(this);
        }
    }

    public String getColor() {
        return color;
    }

    @Override
    public void run() {
        hippodrome.getPhaser().arriveAndAwaitAdvance();
        System.out.println(color + " horse started running");

        while (currentDistance < RACE_TRACK_DISTANCE) {
            currentDistance += random.nextInt(100) + 101;
            try {
                Thread.sleep(random.nextInt(100) + 401);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(color + " horse is on " + currentDistance);
        }

        int place = hippodrome.getPlaceCounter().getAndIncrement();
        hippodrome.getPlaces().put(this, place);
        hippodrome.getPhaser().arriveAndDeregister();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return color.equals(horse.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return color;
    }
}
