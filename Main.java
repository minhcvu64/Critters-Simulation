package com.company;

public class Main {

    public static void main(String[] args) {
        CritterFrame frame = new CritterFrame();

        frame.add(25, Bird.class);
        frame.add(25, Frog.class);
        frame.add(25, Mouse.class);
        frame.add(25, Turtle.class);
        frame.add(25, Wolf.class);

        frame.start();
    }
}
