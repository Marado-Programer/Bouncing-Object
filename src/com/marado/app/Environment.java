package com.marado.app;


public class Environment {
    
    public static double g = 9.80665; //Gravity of Earth
    
    public static String getGravity(){
        return (String.format("Gravity: %.3fm/(s^2)", g));
    }
    
}
