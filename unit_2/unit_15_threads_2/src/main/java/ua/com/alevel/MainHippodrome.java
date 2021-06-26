package ua.com.alevel;

import java.util.Arrays;
import java.util.Scanner;

public class MainHippodrome {

    public static void main(String[] args) {
        Hippodrome hippodrome = new Hippodrome();

        Horse[] horses = {new Horse("Green"),
                new Horse("Yellow"),
                new Horse("Blue"),
                new Horse("White")};

        hippodrome.getHorses().addAll(Arrays.asList(horses));

        for (Horse horse : horses) {
            horse.addToHippodrome(hippodrome);
        }

        System.out.println("Select which horse you will bet on: ");

        for (int i = 0; i < horses.length; i++) {
            System.out.println(i + 1 + ") " + horses[i]);
        }

        int horseNumber;
        Scanner scanner = new Scanner(System.in);
        horseNumber = scanner.nextInt();

        while (horseNumber <= 0 || horseNumber > horses.length) {
            System.out.print("Wrong input, try again: ");
            horseNumber = scanner.nextInt();
        }

        Horse selectedHorse = horses[horseNumber - 1];
        System.out.println("You have selected " + selectedHorse.getColor() + " horse");

        hippodrome.startRace();

        System.out.println("Your horse finished " + hippodrome.getPlace(selectedHorse) + "/" + horses.length);
    }
}
