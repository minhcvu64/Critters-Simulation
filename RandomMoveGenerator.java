package com.company;

import java.util.Random;

public class RandomMoveGenerator {
    public final int getRandomMove() {
        // There are four directions(North, South, East, West).
        // Pick a random integer number from 0 to 3, corresponding to NORTH, SOUTH, EAST, WEST.
        Random random = new Random();
        int randomMove = random.nextInt(4);
        if (randomMove == 0) {
            return Critter.NORTH;
        } else if (randomMove == 1) {
            return Critter.SOUTH;
        } else if (randomMove == 2) {
            return Critter.EAST;
        } else {
            return Critter.WEST;
        }
    }
}
