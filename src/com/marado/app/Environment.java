package com.marado.app;

import java.util.Scanner;

public class Environment {
    
    Scanner input = new Scanner(System.in);

    public static double g = 9.80665; //Gravity of Earth
    
    public static double getGravity(){
        return g;
    }
    
    public void changeGravity(){
        g = input.nextDouble();
        System.out.println("Gravity is now " + g);
    }
}
