package com.marado.entities;

import com.marado.app.Environment;
import java.util.Scanner;

public class BouncingObject {
    
    Scanner input = new Scanner(System.in);
    
    public String objectName = "Object";
    public boolean isBouncing = false;
    
    public double y = 0;
    double initVelocity = 120;
    double velocity;
    double time = 0.1;
    
    public String getName(){
        return objectName;
    }
    
    public void changeName(){
        objectName = input.nextLine();
        System.out.println("Object personal name is now " + objectName);
    }

    public void bounce(){
        if(isBouncing){
            y = initVelocity*time-0.5*Environment.g*Math.pow(time, 2);
            time += 0.1;
            velocity = initVelocity-Environment.g*time;

            if(velocity == 0)
                    initVelocity = Environment.g*time;

            if(y <= 0){
                y = 0;
                time = 0.1;
                velocity = 0;
                isBouncing = false;
            }
        }
    }
    
    public String getValues(){
        return ("Height: " + y + "\tInitial Velocity: " + initVelocity + "\tTime: " + time + "\tVelocity: " + velocity);
    }
}
