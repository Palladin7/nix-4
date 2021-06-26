package ua.com.alevel;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class Hippodrome {

    private final Set<Horse> horses = ConcurrentHashMap.newKeySet();
    private final Map<Horse, Integer> places = new ConcurrentHashMap<>();
    private final AtomicInteger placeCounter = new AtomicInteger();
    private final Phaser phaser = new Phaser();

    public Set<Horse> getHorses() {
        return horses;
    }

    synchronized void startRace() {
        placeCounter.set(1);
        int numberOfHorses = horses.size();
        phaser.bulkRegister(numberOfHorses);
        int start = phaser.getPhase();

        for (Horse horse : horses) {
            new Thread(horse, horse.getColor() + " Tread").start();
        }

        int finish = phaser.awaitAdvance(start);
        phaser.awaitAdvance(finish);
        System.out.println("Race is finished!");
    }

    public Phaser getPhaser() {
        return phaser;
    }

    public AtomicInteger getPlaceCounter() {
        return placeCounter;
    }

    public Map<Horse, Integer> getPlaces() {
        return places;
    }

    public int getPlace(Horse horse) {
        return places.get(horse);
    }
}
